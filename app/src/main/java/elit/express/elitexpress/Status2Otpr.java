package elit.express.elitexpress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status2Otpr {
    @SerializedName("otpr_id")
    @Expose
    private String otprId;
    @SerializedName("city_otpr")
    @Expose
    private String cityOtpr;
    @SerializedName("addtime")
    @Expose
    private String addtime;
    @SerializedName("optr_zone")
    @Expose
    private String optrZone;

    public String getOtprId() {
        return otprId;
    }

    public void setOtprId(String otprId) {
        this.otprId = otprId;
    }

    public String getCityOtpr() {
        return cityOtpr;
    }

    public void setCityOtpr(String cityOtpr) {
        this.cityOtpr = cityOtpr;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getOptrZone() {
        return optrZone;
    }

    public void setOptrZone(String optrZone) {
        this.optrZone = optrZone;
    }
}
