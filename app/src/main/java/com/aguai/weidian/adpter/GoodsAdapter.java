package com.aguai.weidian.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testzjut.R;
import com.rey.material.widget.CheckBox;
import com.weidian.open.sdk.response.product.VdianItemListGetResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 阿怪 on 2014/8/6.
 */
public class GoodsAdapter extends ProgressAdapter<VdianItemListGetResponse.ListItem>{
    private List<VdianItemListGetResponse.ListItem> products;
    private Activity mActivity;

    public GoodsAdapter(Activity activity, List<VdianItemListGetResponse.ListItem> list, RecyclerView target) {
        super(activity,list, target);
        mActivity=activity;
        products=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_adpter_goods, parent, false);
        return new MasonryView(v);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MasonryView masonryView= (MasonryView) holder;
        final VdianItemListGetResponse.ListItem listItem = products.get(position);
        if(listItem.getThumb_imgs().length>0){
            Glide.with(mActivity).load(listItem.getThumb_imgs()[0]).into(masonryView.imageView);
        }
        masonryView.title.setText(listItem.getFinalItemName());
        masonryView.sales.setText(listItem.getSold()+"");
        masonryView.price.setText(listItem.getFinalprice()+"");
        masonryView.inventory.setText(listItem.getFinalstock()+"");
        masonryView.checkBox.setCheckedImmediately(listItem.isSelected());
    }

    public void selectAll(boolean isSelectAll) {
        for(VdianItemListGetResponse.ListItem l:products){
            l.setSelected(isSelectAll);
        }
        notifyDataChanged();
    }

    public  List<VdianItemListGetResponse.ListItem> getSelectedItems() {
        List<VdianItemListGetResponse.ListItem>listItems=new ArrayList<>();
        for(VdianItemListGetResponse.ListItem l:products){
            if (l.isSelected()){
                listItems.add(l);
            }
        }
        return listItems;
    }

    //viewholder
    public  class MasonryView extends  RecyclerView.ViewHolder {
        @Bind(R.id.headpic) CircleImageView imageView;
        @Bind(R.id.title) TextView title;
        @Bind(R.id.sales)TextView sales;
        @Bind(R.id.price)TextView price;
        @Bind(R.id.inventory)TextView inventory;
        @Bind(R.id.checkbox)CheckBox checkBox;
        public MasonryView(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(products.get(getLayoutPosition()).isSelected()){
                        products.get(getLayoutPosition()).setSelected(false);
                        checkBox.setCheckedImmediately(false);
                    }else{
                        products.get(getLayoutPosition()).setSelected(true);
                        checkBox.setCheckedImmediately(true);
                    }
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        products.get(getLayoutPosition()).setSelected(true);
                    }else{
                        products.get(getLayoutPosition()).setSelected(false);
                    }
                }
            });
        }
    }
}