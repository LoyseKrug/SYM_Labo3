package ch.heigvd.iict.sym.a3dcompassapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.altbeacon.beacon.*;

import java.util.Collection;

public class IBeaconActivity extends AppCompatActivity implements BeaconConsumer {

    private TextView iBeacons = null;
    private BeaconManager beaconManager = null;
    private BeaconParser parser = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ibeacon);
        iBeacons = (TextView) findViewById(R.id.beaconsList);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        parser = new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                //iBeacons.setText("");
                int iBeaconNumber = 0;
                for(Beacon b : collection){
                    String beaconInfo = "Beacon no " + iBeaconNumber +
                            "RSSI: " + b.getRssi() +
                            "\nMajor: " + b.getId2().toString() +
                            "\nMinor: " + b.getId3().toString() + "\n\n";

                    iBeacons.setText(iBeacons.getText() + beaconInfo );
                }
            }
        });
    }
}
