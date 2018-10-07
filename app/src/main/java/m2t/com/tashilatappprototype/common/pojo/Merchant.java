package m2t.com.tashilatappprototype.common.pojo;

/**
 * Created by laanaya on 8/11/17.
 */

public class Merchant {

    private String name;
    private int uan;
    private String thumbnail;
    private String ident;
    private String typeIdent;
    private String modPaiement;
    private float solde;
    private Boolean isFavoris;

    public Merchant() {
    }

    public Merchant(String name, int uan, String thumbnail) {
        this.name = name;
        this.uan = uan;
        this.thumbnail = thumbnail;
    }

    public Merchant(String name, int uan, float solde) {
        this.name = name;
        this.uan = uan;
        this.solde = solde;
    }

    public Boolean getFavoris() {
        return isFavoris;
    }

    public void setFavoris(Boolean favoris) {
        isFavoris = favoris;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUan() {
        return uan;
    }

    public void setUan(int uan) {
        this.uan = uan;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getTypeIdent() {
        return typeIdent;
    }

    public void setTypeIdent(String typeIdent) {
        this.typeIdent = typeIdent;
    }

    public String getModPaiement() {
        return modPaiement;
    }

    public void setModPaiement(String modPaiement) {
        this.modPaiement = modPaiement;
    }
}
