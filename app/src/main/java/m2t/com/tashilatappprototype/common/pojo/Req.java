package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 11/7/17.
 */

public class Req {

    @SerializedName("FacturierId")
    private String facturieId;

    public Req() {
    }

    public Req(String facturieId) {
        this.facturieId = facturieId;
    }

    public String getFacturieId() {
        return facturieId;
    }

    public void setFacturieId(String facturieId) {
        this.facturieId = facturieId;
    }
}
