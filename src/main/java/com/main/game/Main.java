package com.main.game;

import com.main.game.item.GameItem;
import com.main.game.itemManager.GameItemManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE = "items.json";

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        GameItem item = GameItem.getInstance();
        List<GameItem> inventory;
        GameItemManager manager = new GameItemManager();
        int op;
        do {
            inventory = item.getItems();
//            manager.loadFromFile(FILE);
            //Command line interface to work with inventory
            System.out.println("1. Add new item to inventory");
            System.out.println("2. Upgrade an item");
            System.out.println("3. Display inventory");
            System.out.println("4. Exit");
            System.out.print("Enter an option: ");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    item.addItem();
                    break;
                case 2:
                    int ind = 1;
                    for (GameItem it: inventory) {
                        System.out.println(ind + ". " + it.getRarity() + " "+ it.getName() + " : " + it.getQuantity());
                        ind++;
                    }
                    System.out.print("Enter the index of the item to upgrade: ");
                    ind = sc.nextInt();
                    if (ind > inventory.size() || ind < 1) {
                        System.out.println("Invalid index");
                        break;
                    }
                    item.upgradeItem(inventory.get(ind - 1));
                    break;
                case 3:
                    if (inventory.isEmpty()) {
                        System.out.println("No items in inventory");
                    }
                    for (GameItem it: inventory) {
                        System.out.println(it);
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            manager.saveToFile(FILE);
        } while (true);
    }
}