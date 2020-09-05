package elit.express.elit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status7 {
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("mest")
    @Expose
    private String mest;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMest() {
        return mest;
    }

    public void setMest(String mest) {
        this.mest = mest;
    }
}
