package elit.express.elit;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
                phone=v.getText().toString();
                return false;
            }
        });

        setNameTel();

        if (!phone.equals("default value") && firstInit) {
            Items.setReservationActivity(RegistrationActivity.this);
            firstInit = false;
            finish();
        }

        Button buttonGo = findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTelCorrect()) {
                    saveNameTel();
                    Items.setReservationActivity(RegistrationActivity.this);
                } else
                    Toast.makeText(getApplicationContext(), R.string.checkTel, Toast.LENGTH_LONG).show();
            }
        });
    }

    //set default name and phone
    void setNameTel() {

        SharedPreferences user = getPreferences(MODE_PRIVATE);
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
        SharedPreferences user = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = user.edit();

        name = nameET.getText().toString();
        phone = phoneET.getText().toString();

        //save user data in register
        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.apply();
    }

    boolean isTelCorrect() {
//        String formattedPhone = PhoneNumberUtils.formatNumber(phone);
//        Toast.makeText(getApplicationContext(),formattedPhone,Toast.LENGTH_LONG).show();

        return phone.matches("\\d{10}");//check phone number for 10 characters
    }

    public static String getPhone() {
        return phone;
    }

    public static String getName() {
        return name;
    }

    private void setImageSize(){
        ImageView image=findViewById(R.id.imageView2);

    }
}
