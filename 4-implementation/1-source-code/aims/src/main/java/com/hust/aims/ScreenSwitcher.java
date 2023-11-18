package com.hust.aims;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

/**
 * Description
 * This class is a utility class for switching screens
 * This class should never be initialized
 * The setMainScene method should only be called once in the start method of the Application class
 * All methods in this class are static i.e. everything should be called with ScreenSwitcher.method-name
 * @author TVKain
 * @version 1.0
 * @since 2023-11-18
 */
public class ScreenSwitcher {
    private static Scene mainScene;
    private static final HashMap<String, String> screenMap = new HashMap<>();
    public static void setMainScene(Scene scene) {
        if (mainScene == null) {
            mainScene = scene;
        }
    }

    public static void addScreen(String key, String parent) {
        screenMap.put(key, parent);
    }

    public static void removeScreen(String key) {
        screenMap.remove(key);
    }

    public static void setScreen(String key, Object controller) {
        String parent = screenMap.get(key);
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenSwitcher.class.getResource(parent));
        fxmlLoader.setController(controller);
        try {
            mainScene.setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
