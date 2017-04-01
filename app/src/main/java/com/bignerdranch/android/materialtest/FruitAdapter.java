package com.bignerdranch.android.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lfs-ios on 2017/4/1.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitHolder> {
    private Context mContext;

    //水果列表
    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    public class FruitHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private ImageView mImageView;
        private TextView mFruitNameView;

        public FruitHolder(View itemView) {
            super(itemView);

            mCardView = (CardView)itemView;

            mImageView = (ImageView)itemView.findViewById(R.id.fruit_image);

            mFruitNameView = (TextView)itemView.findViewById(R.id.fruit_name);
        }
    }

    @Override
    public FruitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);

        final FruitHolder holder = new FruitHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                Fruit fruit = mFruitList.get(position);

                Intent intent = new Intent(mContext, FruitActivity.class);

                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(FruitHolder holder, int position) {

        Fruit fruit = mFruitList.get(position);
        holder.mFruitNameView.setText(fruit.getName());
        //加载图片到imageview
        Glide.with(mContext).load(fruit.getImageId()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
