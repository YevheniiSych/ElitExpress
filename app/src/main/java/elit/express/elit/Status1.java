package elit.express.elit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status1 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("times")
    @Expose
    private String times;
    @SerializedName("mesta")
    @Expose
    private int mesta;


    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getMesta() {
        return mesta;
    }

    public void setMesta(int mesta) {
        this.mesta = mesta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
