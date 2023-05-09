package com.my.mw_wish_list.models;

public class Wish {
    private String name;
    private String info;
    private float defaultPriceUSD;
    private PriceType price;
    private int imageId;
    private boolean selected;

    public Wish() {
    }

    public Wish(String name, String info, float defaultPriceUSD, int imageId) {
        this(name, info, defaultPriceUSD, imageId, PriceType.USD);
    }

    public Wish(String name, String info, float defaultPriceUSD, int imageId, PriceType price) {
        this(name, info, defaultPriceUSD, imageId, price, false);
    }

    public Wish(String name, String info, float defaultPriceUSD, int imageId, PriceType price, boolean selected) {
        this.name = name;
        this.info = info;
        this.defaultPriceUSD = defaultPriceUSD;
        this.price = price;
        this.imageId = imageId;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getDefaultPriceUSD() {
        return defaultPriceUSD;
    }

    public void setDefaultPriceUSD(float defaultPriceUSD) {
        this.defaultPriceUSD = defaultPriceUSD;
    }

    public String getPriceCurrencyName(){
        return price.name();
    }

    public float getPrice() {
        return price.getPrice(defaultPriceUSD);
    }

    public void setPrice(PriceType priceType) {
        this.price = priceType;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
