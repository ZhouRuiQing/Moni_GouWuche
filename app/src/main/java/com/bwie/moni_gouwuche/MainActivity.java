package com.bwie.moni_gouwuche;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.moni_gouwuche.apdter.Myapdater;
import com.bwie.moni_gouwuche.bean.ShopCartBean;
import com.bwie.moni_gouwuche.event.OnResfreshListener;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_shopcartselect,tv_shopcarttotalprice,tv_shopcartlnum,tv_shopcartsubmit;
    private RecyclerView recylerView;
    private List<ShopCartBean.CartlistBean> list=new ArrayList<>();
    private Myapdater apdater;
    private boolean mSelect;
    private float mTotalPrice1;
    private ArrayList<ShopCartBean.CartlistBean> mGoPayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initData();
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        apdater = new Myapdater(this,list);
        recylerView.setAdapter(apdater);
        apdater.notifyDataSetChanged();
        //实时全选按钮监控
        apdater.setOnResfreshListener(new OnResfreshListener() {
            @Override
            public void onResfresh(boolean isSelect) {
                mSelect = isSelect;
                if (isSelect) {
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_selected);
                    tv_shopcartselect.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                } else {
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_unselected);
                    tv_shopcartselect.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                }
                float mTotalPrice = 0;
                int mTotalNum = 0;
                mTotalPrice1 = 0;
                mGoPayList.clear();
                for (int i = 0; i < list.size(); i++)
                    if (list.get(i).getIsSelect()) {
                        mTotalPrice += Float.parseFloat(list.get(i).getPrice()) * list.get(i).getCount();
                        mTotalNum += 1;
                        mGoPayList.add(list.get(i));
                    }
                mTotalPrice1 = mTotalPrice;
                tv_shopcarttotalprice.setText("总价：" + mTotalPrice);
                tv_shopcartlnum.setText("共" + mTotalNum + "件商品");
            }
        });

        //全选
        tv_shopcartselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelect=!mSelect;
                if(mSelect){
                    //全选
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_selected);
                    tv_shopcartselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                    for (int i=0;i<list.size();i++){

                        list.get(i).setSelect(true);
                        list.get(i).setShopSelect(true);
                    }
                }else{
                    //全不选
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_unselected);
                    tv_shopcartselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);

                    for (int i =0;i<list.size();i++){

                        list.get(i).setSelect(false);
                        list.get(i).setShopSelect(false);
                    }
                }

                apdater.notifyDataSetChanged();
            }
        });

    }

    private void init() {
        tv_shopcarttotalprice = findViewById(R.id.tv_shopcart_totalprice);
        tv_shopcartselect     = findViewById(R.id.tv_shopcart_selected);
        tv_shopcartlnum       = findViewById(R.id.tv_shopcart_totalnum);
        tv_shopcartsubmit     = findViewById(R.id.tv_shopcart_sbumit);
        recylerView           = findViewById(R.id.recylerView);
    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            ShopCartBean.CartlistBean sb = new ShopCartBean.CartlistBean();
            sb.setShopId(1);
            sb.setPrice("1999.0");
            sb.setDefaultPic("http://img2.3lian.com/2014/c7/25/d/40.jpg");
            sb.setProductName("小米MIX手机");
            sb.setShopName("小米旗舰店");
            sb.setColor("玫瑰金");
            sb.setCount(1);
            list.add(sb);
        }

        for (int i = 0; i < 2; i++) {
            ShopCartBean.CartlistBean sb = new ShopCartBean.CartlistBean();
            sb.setShopId(2);
            sb.setPrice("2999.0");
            sb.setDefaultPic("http://img2.3lian.com/2014/c7/25/d/40.jpg");
            sb.setProductName("三星note6");
            sb.setShopName("三星旗舰店");
            sb.setColor("玫瑰金");
            sb.setCount(1);
            list.add(sb);
        }
        isSelectFirst(list);
    }

    /**
     * 判断是否是商品的第一个商品 是第一个商品 需要显示商铺
     *
     * @author zhaoliang
     * @version 1.0
     * @create 2018/9/17
     */
    public static void isSelectFirst(List<ShopCartBean.CartlistBean> list) {
        // 1. 判断是否有商品 有商品 根据商品是否是第一个显示商铺
        if (list.size() > 0) {
            //头个商品一定属于它所在商铺的第一个位置，isFirst标记为1.
            list.get(0).setFirst(true);
            for (int i = 1; i < list.size(); i++) {
                //每个商品跟它前一个商品比较，如果Shopid相同isFirst则标记为2，
                //如果Shopid不同，isFirst标记为1.
                if (list.get(i).getShopId() == list.get(i - 1).getShopId()) {
                    list.get(i).setFirst(false);
                } else {
                    list.get(i).setFirst(true);
                }
            }
        }
    }

}
