package com.bwie.moni_gouwuche.apdter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.moni_gouwuche.R;

class MyViewHoder  extends RecyclerView.ViewHolder {

    public ImageView ivShopCartShopSel;
    public TextView tvShopCartShopName;
    public TextView tvShopCartClothName;
    public TextView tvShopCartClothPrice;
    public TextView etShopCartClothNum;
    public TextView tvShopCartClothColor;
    public TextView tvShopCartClothSize;
    public ImageView ivShopCartClothSel;
    public ImageView ivShopCartClothMinus;
    public ImageView ivShopCartClothAdd;
    public ImageView ivShopCartClothDelete;
    public ImageView ivShopCartClothPic;
    public LinearLayout llShopCartHeader;


    public MyViewHoder(View itemView) {
        super(itemView);

        llShopCartHeader = itemView.findViewById(R.id.ll_shopcart_header);
        ivShopCartShopSel = itemView.findViewById(R.id.iv_shopcart_shposelecd);
        tvShopCartShopName = itemView.findViewById(R.id.shopcart_shopname);
        tvShopCartClothName = itemView.findViewById(R.id.tv_item_shopcart_clothname);
        tvShopCartClothPrice = itemView.findViewById(R.id.tv_item_shopcart_cloth_price);
        etShopCartClothNum = itemView.findViewById(R.id.et_item_shopcart_cloth_num);
        tvShopCartClothColor = itemView.findViewById(R.id.tv_item_shopcart_cloth_color);
        tvShopCartClothSize = itemView.findViewById(R.id.tv_item_shopcart_cloth_size);
        ivShopCartClothSel = itemView.findViewById(R.id.tv_item_shopcart_clothselect);
        ivShopCartClothMinus = itemView.findViewById(R.id.iv_item_shopcart_cloth_minus);
        ivShopCartClothAdd = itemView.findViewById(R.id.iv_item_shopcart_cloth_add);
        ivShopCartClothPic = itemView.findViewById(R.id.iv_item_shopcart_cloth_pic);
        //ivShopCartClothDelete = itemView.findViewById(R.id.iv_item_shopcart_cloth_delete);

    }
}
