package com.example.windowcapturer;

import com.example.windowcapturer.util.CodePool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wlk
 * @date 2023/8/3 16:06
 */
@SpringBootTest
public class CodeMangerTest {
    private final CodePool codePool ;

    @Autowired
    public CodeMangerTest(CodePool codePool) {
        this.codePool = codePool;
    }


    @Test
    void testGetById(){
        System.out.println(codePool.allocate());
        System.out.println(codePool.release(codePool.allocate()));
        System.out.println(codePool.release(36));
    }
//    @Test
//    void testGetId(){
//        ScreenPool screenPool = new ScreenPool(7680, 4320, 4, 4,2);
//        for(int i=0;i<32;i++){
//            LittleScreen littleScreen = screenPool.allocateLittleScreen();
//            System.out.println(littleScreen.code);
//        }
//    }
}
