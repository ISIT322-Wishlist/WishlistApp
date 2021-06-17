package com.hfad.wishlist;

public class Item {
    public String ItemName;
    public String Manufacturer = null;
    public String Price = null;
    public String Barcode = null;

    public Item(String sItem, String sManufacturer, String sPrice, String sBarcode) {
        ItemName = sItem;
        Manufacturer = sManufacturer;
        Price = sPrice;
        Barcode = sBarcode;
    }
}
