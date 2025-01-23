package com.main.game.itemManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.game.item.GameItem;

import java.io.*;
import java.util.List;

/*
**  IS NOT WORKING
 */
public class GameItemManager {
    private final GameItem item = GameItem.getInstance();

    //To save items to file
    public void saveToFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("src/main/resources/" + fileName), item.getItems());
            System.out.println("Items saved to JSON: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving to JSON: " + e.getMessage());
        }
    }

    //To load items from file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/" + fileName))) {
            item.setItems((List<GameItem>) ois.readObject());
            System.out.println("Items loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading items from file: " + e.getMessage());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            item.setItems(objectMapper.readValue(new File("src/main/resources/" + fileName),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, GameItem.class)));
        } catch (IOException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
