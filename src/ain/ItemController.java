package com.example.ain.ain;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemController {
@FXML
    private ImageView itemImage;
@FXML
    private Label ItemName;
@FXML
    private Label ItemCount;
@FXML
    private Label ItemPrice ;

    public void setData(Item item)
    {
        Image image = new Image(getClass().getResourceAsStream(item.getImageSrc()));
        itemImage.setImage(image);
        ItemName.setText(item.getName());
        ItemCount.setText(item.getCount());
        ItemPrice.setText(item.getPrice());
    }
}
