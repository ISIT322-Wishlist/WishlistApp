package com.hfad.wishlist;

import java.io.Serializable;

public class Item implements Serializable {
    public String ItemName;
    public String Manufacturer;
    public String Price;
    public String Barcode;

    @Override
    public String toString() {

        return  "ItemName: " + ItemName + '\n' +
                "Manufacturer: " + Manufacturer + '\n' +
                "Price: " + Price + '\n' +
                "Barcode: " + Barcode;
    }

    public Item(String sItem, String sManufacturer, String sPrice, String sBarcode) {
        ItemName = sItem;
        Manufacturer = sManufacturer;
        Price = sPrice;
        Barcode = sBarcode;
    }

    public Item(){};

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }
}
