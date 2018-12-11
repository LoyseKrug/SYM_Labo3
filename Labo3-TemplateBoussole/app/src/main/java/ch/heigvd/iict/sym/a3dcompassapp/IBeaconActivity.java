package ch.heigvd.iict.sym.a3dcompassapp;


import android.Manifest;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import org.altbeacon.beacon.*;

import java.util.Collection;
import java.util.List;

public class IBeaconActivity extends AppCompatActivity implements BeaconConsumer {

    private TextView iBeacons = null;
    private BeaconManager beaconManager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET,
                        Manifest.permission.BLUETOOTH
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();

        setContentView(R.layout.activity_ibeacon);
        iBeacons = (TextView) findViewById(R.id.beaconsList);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {

        beaconManager.addRangeNotifier(new RangeNotifier() {

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                iBeacons.setText("");
                int iBeaconNumber = 0;
                for(Beacon b : collection){
                    String beaconInfo = "Beacon no " + iBeaconNumber++ +
                            "Beacon id: " + b.getId1().toString() +
                            "RSSI: " + b.getRssi() +
                            "\nMajor number: " + b.getId2().toString() +
                            "\nMinor number: " + b.getId3().toString() + "\n\n";

                    iBeacons.setText(iBeacons.getText() + beaconInfo);
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {

        }


    }
}
