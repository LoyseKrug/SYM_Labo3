package ch.heigvd.iict.sym.a3dcompassapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import ch.heigvd.iict.sym.a3dcompassapp.Services.IBeacon;

public class IBeaconActivity extends AppCompatActivity {

    private IBeacon iBeaconManager = null;
    private TextView iBeacons = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibeacon);
        iBeacons = (TextView) findViewById(R.id.beaconsList);
        iBeaconManager = new IBeacon(iBeacons);
    }
}
