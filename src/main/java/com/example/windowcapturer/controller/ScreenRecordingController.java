package com.example.windowcapturer.controller;

import com.example.windowcapturer.service.ScreenRecorder;
import com.example.windowcapturer.util.CodePool;
import com.example.windowcapturer.Bean.LittleScreen;
import com.example.windowcapturer.util.ScreenPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wlk
 */
@RestController
public class ScreenRecordingController {

    private final CodePool codePool ;
    private final ScreenPool screenPool ;

    @Autowired
    public ScreenRecordingController(CodePool codePool , ScreenPool screenPool) {
        this.codePool = codePool;
        this.screenPool = screenPool;
    }

    @PostMapping(value = "/record", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void ScreenRecord(@RequestParam("url") String url , @RequestParam("userId") int userId) throws InterruptedException {
        int code = codePool.allocate();
        System.out.println(code);
        LittleScreen littleScreen = screenPool.getLittleScreen(code);
        System.out.println(littleScreen.bigScreenId);
        ScreenRecorder screenRecorder = new ScreenRecorder();
        screenRecorder.ScreenRecorder(url,userId,littleScreen);
        codePool.release(code);
    }
}


