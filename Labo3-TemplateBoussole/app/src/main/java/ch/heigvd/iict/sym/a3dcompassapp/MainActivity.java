package ch.heigvd.iict.sym.a3dcompassapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nfc;
    private Button barCode;
    private Button iBeacon;
    private Button captors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfc = (Button) findViewById(R.id.toNFCActivity);
        barCode = (Button) findViewById(R.id.toBarCodeActivity);
        iBeacon = (Button) findViewById(R.id.toIBeacon);
        captors = (Button) findViewById(R.id.toCaptors);
    }


}

