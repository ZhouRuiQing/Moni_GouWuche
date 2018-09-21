package com.bwie.moni_gouwuche.apdter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.moni_gouwuche.MainActivity;
import com.bwie.moni_gouwuche.R;
import com.bwie.moni_gouwuche.bean.ShopCartBean;
import com.bwie.moni_gouwuche.event.OnResfreshListener;

import java.util.List;

public class Myapdater extends RecyclerView.Adapter<MyViewHoder> {
    Context context;
    List<ShopCartBean.CartlistBean> list;

    public Myapdater(Context context, List<ShopCartBean.CartlistBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MyViewHoder hoder = new MyViewHoder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
        return hoder;
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, final int position) {

        //商品图片
        Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537181886761&di=576079fc797f5c11e552783c84a49b6c&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120702%2F2129594_171228709393_2.jpg").into(holder.ivShopCartClothPic);

        holder.tvShopCartClothColor.setText("颜色"+list.get(position).getColor());
        holder.tvShopCartClothSize.setText("尺寸"+list.get(position).getSize());
        holder.tvShopCartClothName.setText(list.get(position).getProductName());
        holder.tvShopCartClothPrice.setText(list.get(position).getPrice());
        holder.etShopCartClothNum.setText(list.get(position).getCount()+"");
        holder.tvShopCartShopName.setText(list.get(position).getShopName());

        //显示前面的选中状态
        if(list.get(position).getIsSelect()){
            holder.ivShopCartClothSel.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_selected));

        }else{
            holder.ivShopCartClothSel.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_unselected));
        }

        if(list.get(position).getIsShopSelect()){
            holder.ivShopCartShopSel.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_selected));
        }else {
            holder.ivShopCartShopSel.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_unselected));
        }

        /* 判断是否显示商铺 */
        if (position > 0) {
            /* 判断是否是同一个商铺的商品 */
            if (list.get(position).getShopId() == list.get(position - 1).getShopId()) {
                holder.llShopCartHeader.setVisibility(View.GONE);
            } else {
                holder.llShopCartHeader.setVisibility(View.VISIBLE);
            }
        } else {
            holder.llShopCartHeader.setVisibility(View.VISIBLE);
        }


        //* 判断是否全选并计算 *//*
        if (onResfreshListener != null) {
            boolean isSelect = false;
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getIsSelect()) {
                    isSelect = false;
                    break;
                } else {
                    isSelect = true;
                }
            }
            onResfreshListener.onResfresh(isSelect);
        }

        //商品加
        holder.ivShopCartClothAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.get(position).setCount(list.get(position).getCount()+1);
                notifyDataSetChanged();
            }
        });

        //商品减
        holder.ivShopCartClothMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.get(position).getCount()>1){

                    list.get(position).setCount(list.get(position).getCount()-1);
                    notifyDataSetChanged();
                }

            }
        });

        //单个商品选中状态
        holder.ivShopCartClothSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setSelect(!list.get(position).getIsSelect());
                //通过循环找出不同的第一个商品的位置
                for (int i=0;i<list.size();i++){
                    if(list.get(i).isFirst()){

                        //遍历去找出同一家商铺的所有商品的勾选情况
                        for (int j =0;j<list.size();j++){

                            //如果是同一家商铺的商品，并且其中一个商品是未选中，那么商铺的全选勾选取消
                            if(list.get(j).getShopId()==list.get(i).getShopId() && !list.get(j).getIsSelect()){
                                list.get(i).setShopSelect(false);
                                break;
                            }else {
                                list.get(i).setShopSelect(true);
                            }
                        }

                    }
                }
                notifyDataSetChanged();
            }
        });

        /* 商铺选中状态 */
        holder.ivShopCartShopSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).isFirst()){

                    list.get(position).setShopSelect(!list.get(position).getIsShopSelect());
                    for (int i=0;i<list.size();i++){

                        if(list.get(i).getShopId()==list.get(position).getShopId()){
                            list.get(i).setSelect(list.get(position).getIsShopSelect());
                        }
                    }

                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return list==null ? 0 :list.size();
    }

    //刷新的接口

   private OnResfreshListener onResfreshListener;

    public void setOnResfreshListener(OnResfreshListener onResfreshListener){
        this.onResfreshListener = onResfreshListener;
    }
}
