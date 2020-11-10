package elit.express.elit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        if(BuildConfig.DEBUG)
            Toast.makeText(this,"debug",Toast.LENGTH_LONG).show();

        if (isNetworkAvailable()) {
            Statuses.sendStatus11(new ReceiveCallback() {
                @Override
                public void onCallback() {
                    networkAvailable();
                }
            });
        } else {
            networkNotAvailable();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.toHistory).setVisible(false);
        menu.findItem(R.id.toEditName).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Items.showInfo(this);
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        }

        // get network info for all of the data interfaces (e.g. WiFi, 3G, LTE, etc.)
        NetworkInfo[] info = connectivity.getAllNetworkInfo();

        // make sure that there is at least one interface to test against
        // iterate through the interfaces
        for (NetworkInfo networkInfo : info) {
            // check this interface for a connected state
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }


    void networkNotAvailable() {
        Alerts alerts = new Alerts();
        alerts.networkNotAvailable(this);//show alert

        //check internet every 2 sec
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isNetworkAvailable() && isVersionCorrect()) {
                            enter();
                            timer.cancel();
                            finish();
                        }
                    }
                });
            }
        }, 0, 2000);
    }

    void networkAvailable() {
        if (isVersionCorrect()) {
            enter();
            finish();
        } else {
            updateAlert();
        }
    }

    boolean isUserRegistered() {
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        //read user data from register
        String name = user.getString("name", "default value");
        String phone = user.getString("phone", "default value");

        if (!name.equals("default value") && !phone.equals("default value"))
            return true;
        else
            return false;
    }

    void enter() {
        if (isUserRegistered()) {
            Items.setReservationActivity(LoadingActivity.this);
        } else
            Items.setRegistrationActivity(LoadingActivity.this);
    }

    boolean isVersionCorrect() {
        return Statuses.getAppVer().equals(Statuses.getActualVersion());
    }

    void updateAlert() {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setMessage(R.string.updateMessage)
                .setPositiveButton(R.string.toUpdate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent updateIntent = new
                                Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=elit.express.elit"));
                        LoadingActivity.this.startActivity(updateIntent);
                    }
                })
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog alert = aBuilder.create();
        alert.setTitle(R.string.update);
        alert.show();
    }

}
