package com.example.windowcapturer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WindowCapturerApplicationTests {

    @Value("${width}")
    private int width;

    @Value("${height}")
    private int height;


}
