package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by laanaya on 12/13/17.
 */

public class EncaisseRequest {

    @SerializedName("ListeFactures")
    private List<Facture> listeFactures;
    @SerializedName("modPaiement")
    private String modPaiement;
    @SerializedName("montantTTC")
    private String montantTTC;
    @SerializedName("montantHT")
    private String montantHT;
    @SerializedName("MntTimbre")
    private String mntTimbre;
    @SerializedName("MntFraisTtc")
    private String mntFraisTtc;
    @SerializedName("FacturesRef")
    private String facturesRef;
    @SerializedName("req")
    private Req req;

    public EncaisseRequest() {
    }

    public List<Facture> getListeFactures() {
        return listeFactures;
    }

    public void setListeFactures(List<Facture> listeFactures) {
        this.listeFactures = listeFactures;
    }

    public String getModPaiement() {
        return modPaiement;
    }

    public void setModPaiement(String modPaiement) {
        this.modPaiement = modPaiement;
    }

    public String getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(String montantTTC) {
        this.montantTTC = montantTTC;
    }

    public String getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(String montantHT) {
        this.montantHT = montantHT;
    }

    public String getMntTimbre() {
        return mntTimbre;
    }

    public void setMntTimbre(String mntTimbre) {
        this.mntTimbre = mntTimbre;
    }

    public String getMntFraisTtc() {
        return mntFraisTtc;
    }

    public void setMntFraisTtc(String mntFraisTtc) {
        this.mntFraisTtc = mntFraisTtc;
    }

    public String getFacturesRef() {
        return facturesRef;
    }

    public void setFacturesRef(String facturesRef) {
        this.facturesRef = facturesRef;
    }

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }
}
