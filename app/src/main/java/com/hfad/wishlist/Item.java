package com.hfad.wishlist;

public class Item {
    public String itemName;

    public static final Item[] items = {
            new Item("Skis"),
            new Item("Golf Clubs"),
            new Item("Mountain Bike"),
            new Item("Handlebars"),
            new Item("Wax")
    };

    public Item(String itemName){
        this.itemName = itemName;
    }

    public String getItemName(){
        return itemName;
    }
}
