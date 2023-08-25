package com.example.windowcapturer.util;


import com.example.windowcapturer.Bean.LittleScreen;
import org.openqa.selenium.Rectangle;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class ScreenPool {
    private List<LittleScreen> screens;
    private BitSet isOccupied;
    private List<Integer> availableIndices;
    public ScreenPool(int width, int height, int numScreensPerRow, int numScreensPerColumn, int bigScreenNum) {
        int numScreens = bigScreenNum * numScreensPerRow * numScreensPerColumn;

        screens = new ArrayList<>();
        isOccupied = new BitSet(numScreens);
        availableIndices = new ArrayList<>();

        int screenWidth = width / numScreensPerRow;
        int screenHeight = height / numScreensPerColumn;

        // 创建小屏幕
        for (int bigScreenId = 1; bigScreenId <= bigScreenNum; bigScreenId++) {
            for (int row = 0; row < numScreensPerColumn; row++) {
                for (int col = 0; col < numScreensPerRow; col++) {
                    LittleScreen littleScreen = new LittleScreen();
                    littleScreen.screen = new Rectangle(col * screenWidth, row * screenHeight, screenWidth, screenHeight);
                    littleScreen.code = numScreensPerRow*numScreensPerColumn*(bigScreenId-1)+row*numScreensPerRow+col;
                    littleScreen.bigScreenId = bigScreenId;
                    screens.add(littleScreen);
                    availableIndices.add(screens.size() - 1);
                }
            }
        }
    }
    public LittleScreen getLittleScreen(int code) {
        return screens.get(code-1);
    }

//    public synchronized LittleScreen allocateLittleScreen() {
//        if (!availableIndices.isEmpty()) {
//            int index = availableIndices.remove(availableIndices.size() - 1);
//            isOccupied.set(index);
//            return screens.get(index);
//        }
//        return null; // 如果没有可用的屏幕，返回null
//    }

//    public synchronized void releaseLittleScreen(LittleScreen littleScreen) {
//        int index = -1;
//        for (int i = 0; i < screens.size(); i++) {
//            if (screens.get(i).equals(littleScreen)) {
//                index = i;
//                break;
//            }
//        }
//        if (index != -1 && isOccupied.get(index)) {
//            isOccupied.clear(index);
//            availableIndices.add(index);
//        }
//    }
}
