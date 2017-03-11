package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Diagnosa.sikepok_diagnosa;
import com.hi_depok.hi_depok.Sikepok_RS.sikepok_rs;
import com.hi_depok.hi_depok.fragment_sikepok_activity.fragment1;
import com.hi_depok.hi_depok.fragment_sikepok_activity.fragment2;
import com.hi_depok.hi_depok.fragment_sikepok_activity.fragment3;
import com.hi_depok.hi_depok.fragment_sikepok_activity.fragment4;

public class sikepok extends AppCompatActivity implements View.OnClickListener{

    ViewPager pager;
    SikepokPager adapter;
    View strip;
    ImageView hospital,diagnosa,pb, play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sikepok);
        pager = (ViewPager) findViewById(R.id.pager);
        hospital = (ImageView) findViewById(R.id.hospital);
        diagnosa = (ImageView) findViewById(R.id.diagnosa);
        pb = (ImageView) findViewById(R.id.pb);
        play = (ImageView) findViewById(R.id.play);
        strip = findViewById(R.id.strip);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView tv = (TextView) findViewById(R.id.sikepok);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        adapter = new SikepokPager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 4);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 4);
                        break;
                    case 2:
                        strip.setTranslationX(strip.getWidth() * 2 + positionOffsetPixels / 4);
                        break;
                    case 3:
                        strip.setTranslationX(strip.getWidth() * 3 + positionOffsetPixels / 4);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        hospital.setOnClickListener(this);
        diagnosa.setOnClickListener(this);
        pb.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.diagnosa:
                pager.setCurrentItem(0);
                break;
            case R.id.hospital:
                pager.setCurrentItem(1);
                break;
            case R.id.pb:
                pager.setCurrentItem(2);
                break;
            case R.id.play:
                pager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    class SikepokPager extends FragmentPagerAdapter {
        fragment1 fragment1;
        fragment2 fragment2;
        fragment3 fragment3;
        fragment4 fragment4;

        public SikepokPager(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.fragment_sikepok_activity.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.fragment_sikepok_activity.fragment2.newInstance();
            fragment3 = com.hi_depok.hi_depok.fragment_sikepok_activity.fragment3.newInstance();
            fragment4 = com.hi_depok.hi_depok.fragment_sikepok_activity.fragment4.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                case 2:
                    return fragment3;
                case 3:
                    return fragment4;
                default:
                    return fragment1;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    //fragment1 fragment1;
    public void diagnosa(View v){
        Intent intent = new Intent(sikepok.this, sikepok_diagnosa.class);
        startActivity(intent);
    }

    public void rumah_sakit(View v){
        Intent intent = new Intent(sikepok.this, sikepok_rs.class);
        startActivity(intent);
    }
}
