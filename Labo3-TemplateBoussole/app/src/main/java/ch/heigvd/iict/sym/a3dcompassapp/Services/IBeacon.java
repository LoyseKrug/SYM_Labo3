/**
 * Authors : Adrien Allemand, James Smith, Loyse Krug
 *
 * Source: https://altbeacon.github.io/android-beacon-library/samples.html
 */

package ch.heigvd.iict.sym.a3dcompassapp.Services;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.widget.TextView;
import org.altbeacon.beacon.*;
import java.util.Collection;

public class IBeacon implements BeaconConsumer {

    public static final String TAG = "BeaconActivity";
    private TextView text;
    private BeaconManager beaconManager;
    private BeaconParser parser = new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");



    public IBeacon(TextView text) {
        this.text = text;
        beaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                text.setText("");
                int iBeaconNumber = 0;
                for(Beacon b : collection){
                    String beaconInfo = "Beacon no " + iBeaconNumber +
                            "RSSI: " + b.getRssi() +
                            "\nMajor: " + b.getId2() +
                            "\nMinor: " + b.getId3() + "\n\n";

                    text.setText(text.getText() + beaconInfo );
                }
            }
        });
    }

    @Override
    public Context getApplicationContext() {
        return null;
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {

    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return false;
    }

}
