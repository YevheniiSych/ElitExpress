package elit.express.elitexpress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status5 {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("reis")
    @Expose
    private String reis;
    @SerializedName("dates")
    @Expose
    private String dates;
    @SerializedName("times")
    @Expose
    private String times;
    @SerializedName("citygo")
    @Expose
    private String citygo;
    @SerializedName("cityto")
    @Expose
    private String cityto;
    @SerializedName("marshrut")
    @Expose
    private String marshrut;
    @SerializedName("mest")
    @Expose
    private String mest;
    @SerializedName("itog")
    @Expose
    private String itog;
    @SerializedName("predoplata")
    @Expose
    private String predoplata;
    @SerializedName("status_pay")
    @Expose
    private String statusPay;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReis() {
        return reis;
    }

    public void setReis(String reis) {
        this.reis = reis;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getCitygo() {
        return citygo;
    }

    public void setCitygo(String citygo) {
        this.citygo = citygo;
    }

    public String getCityto() {
        return cityto;
    }

    public void setCityto(String cityto) {
        this.cityto = cityto;
    }

    public String getMarshrut() {
        return marshrut;
    }

    public void setMarshrut(String marshrut) {
        this.marshrut = marshrut;
    }

    public String getMest() {
        return mest;
    }

    public void setMest(String mest) {
        this.mest = mest;
    }

    public String getItog() {
        return itog;
    }

    public void setItog(String itog) {
        this.itog = itog;
    }

    public String getPredoplata() {
        return predoplata;
    }

    public void setPredoplata(String predoplata) {
        this.predoplata = predoplata;
    }

    public String getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(String statusPay) {
        this.statusPay = statusPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
