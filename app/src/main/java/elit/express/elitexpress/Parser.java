package elit.express.elitexpress;

public class Parser {

    static String parseTime(String time) {
        time = time.split(":")[0]
                + ":"
                + time.split(":")[1];
        return time;
    }
}
