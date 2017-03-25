package com.hi_depok.hi_depok.Kapok;

/**
 * Created by User on 3/24/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

import java.util.List;



/**
 * Created by User on 3/24/2017.
 */

public class CustomAdapterView extends RecyclerView.Adapter<RecycleViewHolderViewUlasan> {
    private List<ItemObjectViewUlasan> itemlist;
    private Context context;

    public CustomAdapterView(Context context, List<ItemObjectViewUlasan> itemlist){
        this.itemlist = itemlist;
        this.context = context;
    }

    @Override
    public RecycleViewHolderViewUlasan onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kapok_selengkapnya, null);
        RecycleViewHolderViewUlasan vs = new RecycleViewHolderViewUlasan(layoutView);
        return vs;

    }

    @Override
    public void onBindViewHolder(RecycleViewHolderViewUlasan holder, int position) {
        holder.title.setText(itemlist.get(position).getTitle());
        holder.waktu.setText(itemlist.get(position).getTime());
        holder.komentar.setText(itemlist.get(position).getDesc());
        holder.avatar.setImageResource(itemlist.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return this.itemlist.size();
    }
}


