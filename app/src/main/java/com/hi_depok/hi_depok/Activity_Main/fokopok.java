package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.fokopok.fokopok_content;

public class fokopok extends BaseActivity implements View.OnClickListener {

    ViewPager pager;
    FokopokPager adapter;
    View strip;
    ImageView komunitas, play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fokopok);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pager = (ViewPager) findViewById(R.id.pager);
        komunitas = (ImageView) findViewById(R.id.komunitas);
        play = (ImageView) findViewById(R.id.play);
        strip = findViewById(R.id.strip);

        TextView tv = (TextView) findViewById(R.id.fokopok);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        adapter = new FokopokPager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 2);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 2);
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

        komunitas.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.komunitas:
                pager.setCurrentItem(0);
                break;
            case R.id.play:
                pager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    class FokopokPager extends FragmentPagerAdapter {
        com.hi_depok.hi_depok.fragment_fokopok_activity.fragment1 fragment1;
        com.hi_depok.hi_depok.fragment_fokopok_activity.fragment2 fragment2;

        public FokopokPager(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.fragment_fokopok_activity.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.fragment_fokopok_activity.fragment2.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                default:
                    return fragment1;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
    public void linkcontent(View v){
        Intent intent = new Intent(fokopok.this, fokopok_content.class);
        startActivity(intent);
    }
}