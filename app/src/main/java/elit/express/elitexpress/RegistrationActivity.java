package elit.express.elitexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.Preferences;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setNameTel();
        Button buttonGo=findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNameTel();
                Items.setReservationActivity(RegistrationActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.toHistory).setVisible(false);
        menu.findItem(R.id.toEditName).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Items.showInfo(this);
        return super.onOptionsItemSelected(item);
    }

    void setNameTel(){
        SharedPreferences user = getPreferences(MODE_PRIVATE);

        String name=user.getString("name","default value");
        String phone=user.getString("phone","default value");

        if(!name.equals("default value") && !phone.equals("default value")){
            EditText nameET=findViewById(R.id.editName);
            EditText phoneET=findViewById(R.id.editPhone);
            nameET.setText(name);
            phoneET.setText(phone);
        }
    }

    void saveNameTel(){
        SharedPreferences user = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor =user.edit();
        EditText nameET=findViewById(R.id.editName);
        EditText phoneET=findViewById(R.id.editPhone);

        String name=nameET.getText().toString();
        String phone=phoneET.getText().toString();

        editor.putString("name",name);
        editor.putString("phone",phone);
        editor.apply();
    }
}
