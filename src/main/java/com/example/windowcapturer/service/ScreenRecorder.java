package com.example.windowcapturer.service;

import com.example.windowcapturer.Bean.LittleScreen;
import com.example.windowcapturer.util.ChromeLauncher;
import com.example.windowcapturer.util.FfmpegExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author wlk
 */
public class ScreenRecorder {
    public void ScreenRecorder(String Url , Integer userId , LittleScreen littleScreen) throws InterruptedException {
        //配置driver并打开浏览器页面
        ChromeLauncher chromeLauncher = new ChromeLauncher();
        WebDriver driver = chromeLauncher.setEnvironmentAndLaunchChrome(littleScreen);
        driver.get(Url);

        // 等待一段时间，使页面加载完成
        Thread.sleep(5000);

        //录屏
        FfmpegExecutor ffmpegExecutor = new FfmpegExecutor(userId,littleScreen);
        ffmpegExecutor.doFfmpeg();

        // 关闭ChromeDriver
        driver.close();
        driver.quit();
    }
}

