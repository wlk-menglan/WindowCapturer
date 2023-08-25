package com.example.windowcapturer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author wlk
 * @date 2023/8/4 9:31
 */
public class XvfbBuilder {
    public void buildXvfb(Integer code) throws IOException {
        try {
            // Start Xvfb
            ProcessBuilder startPb = new ProcessBuilder("Xvfb", ":"+code, "-screen", "0", "1280x720x16", "-ac", "-extension", "RANDR", "-noreset", "&");
            Process startProcess = startPb.start();
            startProcess.waitFor();

            // Set environment variable for Xvfb
            ProcessBuilder envPb = new ProcessBuilder("bash", "-c", "export DISPLAY=:"+code);
            Process envProcess = envPb.start();
            envProcess.waitFor();
            System.out.println("Xvfb process started and environment variables set.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void killXvfb(Integer code){
        int targetPort = code;
        int targetPid = getTargetXvfbProcessId(targetPort);
        if (targetPid != -1) {
            terminateXvfbProcess(targetPid);
            System.out.println("Xvfb process on port " + targetPort + " terminated.");
        } else {
            System.out.println("Xvfb process on port " + targetPort + " not found.");
        }
    }

    private static int getTargetXvfbProcessId(int targetPort) {
        try {
            ProcessBuilder pb = new ProcessBuilder("ps", "axo", "pid,args");
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Xvfb")) {
                    String[] parts = line.split("\\s+");
                    for (int i = 1; i < parts.length; i++) {
                        if (parts[i].startsWith(":" + targetPort)) {
                            return Integer.parseInt(parts[0]);
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return -1; // 如果找不到目标 Xvfb 进程，则返回 -1
    }

    private static void terminateXvfbProcess(int pid) {
        try {
            ProcessBuilder pb = new ProcessBuilder("kill", "-TERM", String.valueOf(pid));
            Process process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
