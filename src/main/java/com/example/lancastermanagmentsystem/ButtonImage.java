package com.example.lancastermanagmentsystem;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class ButtonImage {
    private final Button button;
    private final ImageView butttonImage;

    private final Image orgButtonImage;
    private final Image hoverButtonImage;

    private final Label buttonLabel;
    private Boolean selected = false;

    /**
     * Constructor.
     * @param button button to edit.
     * @param label text to go on button.
     */
    public ButtonImage(Button button, String label) {
        this.button = button;
        this.buttonLabel = new Label(label);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/fonts/Dishcek.otf"), 20);
        buttonLabel.setFont(customFont);

        InputStream buttonImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/button.png");
        InputStream hoverImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/buttonHover.png");

        assert buttonImageLoc != null;
        assert hoverImageLoc != null;
        orgButtonImage = new Image(buttonImageLoc);
        hoverButtonImage = new Image(hoverImageLoc);

        butttonImage = new ImageView(orgButtonImage);
    }

    /**
     * Constructor.
     * @param button button to edit.
     * @param label text to go on button.
     * @param selected is the page selected.
     */
    public ButtonImage(Button button, String label, boolean selected) {
        this.button = button;
        this.buttonLabel = new Label(label);
        this.selected = selected;

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/fonts/Dishcek.otf"), 20);
        buttonLabel.setFont(customFont);

        InputStream buttonImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/button.png");
        InputStream hoverImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/buttonHover.png");
        InputStream selectedImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/buttonClicked.png");


        assert buttonImageLoc != null;
        assert hoverImageLoc != null;
        assert selectedImageLoc != null;

        if(selected) {
            orgButtonImage = new Image(selectedImageLoc);
            hoverButtonImage = new Image(hoverImageLoc);
        } else{
            orgButtonImage = new Image(buttonImageLoc);
            hoverButtonImage = new Image(hoverImageLoc);
        }


        butttonImage = new ImageView(orgButtonImage);
    }

    /**
     * Set the button to have a background.
     */
    private void setBackground(){
        button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        butttonImage.setFitWidth(button.getPrefWidth());
        butttonImage.setFitHeight(button.getPrefHeight());
    }

    /**
     * Set button to have a hover image.
     */
    private void setAnimation(){
        if(!selected) {
            button.setOnMouseEntered(event -> butttonImage.setImage(hoverButtonImage));
            button.setOnMouseExited(event -> butttonImage.setImage(orgButtonImage));
        }
    }

    /**
     * add all graphics to the button.
     */
    public void setGraphics(){
        setBackground();
        setAnimation();
        button.setGraphic(new StackPane(butttonImage, buttonLabel));
    }
}
