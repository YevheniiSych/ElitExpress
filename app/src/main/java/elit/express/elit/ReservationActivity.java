package elit.express.elit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity extends AppCompatActivity {

    private Filler filler;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


        filler = new Filler(this);
        setInitialData();
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

    void setInitialData() {
        filler.setDate();
        filler.setRaceSpinner();
        setOnItemSelectedRaceSpinner();
        //filler.setDepartureAndArrivalSpinner();
        filler.setPlaceCountSpinner();
        setPayButtonClickListener();
    }

    void setOnItemSelectedRaceSpinner() {
        Spinner raceSp = findViewById(R.id.raceSpinner);
        raceSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LoadingDialog.loading(ReservationActivity.this);
                filler.setTimeSpinner();
                filler.setDepartureAndArrivalSpinner();

                TextView timeTV=findViewById(R.id.departureTimeTextView);
                timeTV.setText(getString(R.string.departureTime)+" з " +filler.getRaceCities().split("-")[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    void setPayButtonClickListener(){
        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog.loading(ReservationActivity.this);
                Statuses.sendStatus1(new ReceiveCallback() {
                    @Override
                    public void onCallback() {
                        Statuses.sendStatus3(new ReceiveCallback() {
                            @Override
                            public void onCallback() {
                                LoadingDialog.dismiss();
                                reserve();
                            }
                        },filler.getTimeZone(),filler.getRace());

                    }
                },filler.getRace(),filler.getDate());
            }
        });
    }

    void reserve(){
        Alerts alerts=new Alerts();
        alerts.reserveAlert(new ReceiveCallback() {
            @Override
            public void onCallback() {
                LoadingDialog.dismiss();
                Pay pay=new Pay();
                pay.sendPay(ReservationActivity.this,Statuses.getOrderId());
            }
        }, this, filler);
    }

    void setOnItemSelectedDepartureSpinner(){
        Spinner departureSp = findViewById(R.id.departureSpinner);
        departureSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void setOnItemSelectedArrivalSpinner(){
        Spinner arrivalSp = findViewById(R.id.arrivalSpinner);
        arrivalSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void setPrice(){
        Statuses.sendStatus3(new ReceiveCallback() {
            @Override
            public void onCallback() {
            }
        },filler.getTimeZone(),filler.getRace());
    }

}
