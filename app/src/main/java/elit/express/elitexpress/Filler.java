package elit.express.elitexpress;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Filler {
    private Activity activity;
    private Calendar calendar;
    private Statuses statuses;// contains methods to send requests


    Filler(Activity activity) {
        this.activity = activity;
        calendar = Calendar.getInstance();
        statuses = new Statuses();
    }

    void setDate() {
        setDateToButton();// set Text date into date button
        Button dateButton = activity.findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDatePicker();
            }
        });
    }

    private void getDatePicker() {
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final long monthInMillis = 2592000000L;

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                setDateToButton();// set Text date into date button
            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + monthInMillis);
        datePickerDialog.show();
    }

    private void setDateToButton() {
        Button buttonDate = activity.findViewById(R.id.dateButton);
        @SuppressLint("SimpleDateFormat")
        String buttonDateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy")
                .format(new Date(calendar.getTimeInMillis()));//format date into dd-MM-yyyy for text in button
        buttonDate.setText(buttonDateFormat);
    }

    @SuppressLint("SimpleDateFormat")
    public String getDate() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(calendar.getTimeInMillis()));//format date into yyyy-MM-dd for request
    }

    void setRaceSpinner() {

        String[] race = {"Суми-Київ", "Київ-Суми"};
        ArrayAdapter<String> raceAdapter = new ArrayAdapter<String>(activity, R.layout.row, R.id.weekofday, race);

        Spinner spRace = activity.findViewById(R.id.raceSpinner);

        spRace.setAdapter(raceAdapter);
    }

    String getRace() {
        Spinner spRace = activity.findViewById(R.id.raceSpinner);
        String race = String.valueOf(spRace.getSelectedItemId() + 1);//get selected item because it needs to send reis_id that begins from 1
        return race;
    }


    void setTimeSpinner() {
        statuses.sendStatus7(new ReceiveCallback() {
            @Override
            public void onCallback() {
                fillTimeSpinner();
            }
        }, getRace());
    }

    private void fillTimeSpinner() {
        ArrayList<String> time = new ArrayList<>();

        ArrayList<Status7> status7ArrayList = statuses.getStatus7ArrayList();

        for (int i = 0; i < status7ArrayList.size(); i++) {
            String formattedTime = Parser.parseTime(status7ArrayList.get(i).getTime());//format time to 00:00
            time.add(formattedTime);
        }

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(activity, R.layout.row, R.id.weekofday, time);

        Spinner timeSp = activity.findViewById(R.id.timeSpinner);
        timeSp.setAdapter(timeAdapter);
    }

    public void setDepartureAndArrivalSpinner() {
        statuses.getStatus2();
//        statuses.sendStatus2(new ReciveCallback() {
//            @Override
//            public void onCallback() {
//                fillDepartureSpinner();
//                fillArrivalSpinner();
//            }
//        },getRace());
    }

    private void fillDepartureSpinner() {

    }

    private void fillArrivalSpinner() {

    }

    void statusTest(){
        //statuses.sendStatus3("65",getRace());
//        statuses.sendStatus4("1","14335", "2020-09-05",
//                "01:00:00", "xxxxx", "yyyyy", "1",
//                "37", "44", "300", "rsgdfz");
//        statuses.getStatus5();
        //statuses.sendStatus5("0959144687");
//        statuses.getStatus6();
        statuses.sendStatus6("138147","1");
    }


}
