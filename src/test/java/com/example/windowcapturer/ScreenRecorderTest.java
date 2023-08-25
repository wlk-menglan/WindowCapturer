package com.example.windowcapturer;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wlk
 * @date 2023/8/4 9:09
 */
@SpringBootTest
public class ScreenRecorderTest {

//    @Test
//    public void testScreenRecorder() throws InterruptedException {
//        CodeManager codeManager = new CodeManager(5);
//        ScreenRecorder screenRecorder = new ScreenRecorder();
//        String Url = "https://v.youku.com/v_show/id_XNTk1NTA1NzA4MA==.html?spm=a2hja.14919748_WEBHOME_NEW.drawer2.d_zj1_1&s=aeecbb6ff65143a8b59f&scm=20140719.rcmd.35027.show_aeecbb6ff65143a8b59f";
//        String FfmpegCommand = "ffmpeg -f x11grab -framerate 60 -s 1280x720 -i :99 -vf crop=1280:720:0:0 -t 10 -c:v mpeg4 -crf 0 -pix_fmt yuv420p -b:v 40M -preset ultrafast -qp 0";
//        int id = 1;
//        int code = codeManager.allocate();
//        screenRecorder.ScreenRecorder(Url,FfmpegCommand,id,code);
//        codeManager.release(code);
//    }
}
