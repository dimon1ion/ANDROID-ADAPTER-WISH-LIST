package com.my.mw_wish_list.models;

public enum PriceType {
    USD(1), EUR(0.91f), AZN(1.7f);

    private float USDCourse;

    PriceType(float USDCourse) {
        this.USDCourse = USDCourse;
    }

    public float getPrice(float usdPrice){
        return usdPrice * USDCourse;
    }
}
