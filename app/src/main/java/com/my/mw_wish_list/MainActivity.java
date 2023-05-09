package com.my.mw_wish_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.my.mw_wish_list.adapters.WishAdapter;
import com.my.mw_wish_list.models.PriceType;
import com.my.mw_wish_list.models.Wish;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView totalSum;
    TextView currency;
    ListView wishListView;
    RadioGroup walletGroup;
    List<Wish> wishList;
    PriceType priceType;
    WishAdapter wishAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        totalSum = findViewById(R.id.totalNum_text);
        currency = findViewById(R.id.totalCurrency_text);
        wishListView = findViewById(R.id.listView_wishList);
        walletGroup = findViewById(R.id.radio_group);
        priceType = PriceType.USD;
        currency.setText(priceType.name());

        walletGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_usd){
                    if (priceType != PriceType.USD){
                        setCurrency(PriceType.USD);
                    }
                }
                else if(checkedId == R.id.radio_eur){
                    if (priceType != PriceType.EUR){
                        setCurrency(PriceType.EUR);
                    }
                }
                else if(checkedId == R.id.radio_azn){
                    if (priceType != PriceType.AZN){
                        setCurrency(PriceType.AZN);
                    }
                }
            }
        });

        wishList = new ArrayList<>();
        wishList.add(new Wish("Sleep",
                "The best thing in this world!",
                10000,
                R.drawable.baseline_airline_seat_individual_suite_24));
        wishList.add(new Wish("Ferrari Truck",
                "Very fast and strong!",
                2500,
                R.drawable.baseline_agriculture_24));
        wishList.add(new Wish("Xiaomi Iphone XXL+",
                "New smartphone!",
                1999,
                R.drawable.baseline_android_24));
        wishList.add(new Wish("Happiness",
                "The most valuable and rare feeling",
                55555,
                R.drawable.baseline_add_reaction_24));
        wishList.add(new Wish("100 Dollars",
                "It's a hundred dollars!",
                200,
                R.drawable.baseline_attach_money_24));

        wishAdapter = new WishAdapter(this, R.layout.wish_item, wishList, (position, isChecked) -> {
            Wish wish = wishList.get(position);
            wish.setSelected(isChecked);
            float price = wish.getPrice();
            float totalPrice;
            if (isChecked){
                totalPrice = Float.parseFloat(totalSum.getText().toString()) + price;
            }
            else{
                totalPrice = Float.parseFloat(totalSum.getText().toString()) - price;
            }
            totalSum.setText(Float.toString(totalPrice));
        });
        wishListView.setAdapter(wishAdapter);

    }

    public void setCurrency(PriceType price){
        priceType = price;
        float sum = 0;
        currency.setText(priceType.name());
        wishList.stream().forEach(wish -> {
            wish.setPrice(priceType);
        });
        sum = (float)wishList.stream().mapToDouble(wish -> {
            if (wish.isSelected()){
                return wish.getPrice();
            }
            return 0;
        }).sum();
        totalSum.setText(Float.toString(sum));
        wishAdapter.notifyDataSetChanged();
    }
}