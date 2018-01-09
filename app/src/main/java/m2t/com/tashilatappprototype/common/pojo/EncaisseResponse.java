package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 12/13/17.
 */

public class EncaisseResponse {

    @SerializedName("MsgError")
    private String msgError;
    @SerializedName("CodeError")
    private String codeError;
    @SerializedName("RecuNum")
    private String recuNum;
    @SerializedName("TrxNum")
    private String trxNum;
    @SerializedName("dateTrx")
    private String dateTrx;
    @SerializedName("FacturierTrxNum")
    private String facturierTrxNum;
    @SerializedName("Solde")
    private String solde;

    public EncaisseResponse() {
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    public String getRecuNum() {
        return recuNum;
    }

    public void setRecuNum(String recuNum) {
        this.recuNum = recuNum;
    }

    public String getTrxNum() {
        return trxNum;
    }

    public void setTrxNum(String trxNum) {
        this.trxNum = trxNum;
    }

    public String getDateTrx() {
        return dateTrx;
    }

    public void setDateTrx(String dateTrx) {
        this.dateTrx = dateTrx;
    }

    public String getFacturierTrxNum() {
        return facturierTrxNum;
    }

    public void setFacturierTrxNum(String facturierTrxNum) {
        this.facturierTrxNum = facturierTrxNum;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }
}
