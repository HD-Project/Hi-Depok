package com.hi_depok.hi_depok.Sikepok_Panic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.List;
import java.util.Locale;
import com.hi_depok.hi_depok.R;


public class PanicActivity extends AppCompatActivity implements LocationListener{
    int clickcount = 0;
    TextView notice1;
    TextView latlon;
    TextView address;
    ProgressDialog dialog;
    LocationManager locationManager;
    double getlatitude;
    double getlongitude;
    private Toolbar toolbar;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    protected static final String TAG = "PanicActivity";
    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panicbutton);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_more) {
            Intent intent = new Intent(PanicActivity.this, PilihanLainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_about) {
            Intent intent = new Intent(PanicActivity.this, MenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void mapsClicked (View view) {
        Intent intent = new Intent(PanicActivity.this, MapsActivity.class);
        startActivity(intent);
    }
    public void panicbuttonClicked(View view) {
        clickcount = clickcount + 1;
        if (clickcount == 1) {
            //first time clicked to do this
            //Toast.makeText(getApplicationContext(),"Tekan 2 kali lagi!", Toast.LENGTH_SHORT).show();
            Snackbar.make(findViewById(android.R.id.content), "Kamu menekan 1 kali..", Snackbar.LENGTH_INDEFINITE).show();
        } else if (clickcount == 2) {
            //check how many times clicked and so on
            //Toast.makeText(getApplicationContext(),"Tekan 1 kali lagi!", Toast.LENGTH_SHORT).show();
            Snackbar.make(findViewById(android.R.id.content), "Kamu menekan 2 kali..", Snackbar.LENGTH_INDEFINITE).show();
        } else {
            clickcount = 0;
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Kamu sedang dalam bahaya, apa kamu ingin memesan ambulan?", Snackbar.LENGTH_INDEFINITE)
                    .setAction("YA", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showMessageDialog();
                        }

                    });
            snackbar.show();
        }
    }

    private void orderAmbulance(){
        dialog = new ProgressDialog(PanicActivity.this);
        dialog.show();
        dialog.setMessage("Sistem sedang mencari ambulan terdekat dari tempat anda, mohon bersabar :)");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Lokasi anda belum di hidupkan!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("HIDUPKAN", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            displayLocationSettingsRequest(getBaseContext());
                        }

                    });
            snackbar.show();
            return;
        }
        if (locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 30000,
                    1, this);
        } else if (locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 30000,
                    1, this);
        }
        else {
            dialog.dismiss();

            //Toast.makeText(getApplicationContext(), "Enable Your Location First!", Toast.LENGTH_LONG).show();
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Hidupkan Lokasi!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("HIDUPKAN", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            displayLocationSettingsRequest(getBaseContext());
                        }

                    });
            snackbar.show();
        }
    }
    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(PanicActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }
    public void settingRequest()
    {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(PanicActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }
    @Override
    public void onLocationChanged(Location location) {
        notice1 = (TextView)findViewById(R.id.txtNotice1);
        latlon = (TextView)findViewById(R.id.latlong);
        address = (TextView)findViewById(R.id.address);
        getlatitude = location.getLatitude();
        getlongitude = location.getLongitude();
        if (getlatitude != 0 && getlongitude != 0){
            notice1.setText("Location Detail: ");
            findViewById(R.id.txtNotice2).setVisibility(View.INVISIBLE);
            findViewById(R.id.layoutDirections).setVisibility(View.VISIBLE);
            try {

                Geocoder geo = new Geocoder(this.getApplicationContext(), Locale.getDefault());
                List<Address> addresses = geo.getFromLocation(-6.367649, 106.819693, 1);
                if (addresses.isEmpty()) {
                    address.setText("Waiting for Location");
                }
                else {
                    if (addresses.size() > 0) {
                        latlon.setText("Lokasi Ambulan: \n" + addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                        //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace(); // getFromLocation() may sometimes fail
            }
            try {

                Geocoder geo = new Geocoder(this.getApplicationContext(), Locale.getDefault());
                List<Address> addresses = geo.getFromLocation(getlatitude, getlongitude, 1);
                if (addresses.isEmpty()) {
                    address.setText("Waiting for Location");
                }
                else {
                    if (addresses.size() > 0) {
                        address.setText("Lokasi Anda: \n" + addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                        //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace(); // getFromLocation() may sometimes fail
            }
            dialog.dismiss();
            Snackbar.make(findViewById(android.R.id.content), "Selamat! kami menemukan ambulan yang tersedia..", Snackbar.LENGTH_LONG).show();

        }
    }
    private void showMessageDialog (){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.message_dialog, null);
        final EditText formMessage = (EditText) alertLayout.findViewById(R.id.form_message);

        AlertDialog.Builder alert = new AlertDialog.Builder(PanicActivity.this);
        alert.setTitle("Pesan Tambahan (optional)");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                orderAmbulance();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}