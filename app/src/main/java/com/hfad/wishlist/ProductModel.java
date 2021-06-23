package com.hfad.wishlist;

import java.util.List;

class Store {
    public String store_name;
    public String store_price;
    public String product_url;
    public String currency_code;
    public String currency_symbol;
}

public class ProductModel {
    private String barcode_number;
    private String barcode_type;
    private String barcode_formats;
    private String mpn;
    private String model;
    private String asin;
    private String product_name;
    private String title;
    private String category;
    private String manufacturer;
    private String brand;
    private String label;
    private String author;
    private String publisher;
    private String artist;
    private String actor;
    private String director;
    private String studio;
    private String genre;
    private String audience_rating;
    private String ingredients;
    private String nutrition_facts;
    private String color;
    private String format;
    private String package_quantity;
    private String size;
    private String length;
    private String width;
    private String height;
    private String weight;
    private String release_date;
    private String description;
    private List<Object> features;
    private List<String> images;
    private List<Store> stores;
    private List<Object> reviews;

    public ProductModel(String barcode_number, String barcode_type, String barcode_formats, String mpn, String model, String asin, String product_name, String title, String category, String manufacturer, String brand, String label, String author, String publisher, String artist, String actor, String director, String studio, String genre, String audience_rating, String ingredients, String nutrition_facts, String color, String format, String package_quantity, String size, String length, String width, String height, String weight, String release_date, String description, List<Object> features, List<String> images, List<Store> stores, List<Object> reviews) {
        this.barcode_number = barcode_number;
        this.barcode_type = barcode_type;
        this.barcode_formats = barcode_formats;
        this.mpn = mpn;
        this.model = model;
        this.asin = asin;
        this.product_name = product_name;
        this.title = title;
        this.category = category;
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.label = label;
        this.author = author;
        this.publisher = publisher;
        this.artist = artist;
        this.actor = actor;
        this.director = director;
        this.studio = studio;
        this.genre = genre;
        this.audience_rating = audience_rating;
        this.ingredients = ingredients;
        this.nutrition_facts = nutrition_facts;
        this.color = color;
        this.format = format;
        this.package_quantity = package_quantity;
        this.size = size;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.release_date = release_date;
        this.description = description;
        this.features = features;
        this.images = images;
        this.stores = stores;
        this.reviews = reviews;
    }

    public ProductModel() {
    }

    // Formatting what gets displayed to Text View from API call
    @Override
    public String toString() {

        return  "ItemName: " + product_name + '\n' +
                "Manufacturer: " + manufacturer + '\n' +
                "Barcode: " + barcode_number;
    }

    public String getBarcode_number() {
        return barcode_number;
    }

    public void setBarcode_number(String barcode_number) {
        this.barcode_number = barcode_number;
    }

    public String getBarcode_type() {
        return barcode_type;
    }

    public void setBarcode_type(String barcode_type) {
        this.barcode_type = barcode_type;
    }

    public String getBarcode_formats() {
        return barcode_formats;
    }

    public void setBarcode_formats(String barcode_formats) {
        this.barcode_formats = barcode_formats;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(String audience_rating) {
        this.audience_rating = audience_rating;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getNutrition_facts() {
        return nutrition_facts;
    }

    public void setNutrition_facts(String nutrition_facts) {
        this.nutrition_facts = nutrition_facts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPackage_quantity() {
        return package_quantity;
    }

    public void setPackage_quantity(String package_quantity) {
        this.package_quantity = package_quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}