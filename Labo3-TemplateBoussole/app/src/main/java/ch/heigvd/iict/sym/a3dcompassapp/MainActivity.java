/**
 * Authors: Adrien Allemand, James Smith, Loyse Krug
 *
 * Date: 16.12.2018
 *
 * Objective: This activity is the center point that gives access to all the other activities.
 *            The user will be able to access:
 *            - NFC or QRcode connetion system
 *            - an iBeacon captor
 *            - a compass
 *
 * Sources: -
 */

package ch.heigvd.iict.sym.a3dcompassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nfc_qr;
    private Button iBeacon;
    private Button captors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfc_qr = (Button) findViewById(R.id.btn_NFCandQRActivity);
        iBeacon = (Button) findViewById(R.id.btn_IBeacon);
        captors = (Button) findViewById(R.id.btn_Captors);

        nfc_qr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, LoginNFCandQRActivity.class);
                startActivity(intent);
            }
        });

        iBeacon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, IBeaconActivity.class);
                startActivity(intent);
            }
        });

        captors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, CompassActivity.class); // TODO: mettre le nom de la classe gérant les iBeacon
                startActivity(intent);
            }
        });
    }


}

