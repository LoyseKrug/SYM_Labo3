package ch.heigvd.iict.sym.a3dcompassapp.Services;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import org.altbeacon.beacon.*;

import java.util.ArrayList;

public class IBeacon implements BeaconConsumer {

    public static final String TAG = "BeaconActivity";
    private BeaconManager beaconManager;
    private BeaconParser parser = new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");
    private ArrayList<String> iBeacons = new ArrayList<String>();

    public IBeacon() {
        beaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "Enter a beacon region");
                //TODO Add beacon to the list of beacon

            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "Exit a beacon region");
                //TODO Delete beacon from the list
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {

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
