package com.main.game.item;

import java.util.*;

public class GameItem extends AbstractGameItem implements GameItemInterface{
    private final List<GameItem> items = new LinkedList<>();
    Map<Rarity, Integer> rarityWeights = new HashMap<>();

    private GameItem(String name, Rarity rarity, int quantity) {
        this.name = name;
        this.rarity = rarity;
        this.quantity = quantity;
    }

    private GameItem() {
    }
    //To make this class singleton
    public static GameItem getInstance() {
        return new GameItem();
    }

    //Generates and adds a new item
    @Override
    public void addItem() {
        rarityWeights.put(Rarity.Common, 50);
        rarityWeights.put(Rarity.Great, 25);
        rarityWeights.put(Rarity.Rare, 15);
        rarityWeights.put(Rarity.Epic, 8);
        rarityWeights.put(Rarity.Legendary, 2);

        String name = "Sword";
        Rarity rarity = getRandomRarity(rarityWeights);
        GameItem item = new GameItem(name, rarity, 1);
        addNewItem(item);
    }

    //Return the list of items
    @Override
    public List<GameItem> getItems() {
        return items;
    }

    //Upgrades the rarity of an item
    @Override
    public void upgradeItem(GameItem item) {
        if (!contains(item) || item.quantity < 3) {
            System.out.println("Unable to upgrade this item");
            return;
        }
        switch (item.rarity) {
            case Common -> {
                items.get(items.indexOf(item)).quantity -= 3;
                addNewItem(new GameItem(item.name, Rarity.Great, 1));
            }
            case Great -> {
                items.get(items.indexOf(item)).quantity -= 3;
                addNewItem(new GameItem(item.name, Rarity.Rare, 1));
            }
            case Rare -> {
                items.get(items.indexOf(item)).quantity -= 3;
                addNewItem(new GameItem(item.name, Rarity.Epic, 1));
            }
            case Epic -> {
                items.get(items.indexOf(item)).quantity -= 3;
                addNewItem(new GameItem(item.name, Rarity.Epic1, 1));
            }
            case Epic1 -> {
                items.get(items.indexOf(item)).quantity -= 3;
                addNewItem(new GameItem(item.name, Rarity.Epic2, 1));
            }
            case Epic2 -> {
                items.get(items.indexOf(item)).quantity -= 3;
                addNewItem(new GameItem(item.name, Rarity.Legendary, 1));
            }
            case Legendary -> System.out.println("Already Legendary");
        }
    }

    //Returns the name of the item
    @Override
    public String getName() {
        return this.name;
    }

    //Returns the rarity of the item
    @Override
    public Rarity getRarity() {
        return this.rarity;
    }

    //Returns the quantity of the item
    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public void setItems(List<GameItem> items) {
    }

    //Custom equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameItem gameItem = (GameItem) o;
        return name.equals(gameItem.name) && rarity == gameItem.rarity;
    }

    //Custom toString method
    @Override
    public String toString() {
        return "====GameItem====\n" +
                "name='" + name + "'\n" +
                "rarity=" + rarity + "\n" +
                "quantity=" + quantity + "\n" +
                "==================";
    }

    //Checks if the item is already in the list
    public boolean contains(GameItem gameItem) {
        for (GameItem it: items) {
            if (it.equals(gameItem)) {
                return true;
            }
        }
        return false;
    }

    //Returns a random rarity based on the rarity weights
    private static Rarity getRandomRarity(Map<Rarity, Integer> rarityWeights) {
        int totalWeight = rarityWeights.values().stream().mapToInt(Integer::intValue).sum();

        int random = new Random().nextInt(totalWeight);

        int cumulativeWeight = 0;
        for (Map.Entry<Rarity, Integer> entry : rarityWeights.entrySet()) {
            cumulativeWeight += entry.getValue();
            if (random < cumulativeWeight) {
                return entry.getKey();
            }
        }

        return Rarity.Common;
    }

    //Adds the item to the list
    //Updates in files are excluded
    private void addNewItem(GameItem item) {
        if (contains(item)) {
            items.get(items.indexOf(item)).quantity++;
            System.out.println("Item added\n" + item);
            return;
        }
        items.add(item);
        System.out.println("Item added\n" + item);
    }
}
