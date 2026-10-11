package com.example.auto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Auto {

    private final Node autoTg;
    private double speedX = 3;
    private boolean isAutomatic = false;
    private Timeline autoTimeline;

    private final double BOUND_WIDTH = 1000.0;
    private final double BOUND_HEIGHT = 800.0;

    public Auto(Node autoTg) {
        this.autoTg = autoTg;

        autoTg.setOnMouseClicked(e -> {
            isAutomatic = !isAutomatic;

            if (isAutomatic) {
                setAutomaticMode();
            } else {
                setManualMode();
            }
        });

        autoTg.setFocusTraversable(true);
        autoTg.setOnKeyPressed(e -> {
            if (!isAutomatic) {
                double step = 10;
                double newX = autoTg.getLayoutX();
                double newY = autoTg.getLayoutY();

                double nodeWidth = autoTg.getLayoutBounds().getWidth();
                double nodeHeight = autoTg.getLayoutBounds().getHeight();

                if (e.getCode() == KeyCode.UP) {
                    newY = Math.max(0, newY - step);
                }
                if (e.getCode() == KeyCode.DOWN) {
                    newY = Math.min(BOUND_HEIGHT - nodeHeight, newY + step);
                }
                if (e.getCode() == KeyCode.LEFT) {
                    newX = Math.max(0, newX - step);
                }
                if (e.getCode() == KeyCode.RIGHT) {
                    newX = Math.min(BOUND_WIDTH - nodeWidth, newX + step);
                }

                autoTg.setLayoutX(newX);
                autoTg.setLayoutY(newY);
            }
        });

        autoTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            if (isAutomatic) {
                double newX = autoTg.getLayoutX() + speedX;
                double nodeWidth = autoTg.getLayoutBounds().getWidth();

                if (newX + nodeWidth >= BOUND_WIDTH) {
                    newX = BOUND_WIDTH - nodeWidth;
                    speedX = -speedX;
                } else if (newX <= 0) {
                    newX = 0;
                    speedX = -speedX;
                }

                autoTg.setLayoutX(newX);
            }
        }));
        autoTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void setManualMode() {
        isAutomatic = false;
        autoTimeline.stop();
        autoTg.requestFocus();
    }

    public void setAutomaticMode() {
        isAutomatic = true;
        autoTimeline.play();
    }
}