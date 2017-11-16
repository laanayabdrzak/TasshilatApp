package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 11/7/17.
 */

public class FacturieRequest {

    @SerializedName("searchForm")
    private SearchForm searchForm;
    @SerializedName("modPaiement")
    private String modPaiement;
    @SerializedName("tokenCSFR")
    private String tokenCSFR;
    @SerializedName("req")
    private Req req;

    public FacturieRequest() {
    }

    public SearchForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    public String getModPaiement() {
        return modPaiement;
    }

    public void setModPaiement(String modPaiement) {
        this.modPaiement = modPaiement;
    }

    public String getTokenCSFR() {
        return tokenCSFR;
    }

    public void setTokenCSFR(String tokenCSFR) {
        this.tokenCSFR = tokenCSFR;
    }

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }
}
