package com.example.windowcapturer.util;

import com.example.windowcapturer.Bean.LittleScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author wlk
 * @date 2023/8/15 15:03
 */
public class ChromeLauncher {

    String path = System.getProperty("user.dir");
    public WebDriver setEnvironmentAndLaunchChrome(LittleScreen littleScreen) {
        System.setProperty("webdriver.chrome.driver", path+"/chromedriver");
        System.setProperty("webdriver.chrome.bin", "/usr/bin/google-chrome"+littleScreen.code);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // 最大化窗口
        options.addArguments("--window-size=1280,720");
        options.addArguments("--window-position="+littleScreen.screen.x+","+littleScreen.screen.y);
        options.addArguments("--enable-screen-capture");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port="+(9220+littleScreen.code));
        options.addArguments("--user-data-dir=/tmp/chrome_profile_"+littleScreen.code);
        options.addArguments("--display=:"+littleScreen.bigScreenId); // 指定为虚拟屏幕 :code;
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingPort(1020+littleScreen.code)
                .build();

        return new ChromeDriver(service,options);
    }
}
