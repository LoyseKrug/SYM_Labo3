/**
 * Authors: Adrien Allemand, James Smith, Loyse Krug
 *
 * Date: 16.12.2018
 *
 * Objectives:  This activity shows the list of all the beacons active close to the phone. The list is regularly
 *              updated
 *
 *
 * Sources:
 *          Permission requirment: https://github.com/Karumi/Dexter
 *          Bluetooth activation: https://developer.android.com/guide/topics/connectivity/bluetooth#java
 *          IBeacon example: https://altbeacon.github.io/android-beacon-library/samples.html
 */

package ch.heigvd.iict.sym.a3dcompassapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import android.widget.Toast;
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

        setContentView(R.layout.activity_ibeacon);

        //TextView to display all the beacons in the region
        iBeacons = (TextView) findViewById(R.id.beaconsList);
        //As the list of beacons can be longer than the text view, this field must be scrollable
        iBeacons.setMovementMethod(new ScrollingMovementMethod());

        //Permissions are required to be able to detect the beacons.
        //- Localisation
        //- Internet
        //- Bluetooth
        //We use the Dexter library to require those permissions
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET,
                        Manifest.permission.BLUETOOTH
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    //if all the  are granted an toast is displayed to indicate it to the user
                    Toast.makeText(IBeaconActivity.this, R.string.permission_granted, Toast.LENGTH_LONG).show();
                    // and we ask the user for the bluetooth activation
                    if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, 1);
                    }
                    //Only when all the permissions are granted and Bluetooth is activated, we can bind the beaconManager
                    //to the activity
                    beaconManager = BeaconManager.getInstanceForApplication(IBeaconActivity.this);
                    beaconManager.getBeaconParsers().add(new BeaconParser().
                            setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
                    beaconManager.bind(IBeaconActivity.this);
                }else{
                    Toast.makeText(IBeaconActivity.this, R.string.permission_denied, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).onSameThread().check();

    }

    @Override
    public void onBeaconServiceConnect() {
        //We add a range notifier which will check regularly check for the ibeacons around it (in the region)
        beaconManager.addRangeNotifier(new RangeNotifier() {

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                //The list of beacons is cleared to avoir multiple diyplay of a same beacon
                iBeacons.setText("");

                //for each beacon in the list of beacons in the region, we display their id , rssi, major number and
                // minor number
                for(Beacon b : collection){
                    String beaconInfo =
                            "Beacon id: " + b.getId1().toString() +
                            "\nRSSI: " + b.getRssi() +
                            "\nMajor number: " + b.getId2().toString() +
                            "\nMinor number: " + b.getId3().toString() + "\n\n";

                    iBeacons.setText(iBeacons.getText() + beaconInfo);
                }
            }
        });

        try {
            //Once the actions when a beacon List is recieved are defined, we can the process of searching for them
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
