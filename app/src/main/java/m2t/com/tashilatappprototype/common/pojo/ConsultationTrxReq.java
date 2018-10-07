package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

public class ConsultationTrxReq {

    @SerializedName("dateF")
    private String dateF;
    @SerializedName("dateD")
    private String dateD;

    public ConsultationTrxReq(String dateF, String dateD) {
        this.dateF = dateF;
        this.dateD = dateD;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public String getDateD() {
        return dateD;
    }

    public void setDateD(String dateD) {
        this.dateD = dateD;
    }
}
