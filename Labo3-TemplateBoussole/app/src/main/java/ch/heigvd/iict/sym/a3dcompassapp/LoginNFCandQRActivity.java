package ch.heigvd.iict.sym.a3dcompassapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ch.heigvd.iict.sym.a3dcompassapp.Services.LoginService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * @Class LoginNFCandQRActivity
 * Activity handling the Login with nfc or qr code
 */
public class LoginNFCandQRActivity extends NFCandQRActivity {
    private EditText login = null;
    private EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nfc_qr);

        login = (EditText) findViewById(R.id.editText_login);
        password = (EditText) findViewById(R.id.editText_password);
    }

    /**
     * handling the result of the NFC scan
     * @param result result of the scan
     */
    @Override
    public void tagScan(String result) {
        login(result);
    }

    /**
     * Handling the result of the QR Code scan
     * @param result rusult of the scan
     */
    @Override
    public void qrScan(String result) {
        login(result);
    }

    /**
     * Handling the click on the QR Image by running the QR Reader
     * @param view
     */
    @Override
    protected void handleClickQR(View view){
        // Verification if the user have enter a login and password
        if(login.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(this, "Please enter credentials", Toast.LENGTH_SHORT).show();
        } else {
            super.handleClickQR(view);
        }
    }

    /**
     * Handling the click on the NFC Image by sending a message of information
     * @param view
     */
    @Override
    protected void handleClickNFC(View view){
        if(login.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(this, "Please enter credentials", Toast.LENGTH_SHORT).show();
        } else {
            super.handleClickNFC(view);
        }
    }

    /**
     *  Handling the NFC scan
     * @param intent
     */
    @Override
    protected void handleIntent(Intent intent) {
        if(login.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(this, "Please enter credentials", Toast.LENGTH_SHORT).show();
        } else {
            super.handleIntent(intent);
        }
    }

    /**
     * Checking if the username, password and tag is correct
     * @param tag
     */
    private void login(String tag){
        String username = login.getText().toString();
        String pwd = password.getText().toString();
        if(!LoginService.logged(username, pwd, tag)){
            Toast.makeText(this, "Wrong crendentials", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginNFCandQRActivity.this, SecurityActivity.class);
            startActivity(intent);
        }
    }
}
