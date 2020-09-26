package elit.express.elit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    private static String phone;
    private static String name;

    private EditText nameET;
    private EditText phoneET;

    private static boolean firstInit = true;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //make invisible menu items
        menu.findItem(R.id.toHistory).setVisible(false);
        menu.findItem(R.id.toEditName).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Items.showInfo(this);
        return super.onOptionsItemSelected(item);
    }


    void init() {
        nameET = findViewById(R.id.editName);
        phoneET = findViewById(R.id.editPhone);

        phoneET.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                phone = v.getText().toString();
                return false;
            }
        });

        setNameTel();

        Button buttonGo = findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTelCorrect()) {
//                    Items.setReservationActivity(RegistrationActivity.this);
//                    saveNameTel();
                    long now = Calendar.getInstance().getTimeInMillis();
                    long nextSmsTime = getLastSmsTime();

                    if (nextSmsTime == -1
                            || nextSmsTime < now) {

                        LoadingDialog.loading(RegistrationActivity.this);
                        saveLastSmsTime();

                        Statuses.sendStatus8(new ReceiveCallback() {
                            @Override
                            public void onCallback() {
                                LoadingDialog.dismiss();
                                if (!Statuses.getSmsCode().equals("0000"))
                                    smsAlert();
                                else
                                    Toast.makeText(getApplicationContext(), R.string.registrationError, Toast.LENGTH_LONG).show();
                            }
                        }, phone);
                    } else {
                        Calendar leftTimeCalendar = Calendar.getInstance();
                        leftTimeCalendar.setTimeInMillis(nextSmsTime - now);
                        String leftTime = leftTimeCalendar.get(Calendar.MINUTE) + "хв "
                                + leftTimeCalendar.get(Calendar.SECOND) + "c";
                        Toast.makeText(getApplicationContext(), getString(R.string.timeLeft) + " " + leftTime, Toast.LENGTH_LONG).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(), R.string.checkTel, Toast.LENGTH_LONG).show();
            }
        });
    }

    //set default name and phone
    void setNameTel() {

        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        //read user data from register
        name = user.getString("name", "default value");
        phone = user.getString("phone", "default value");

        if (!name.equals("default value") && !phone.equals("default value")) {
            nameET.setText(name);
            phoneET.setText(phone);
        }
    }

    //save name and pone as default
    void saveNameTel() {
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = user.edit();

        name = nameET.getText().toString();
        phone = phoneET.getText().toString();

        //save user data in register
        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.apply();
    }

    boolean isTelCorrect() {
        return phone.matches("\\d{10}");
    }

    public static String getPhone() {
        return phone;
    }

    public static String getName() {
        return name;
    }

    public void smsAlert() {
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final LayoutInflater inflater = getLayoutInflater();

        final View alertView = inflater.inflate(R.layout.sms_dialog, null);

        builder.setView(alertView)
                .setPositiveButton(R.string.confirm,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                .setNegativeButton(R.string.buttonCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        Button positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText codeET = alertView.findViewById(R.id.codeEditText);
                String inputCode = codeET != null ? codeET.getText().toString() : "";//empty input code
                String smsCode = Statuses.getSmsCode();

                if (inputCode.equals(smsCode)) {
                    saveNameTel();
                    Items.setReservationActivity(RegistrationActivity.this);
                    ((DialogInterface) view).dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.errorCode, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    long getLastSmsTime() {
        SharedPreferences lastSmsPreference = getPreferences(MODE_PRIVATE);

        return lastSmsPreference.getLong("lastSmsTime", -1);
    }

    void saveLastSmsTime() {
        SharedPreferences lastSmsPreference = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = lastSmsPreference.edit();

        long nextSmsTime = Calendar.getInstance().getTimeInMillis() + 180000;

        editor.putLong("lastSmsTime", nextSmsTime);
        editor.apply();
    }

}
