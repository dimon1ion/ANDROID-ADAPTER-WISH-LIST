package com.my.mw_wish_list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.mw_wish_list.R;
import com.my.mw_wish_list.models.Wish;

import java.text.NumberFormat;
import java.util.List;

public class WishAdapter extends BaseAdapter {

    private Context context;
    private int templateLayout;
    private List<Wish> wishList;
    private LayoutInflater layoutInflater;
    private MyEventTotalPrice event;

    public WishAdapter(Context context, int templateLayout, List<Wish> wishList, MyEventTotalPrice event) {
        this.context = context;
        this.templateLayout = templateLayout;
        this.wishList = wishList;
        this.event = event;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return wishList.size();
    }

    @Override
    public Object getItem(int position) {
        return wishList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = layoutInflater.inflate(templateLayout, parent, false);
        TextView name = rowView.findViewById(R.id.text_name);
        TextView info = rowView.findViewById(R.id.text_info);
        TextView price = rowView.findViewById(R.id.text_price);
        ImageView avatar = rowView.findViewById(R.id.image_avatar);
        CheckBox checkWish = rowView.findViewById(R.id.check_wish);

        Wish wish = wishList.get(position);
        name.setText(wish.getName());
        info.setText(wish.getInfo());
        price.setText(wish.getPrice() + " " + wish.getPriceCurrencyName());
        avatar.setImageResource(wish.getImageId());
        checkWish.setChecked(wish.isSelected());

        checkWish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                event.run(position, isChecked);
            }
        });

        return rowView;
    }
}
