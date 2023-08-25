package com.example.windowcapturer.util;

import com.example.windowcapturer.Bean.LittleScreen;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wlk
 * @date 2023/8/15 10:41
 */
public class FfmpegExecutor {

    @Value("${width}")
    private int width;

    @Value("${height}")
    private int height;
    String path = System.getProperty("user.dir");
    public File file;
    public LittleScreen littleScreen;
    public FfmpegExecutor(Integer userid,LittleScreen littleScreen) {
        this.littleScreen = littleScreen;
        this.file = createFile(userid, littleScreen);
    }
    public void doFfmpeg() {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg",
                "-f", "x11grab",
                "-framerate", "60",
                "-s", "1280x720",
                "-i", ":"+littleScreen.bigScreenId,
                "-filter:v", "crop=1280:720:"+littleScreen.screen.x+":"+littleScreen.screen.y,
                "-t", "10",
                "-c:v", "mpeg4",
                "-crf", "0",
                "-pix_fmt", "yuv420p",
                "-b:v", "16M",
                "-preset", "ultrafast",
                "-qp", "0",
                file.toString()
        );
        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Ffmpeg命令执行成功！");
            } else {
                System.out.println("Ffmpeg命令执行失败！");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private File createFile(Integer userId,LittleScreen littleScreen){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formatDateTime = now.format(formatter);
        String videoName = userId.toString()+littleScreen.code.toString()+formatDateTime;
        File file = new File(path+"/videos/video"+videoName+".mp4");
        return file;
    }

}
