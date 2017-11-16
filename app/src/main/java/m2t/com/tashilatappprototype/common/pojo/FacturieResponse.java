package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by laanaya on 11/8/17.
 */

public class FacturieResponse {

    @SerializedName("MsgError")
    private String msgError;
    @SerializedName("CodeError")
    private String codeError;
    @SerializedName("ListeFactures")
    private List<Facture> factureList;
    @SerializedName("MntFraisTtc")
    private String mntFraisTtc;
    @SerializedName("MntPenalite")
    private String mntPenalite;
    @SerializedName("MntTimbre")
    private String mntTimbre;
    @SerializedName("FacturesRef")
    private String factureRef;
    @SerializedName("MntHtFrais")
    private String mntHtFrais;
    @SerializedName("MntTvaFrais")
    private String mntTvaFrais;

    public FacturieResponse() {
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

    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }

    public String getMntFraisTtc() {
        return mntFraisTtc;
    }

    public void setMntFraisTtc(String mntFraisTtc) {
        this.mntFraisTtc = mntFraisTtc;
    }

    public String getMntPenalite() {
        return mntPenalite;
    }

    public void setMntPenalite(String mntPenalite) {
        this.mntPenalite = mntPenalite;
    }

    public String getMntTimbre() {
        return mntTimbre;
    }

    public void setMntTimbre(String mntTimbre) {
        this.mntTimbre = mntTimbre;
    }

    public String getFactureRef() {
        return factureRef;
    }

    public void setFactureRef(String factureRef) {
        this.factureRef = factureRef;
    }

    public String getMntHtFrais() {
        return mntHtFrais;
    }

    public void setMntHtFrais(String mntHtFrais) {
        this.mntHtFrais = mntHtFrais;
    }

    public String getMntTvaFrais() {
        return mntTvaFrais;
    }

    public void setMntTvaFrais(String mntTvaFrais) {
        this.mntTvaFrais = mntTvaFrais;
    }
}
