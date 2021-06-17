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

    /*// Get Set ItemName
    public String getItemName(){
        return ItemName;
    }
    public void setItemName(String NewItemName){
        this.ItemName = NewItemName;
    }

    // Get Set Manufacturer
    public String getManufacturer(){
        return Manufacturer;
    }

    public void setManufacturer(String NewManufacturer){
        this.Manufacturer = NewManufacturer;
    }

    // Get Set Price
    public Double getPrice(){
        return Price;
    }

    public void setPrice(Double NewPrice){
        this.Price = NewPrice;
    }

    // Get Set Barcode
    public String getBarcode(){
        return Barcode;
    }

    public void setBarcode(String NewBarcode){
        this.Barcode = NewBarcode;
    }*/
}
