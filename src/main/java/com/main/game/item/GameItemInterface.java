package com.main.game.item;

import java.util.List;

//Defines some behavioral methods for GameItem
public interface GameItemInterface {
    void addItem();
    List<GameItem> getItems();
    void upgradeItem(GameItem item);

    String getName();
    Rarity getRarity();
    int getQuantity();

    void setItems(List<GameItem> items);
}
