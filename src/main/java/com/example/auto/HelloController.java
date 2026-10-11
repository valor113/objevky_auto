package com.example.auto;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML private Rectangle autoRect;
    private Auto auto;

    @FXML
    public void initialize() {
        if (autoRect != null) {
            auto = new Auto(autoRect);
        }
    }

    @FXML
    protected void handleManual() {
        if (auto != null) {
            auto.setManualMode();
        }
    }

    @FXML
    protected void handleAutomatic() {
        if (auto != null) {
            auto.setAutomaticMode();
        }
    }
}