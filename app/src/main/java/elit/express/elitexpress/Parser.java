package elit.express.elitexpress;

import android.app.Activity;
import android.widget.Button;
import android.widget.Spinner;

public class Parser {

    static String parseTime(String time) {
        time = time.split(":")[0]
                + ":"
                + time.split(":")[1];
        return time;
    }

    static String addSecondsToTime(String time){
        return time+":00";
    }

    static String swapDate(String date){
        String day=date.split("-")[2];
        String month=date.split("-")[1];
        String year=date.split("-")[0];
        return day+"-"+month+"-"+year;
    }

    static public String allData(Activity activity) {
        Button dateBtn = activity.findViewById(R.id.dateButton);
        Spinner placeCountSp = activity.findViewById(R.id.placeCountSpinner);
        Spinner departureSp = activity.findViewById(R.id.departureSpinner);
        Spinner arrivalSp = activity.findViewById(R.id.arrivalSpinner);
        Spinner departureTimeSp = activity.findViewById(R.id.timeSpinner);

        String date = "         " + activity.getString(R.string.dateInput) + ": " + dateBtn.getText();
        String placeCount = "     " + activity.getString(R.string.placeCountInp) + ": " + placeCountSp.getSelectedItem();
        String departure = "        " + activity.getString(R.string.departure) + ": " + departureSp.getSelectedItem();
        String arrival = "                " + activity.getString(R.string.arrival) + ": " + arrivalSp.getSelectedItem();
        String departureTime = activity.getString(R.string.departureTime) + ": " + departureTimeSp.getSelectedItem().toString().split(" ")[0];
        String price = "   " + activity.getString(R.string.price) + ": " + Statuses.getPrice() + " грн";
        return date + "\n" + placeCount + "\n" + departure + "\n" + arrival + "\n" + departureTime + "\n" + price;
    }
}
