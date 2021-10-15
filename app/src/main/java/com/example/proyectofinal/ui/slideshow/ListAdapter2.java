package com.example.proyectofinal.ui.slideshow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.ui.home.ListElement;

import java.util.List;

public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.ViewHolder> {
    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final OnItemClickListener listener;

    public interface OnItemClickListener{
        //detectar cuando se hace click
        void onItemClick(ListElement item); //coon item registramos los datos que hagan click
    }

    public ListAdapter2(List<ListElement> itemList, Context context, OnItemClickListener listener){
//pasamos listener
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener; //cuando hagamos click

    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = mInflater.from(parent.getContext()).inflate(R.layout.list_element3, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.bindData(mData.get(position));

    }

    public void setItems(List<ListElement> items ){ mData = items;}


    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name, city, status;
        CardView cv;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            city = itemView.findViewById(R.id.cityTextView);
            status = itemView.findViewById(R.id.statusTextView);
            cv = itemView.findViewById(R.id.cv);

        }

        void bindData(final ListElement item){

            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            city.setText(item.getCity());
            status.setText(item.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item); //metodo declarado donde pasamos item

                }
            });

        }
    }
}
