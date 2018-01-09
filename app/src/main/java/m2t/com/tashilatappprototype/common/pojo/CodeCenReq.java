package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 1/4/18.
 */

public class CodeCenReq {

    @SerializedName("Type")
    private String type;
    @SerializedName("Crit")
    private String crit;
    @SerializedName("ValeCrit")
    private String valeCrit;

    public CodeCenReq() {
    }

    public CodeCenReq(String type, String crit, String valeCrit) {
        this.type = type;
        this.crit = crit;
        this.valeCrit = valeCrit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCrit() {
        return crit;
    }

    public void setCrit(String crit) {
        this.crit = crit;
    }

    public String getValeCrit() {
        return valeCrit;
    }

    public void setValeCrit(String valeCrit) {
        this.valeCrit = valeCrit;
    }
}
