package com.example.windowcapturer.controller;

import com.example.windowcapturer.util.CodePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public class ScreenRecordingController {
    private final CodePool codePool;

    @Autowired
    public ScreenRecordingController(CodePool codePool) {
        this.codePool = codePool;
    }

    @PostMapping(value = "/record", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void recordScreen(@RequestParam("url") String url , @RequestParam("n") int n) throws IOException, InterruptedException {
//        int code = codeManager.allocate();
//        ScreenPool screenPool = new ScreenPool(7680, 4320, 6, 6,2);
//        Rectangle screen = screenPool.getScreen(code);
//        ScreenRecorder screenRecorder = new ScreenRecorder();
////        screenRecorder.ScreenRecorder(url,n,code,screen);
//        codeManager.release(code);
    }
}

