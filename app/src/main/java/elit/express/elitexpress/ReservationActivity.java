package elit.express.elitexpress;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity extends AppCompatActivity {

    private Filler filler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        filler = new Filler(this);
        fillInitialData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toEditName:
                Items.setRegistrationActivity(this);
                break;
            case R.id.toHistory:
                Items.setHistoryActivity(this);
                break;
            case R.id.toInfo:
                Items.showInfo(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }

    void fillInitialData() {
        filler.setDate();
        filler.setRaceSpinner();
        setOnItemSelectedRaceSpinner();
        //filler.statusTest();
        //filler.setTimeSpinner();
//        filler.setDepartureAndArrivalSpinner();
    }

    void setOnItemSelectedRaceSpinner() {
        Spinner raceSp = findViewById(R.id.raceSpinner);
        raceSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filler.setTimeSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
