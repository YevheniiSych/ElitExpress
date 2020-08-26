package elit.express.elitexpress;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class Filler {
    private ReservationActivity activity;
    private Calendar calendar;

    Filler(ReservationActivity activity){
        this.activity=activity;
        calendar = Calendar.getInstance();
    }

    void setDate(){
        setDateToButton();
        Button dateButton=activity.findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDatePicker();
            }
        });
    }

    private void getDatePicker(){
        int year=calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year,monthOfYear,dayOfMonth);
                setDateToButton();
            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + Long.parseLong("2592000000"));
        datePickerDialog.show();
    }

    private void setDateToButton() {
        Button buttonDate = activity.findViewById(R.id.dateButton);
        @SuppressLint("SimpleDateFormat")
        String buttonDateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new Date(calendar.getTimeInMillis()));
        buttonDate.setText(buttonDateFormat);
    }

    @SuppressLint("SimpleDateFormat")
    public String getDate(){
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date(calendar.getTimeInMillis()));
    }

    void fillRaceSpinner(){

        String[] race={"Суми-Київ","Київ-Суми"};
        ArrayAdapter<String> raceAdapter = new ArrayAdapter<String>(activity, R.layout.row, R.id.weekofday, race);

        Spinner spRace = activity.findViewById(R.id.raceSpinner);

        spRace.setAdapter(raceAdapter);
    }

    String getRace(){
        Spinner spRace = activity.findViewById(R.id.raceSpinner);
        String race=String.valueOf(spRace.getSelectedItemId()+1);
        return race;
    }


    public void fillTimesSpinner(ArrayList<Status1> arrayList){
        ArrayList<String> departureTimeList = new ArrayList<String>();




        for (int i = 0; i < arrayList.size(); i++) {

        }



//        spDepartureTime = view.findViewById(R.id.departureTimeList);
//        ArrayAdapter<String> departureTimeAdapter = new ArrayAdapter<String>(context, R.layout.row, R.id.weekofday, departureTimeList);
//        spDepartureTime.setAdapter(departureTimeAdapter);
    }

}
