package ch.heigvd.iict.sym.a3dcompassapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class LoginNFCActivity extends AppCompatActivity {

    public static final String TAG = "Error";

    private EditText login = null;
    private EditText password = null;
    private Button connexion = null;
    private NfcAdapter mNfcAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nfc_qr);

        login = (EditText) findViewById(R.id.editText_login);
        password = (EditText) findViewById(R.id.editText_password);
    }


    protected void onResume() {
        super.onResume();
        setupForegroundDispatch();
    }

    protected void onPause() {
        super.onPause();
        stopForegroundDispatch();
    }

    // called in onResume()
    private void setupForegroundDispatch() {
        if(mNfcAdapter == null)
            return;
        final Intent intent = new Intent(this.getApplicationContext(),
                this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent pendingIntent =
                PendingIntent.getActivity(this.getApplicationContext(), 0, intent, 0);
        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};
        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType("text/plain");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            Log.e(TAG, "MalformedMimeTypeException", e);
        }
        mNfcAdapter.enableForegroundDispatch(this, pendingIntent, filters, techList);
    }
    // called in onPause()
    private void stopForegroundDispatch() {
        if(mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }
}
