package com.hi_depok.hi_depok.Kadepok_Cherish;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hi_depok.hi_depok.R;

public class kadepok_cherish_detail_anak extends AppCompatActivity {
    ViewPager viewPager;
    kadepok_cherish_detail_anak_custom adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_cherish_detail_anak);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        adapter = new kadepok_cherish_detail_anak_custom(this);
        viewPager.setAdapter(adapter);
    }
}