/**
 * Authors: Adrien Allemand, James Smith, Loyse Krug
 *
 * Objective: class that shows the user his level of security when he's connected.
 * The level of security diminishes with time  when the user is connected and can
 * be maximized with a new authentification.
 * At the beginning the user has access to three buttons:
 * - high security
 * - medium security
 * - low security
 * And with  the time, he loses access to high security, then to medium security.
 */

package ch.heigvd.iict.sym.a3dcompassapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import ch.heigvd.iict.sym.a3dcompassapp.Services.LoginService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * @class SecurityActivity
 * Activity handling the test of security with a timer
 */
public class SecurityActivity extends NFCandQRActivity {

    private Button btnMaxSecurity = null;
    private Button btnMediumSecurity = null;
    private Button btnLowSecurity = null;
    private ProgressBar progressSecu = null;
    private final int NB_LVL_SECURITY = 3;
    private final int TIME_SECURITY = 10; // 10s en millisecondes
    private final int SECOND = 1000;
    private final int TICK_RATE = 100;
    private boolean isRunning = false;
    CountDownTimer counter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        btnMaxSecurity = (Button) findViewById(R.id.btn_maxSecurity);
        btnMediumSecurity = (Button) findViewById(R.id.btn_mediumSecurity);
        btnLowSecurity = (Button) findViewById(R.id.btn_minSecurity);
        progressSecu = (ProgressBar) findViewById(R.id.progress_security);

        runTimer();
    }

    /**
     * run/set the timer
     */
    private void runTimer(){
        isRunning = true;
        counter = new CountDownTimer(NB_LVL_SECURITY * TIME_SECURITY * SECOND, TICK_RATE) {
            Toast toast;
            int securityLvl = NB_LVL_SECURITY;
            int timer = 0;
            public void onTick(long millisUntilFinished) {
                timer++; // update timer
                progressSecu.setProgress((((TIME_SECURITY * 100) - ((timer+1) * 10))/(TIME_SECURITY) - 1)); // Updating downgrading progressbar
                // if timer is at the end of a security level. update buttons
                if(timer + 1 == TIME_SECURITY * 10) {
                    securityLvl--;
                    timer = 0;
                    switch (securityLvl){
                        case 2:
                            btnMaxSecurity.setEnabled(false);
                            toast = Toast.makeText(SecurityActivity.this , "The max security time have been finished plz scan your tag to access it again", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        case 1:
                            btnMediumSecurity.setEnabled(false);
                            toast = Toast.makeText(SecurityActivity.this , "The medium security time have been finished plz scan your tag to access it again", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        case 0:
                            btnLowSecurity.setEnabled(false);
                            toast = Toast.makeText(SecurityActivity.this , "The lower security time have been finished plz scan your tag to access it again", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                    }
                }
                 }

            public void onFinish() {
                toast = Toast.makeText(SecurityActivity.this , "Plz scan your tag to access it again", Toast.LENGTH_SHORT);
                toast.show();
                isRunning = false;
            }
        };

        counter.start();
    }

    /**
     * Handling what to do with the NFC scan result. (is the tag correct ? Reset the timer)
     * @param result result of the scan
     */
    @Override
    public void tagScan(String result) {
        logged(result);
    }

    /**
     * Handling what to do with the QR Code scan result. (is the tag correct ? Reset the timer)
     * @param result rusult of the scan
     */
    @Override
    void qrScan(String result) {
        logged(result);
    }

    /**
     * verrifing if the tag is correct. If it's correct, reset timer and enable button
     * @param result
     */
    private void logged(String result){
        if(LoginService.isTagCorrect(result)) {
            btnLowSecurity.setEnabled(true);
            btnMediumSecurity.setEnabled(true);
            btnMaxSecurity.setEnabled(true);
            if(isRunning){
                counter.cancel();
            }
            runTimer();
        }
    }

    /**
     * Cancel timer if exiting activity
     */
    @Override
    public void finish(){
        if(isRunning){
            counter.cancel();
        }
        super.finish();
    }
}
