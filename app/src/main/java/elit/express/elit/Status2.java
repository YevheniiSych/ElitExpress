package elit.express.elit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Status2 {
    @SerializedName("otpr")
    @Expose
    private ArrayList<Status2Otpr> otpr;
    @SerializedName("prib")
    @Expose
    private ArrayList<Status2Prib> prib;

    public ArrayList<Status2Otpr> getOtpr() {
        return otpr;
    }

    public void setOtpr(ArrayList<Status2Otpr> otpr) {
        this.otpr = otpr;
    }

    public ArrayList<Status2Prib> getPrib() {
        return prib;
    }

    public void setPrib(ArrayList<Status2Prib> prib) {
        this.prib = prib;
    }
}
