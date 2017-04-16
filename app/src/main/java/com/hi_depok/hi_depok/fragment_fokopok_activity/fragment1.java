package com.hi_depok.hi_depok.fragment_fokopok_activity;

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
        View  v = inflater.inflate(R.layout.fokopok_fragment1_activity, null);
        WebView view = (WebView) v.findViewById(R.id.textContent);
        view.setBackgroundColor(0);
        view.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        WebSettings webSettings = view.getSettings();
        webSettings.setDefaultFontSize(12);
        String text;
        text = "<html>" +
                "<head>" +
                "<style type=\"text/css\">" +
                "@font-face {\n" +
                "        font-family: 'Blogger_Sans';\n" +
                "        src: url('file:///android_asset/font/Blogger_Sans.otf') format('truetype');\n" +
                "        } "+
                ".text { \n" +
                "   font-family: 'Blogger_Sans'; color:#b7b7b7;background-color:transparent;}"+
                "</style>" +
                "</head>" +
                "<body>" +
                "<p align=\"justify\" class=\"text\">";
        text+= "Pengguna dapat bergabung di suatu komunitas, mendapatkan informasi setiap " +
                "komunitas-komunitas dan informasi acara-acara dari komunitas tersebut. Dilengkapi " +
                "dengan fasilitas chatting untuk para anggota komunitasnya.";
        text+= "</p>" +
                "</body>" +
                "</html>";
        view.loadData(text, "text/html", "utf-8");
        
        return v;
    }
}