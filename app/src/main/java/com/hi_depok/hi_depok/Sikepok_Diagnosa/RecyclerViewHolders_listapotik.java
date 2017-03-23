package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.detail_danus;

/**
 * Created by User on 18/03/17.
 */

public class RecyclerViewHolders_listapotik extends RecyclerView.ViewHolder {
    public TextView list_title;
    public TextView list_desc;
    public ImageView list_avatar;

    public RecyclerViewHolders_listapotik(View itemView) {
        super(itemView);

        list_title = (TextView)itemView.findViewById(R.id.list_title);
        list_desc = (TextView)itemView.findViewById(R.id.list_desc);
        list_avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Apotik1.class);
                //intent.putExtra(detail.EXTRA_POSITION, getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }
}