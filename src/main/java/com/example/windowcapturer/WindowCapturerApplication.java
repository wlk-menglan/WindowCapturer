package com.example.windowcapturer;

import com.example.windowcapturer.util.CodePool;
import com.example.windowcapturer.util.ScreenPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * @author wlk
 */
@SpringBootApplication
public class WindowCapturerApplication {

    @Value("${bigScreensNum}")
    private int bigScreensNum;

    @Value("${width}")
    private int width;

    @Value("${height}")
    private int height;

    @Value("${numScreensPerRow}")
    private int numScreensPerRow;

    @Value("${numScreensPerColumn}")
    private int numScreensPerColumn;

    //创建编码池
    @Bean
    public CodePool codePool() {
        // capacity是初始化的编号池的容量
        return new CodePool(bigScreensNum*numScreensPerRow*numScreensPerColumn);
    }
    //创建屏幕池
    @Bean
    public ScreenPool screenPool() {
        /*
         width*height是设置屏幕的分辨率；
         numScreensPerRow和numScreensPerColumn分别为横纵方向小屏幕的数量；
         bigScreensNum为全部大屏幕的数量，对应创建的Xvfb虚拟屏幕的数量；
         */
        return new ScreenPool(width*numScreensPerRow, height*numScreensPerColumn, numScreensPerRow, numScreensPerColumn,bigScreensNum);
    }

    public static void main(String[] args) {
        SpringApplication.run(WindowCapturerApplication.class, args);
    }

}
