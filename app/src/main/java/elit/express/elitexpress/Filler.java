package elit.express.elitexpress;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Filler {
    private Activity activity;
    private Calendar calendar;


    Filler(Activity activity) {
        this.activity = activity;
        calendar = Calendar.getInstance();
    }

    void setDate() {
        Calendar calendar2 = Calendar.getInstance();
//        long minTime=System.currentTimeMillis();
        if (calendar2.get(Calendar.HOUR_OF_DAY) == 23 &&
                calendar.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)) {//check if time is more than 23 - load the next day

            calendar.set(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY) + 1,
                    calendar.get(Calendar.MINUTE));
        }

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
                LoadingDialog.loading(activity);
                setTimeSpinner();
            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+monthInMillis);
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
    String getDate() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(calendar.getTimeInMillis()));//format date into yyyy-MM-dd for request
    }

    void setRaceSpinner() {

        String[] race = {"Суми-Київ", "Київ-Суми"};
        ArrayAdapter<String> raceAdapter = new ArrayAdapter<>(activity, R.layout.row, R.id.weekofday, race);

        Spinner spRace = activity.findViewById(R.id.raceSpinner);

        spRace.setAdapter(raceAdapter);
    }

    String getRace() {
        Spinner spRace = activity.findViewById(R.id.raceSpinner);
        return String.valueOf(spRace.getSelectedItemId() + 1);
    }

    String getRaceCities(){
        Spinner spRace = activity.findViewById(R.id.raceSpinner);
        return spRace.getSelectedItem().toString();
    }


    void setTimeSpinner() {
        Statuses.sendStatus7(new ReceiveCallback() {
            @Override
            public void onCallback() {
                fillTimeSpinner();
                LoadingDialog.dismiss();
            }
        }, getRace());
    }

    private void fillTimeSpinner() {
        ArrayList<String> time = new ArrayList<>();

        ArrayList<Status7> status7ArrayList = Statuses.getStatus7ArrayList();

        for (int i = 0; i < status7ArrayList.size(); i++) {
            if (isTimeActual(status7ArrayList.get(i).getTime())) {
                String formattedTime = Parser.parseTime(status7ArrayList.get(i).getTime());//format time to 00:00
                time.add(formattedTime);
            }
        }

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(activity, R.layout.row, R.id.weekofday, time);

        Spinner timeSp = activity.findViewById(R.id.timeSpinner);
        timeSp.setAdapter(timeAdapter);
    }

    @SuppressLint("SimpleDateFormat")
    private boolean isTimeActual(String time) {
        int hours = Integer.parseInt(time.split(":")[0]),
                minutes = Integer.parseInt(time.split(":")[1]);
        Calendar chosenTime = Calendar.getInstance();
        chosenTime.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                hours,
                minutes);

        return !chosenTime.before(Calendar.getInstance());
    }

    void setDepartureAndArrivalSpinner() {
        Statuses.sendStatus2(new ReceiveCallback() {
            @Override
            public void onCallback() {
                fillDepartureSpinner();
                fillArrivalSpinner();
                LoadingDialog.dismiss();
            }
        }, getRace());
    }

    private void fillDepartureSpinner() {
        ArrayList<Status2Otpr> status2Otprs = Statuses.getStatus2Otprs();

        ArrayList<String> departure = new ArrayList<>();

        for (int i = 0; i < status2Otprs.size(); i++) {
            departure.add(status2Otprs.get(i).getCityOtpr());
        }

        ArrayAdapter<String> departureAdapter = new ArrayAdapter<String>(activity, R.layout.row, R.id.weekofday, departure);

        Spinner departureSp = activity.findViewById(R.id.departureSpinner);
        departureSp.setAdapter(departureAdapter);
    }

    private void fillArrivalSpinner() {
        ArrayList<Status2Prib> status2Pribs = Statuses.getStatus2Pribs();

        ArrayList<String> arrival = new ArrayList<>();

        for (int i = 0; i < status2Pribs.size(); i++) {
            arrival.add(status2Pribs.get(i).getPribCity());
        }

        ArrayAdapter<String> arrivalAdapter = new ArrayAdapter<String>(activity, R.layout.row, R.id.weekofday, arrival);

        Spinner arrivalSp = activity.findViewById(R.id.arrivalSpinner);
        arrivalSp.setAdapter(arrivalAdapter);
    }

    public void setPlaceCountSpinner() {
        ArrayList<String> placeCount = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            placeCount.add(String.valueOf(i));
        }

        ArrayAdapter<String> placeCountAdapter = new ArrayAdapter<String>(activity, R.layout.row, R.id.weekofday, placeCount);

        Spinner placeCountSp = activity.findViewById(R.id.placeCountSpinner);
        placeCountSp.setAdapter(placeCountAdapter);
    }


    public String getReisID() {
        ArrayList<Status1> status1ArrayList = Statuses.getStatus1ArrayList();
        String selectedTime = getTime();
        for (int i = 0; i < status1ArrayList.size(); i++) {
            if (status1ArrayList.get(i).getTimes().equals(selectedTime))
                return status1ArrayList.get(i).getId();
        }
        return "1";
    }

    String getTime() {
        Spinner timeSp = activity.findViewById(R.id.timeSpinner);
        String time = Parser.addSecondsToTime(timeSp.getSelectedItem().toString());
        return time;
    }

    String getPlaceCount() {
        Spinner placeCountSp = activity.findViewById(R.id.placeCountSpinner);
        String placeCount = placeCountSp.getSelectedItem().toString();
        return placeCount;
    }

    String getID_from() {
        Spinner departureSp = activity.findViewById(R.id.departureSpinner);

        String selectedCityOtpr = departureSp.getSelectedItem().toString();

        ArrayList<Status2Otpr> status2Otprs = Statuses.getStatus2Otprs();
        for (int i = 0; i < status2Otprs.size(); i++) {
            String cityOtpr = status2Otprs.get(i).getCityOtpr();
            if (selectedCityOtpr.equals(cityOtpr))
                return status2Otprs.get(i).getOtprId();
        }
        return null;
    }

    String getId_to() {
        Spinner arrivalSp = activity.findViewById(R.id.arrivalSpinner);

        String selectedCityPrib = arrivalSp.getSelectedItem().toString();

        ArrayList<Status2Prib> status2Pribs = Statuses.getStatus2Pribs();
        for (int i = 0; i < status2Pribs.size(); i++) {
            String cityPrib = status2Pribs.get(i).getPribCity();
            if (selectedCityPrib.equals(cityPrib))
                return status2Pribs.get(i).getPribId();
        }
        return null;
    }

    String getInfo() {
        EditText commentET = activity.findViewById(R.id.commentEditText);
        String info = commentET.getText().toString();
        return info;
    }

    String getTimeZone() {
        Spinner departureSp = activity.findViewById(R.id.departureSpinner);
        String selectedCity_otpr = departureSp.getSelectedItem().toString();
        ArrayList<Status2Otpr> status2Otprs = Statuses.getStatus2Otprs();

        Spinner arrivalSp = activity.findViewById(R.id.arrivalSpinner);
        String selectedPrib_city = arrivalSp.getSelectedItem().toString();
        ArrayList<Status2Prib> status2Pribs = Statuses.getStatus2Pribs();

        int otpr_zone = 0, prib_zone = 0;

        for (int i = 0; i < status2Otprs.size(); i++) {
            String city_otpr = status2Otprs.get(i).getCityOtpr();
            if (city_otpr.equals(selectedCity_otpr)) {
                otpr_zone = Integer.parseInt(status2Otprs.get(i).getOptrZone());
            }
        }

        for (int i = 0; i < status2Pribs.size(); i++) {
            String prib_city = status2Pribs.get(i).getPribCity();
            if (prib_city.equals(selectedPrib_city)) {
                prib_zone = Integer.parseInt(status2Pribs.get(i).getPribZone());
            }
        }

        return String.valueOf(otpr_zone + prib_zone);
    }


}
