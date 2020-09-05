package elit.express.elit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status2Prib {
    @SerializedName("prib_id")
    @Expose
    private String pribId;
    @SerializedName("prib_city")
    @Expose
    private String pribCity;
    @SerializedName("prib_zone")
    @Expose
    private String pribZone;

    public String getPribId() {
        return pribId;
    }

    public void setPribId(String pribId) {
        this.pribId = pribId;
    }

    public String getPribCity() {
        return pribCity;
    }

    public void setPribCity(String pribCity) {
        this.pribCity = pribCity;
    }

    public String getPribZone() {
        return pribZone;
    }

    public void setPribZone(String pribZone) {
        this.pribZone = pribZone;
    }
}
