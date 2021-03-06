package com.hi_depok.hi_depok.fragment_kadepok_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/4/2017.
 */

public class fragment1 extends Fragment{

    public static fragment1 newInstance() {

        Bundle args = new Bundle();

        fragment1 fragment = new fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.kadepok_fragment1_activity, null);
        WebView view = (WebView) v.findViewById(R.id.textContent);
        view.setBackgroundColor(0);
        view.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        WebSettings webSettings = view.getSettings();
        webSettings.setDefaultFontSize(12);
        String text;
        text = "<html>" +
                "<head>" +
                "<style type=\"text/css\"> .text{color:#b7b7b7;background-color:transparent;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<p align=\"justify\" class=\"text\">";
        text+= "Pengguna dapat melihat detail informasi seluruh panti asuhan yang berada di Kota " +
                "Depok baik dari data anak hingga kondisi terkini panti asuhan yang telah " +
                "dikelompokkan berdasarkan kecamatan serta dapat juga melakukan donasi kepada panti " +
                "asuhan tersebut";
        text+= "</p>" +
                "</body>" +
                "</html>";
        view.loadData(text, "text/html", "utf-8");
        return v;
    }
}
