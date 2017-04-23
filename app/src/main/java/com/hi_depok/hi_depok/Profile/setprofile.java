package com.hi_depok.hi_depok.Profile;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

public class setprofile extends BaseActivity {

    TextView changepass;
    EditText etBio, etNama, etEmail, etNomer, etAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_profile);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etBio = (EditText) findViewById(R.id.setBio);
        etNama = (EditText) findViewById(R.id.setNama);
        etEmail = (EditText) findViewById(R.id.setEmail);
        etNomer = (EditText) findViewById(R.id.setNomer);
        etAlamat = (EditText) findViewById(R.id.setAlamat);
        btnSimpan = (Button) findViewById(R.id.btnSimpanProfil);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bio = etBio.getText().toString();
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String no_telp = etNomer.getText().toString();
            }
        });

        changepass = (TextView) findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setprofile.this, changepass.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                setprofile.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}