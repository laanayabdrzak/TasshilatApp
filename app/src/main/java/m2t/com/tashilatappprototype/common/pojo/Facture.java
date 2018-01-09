package m2t.com.tashilatappprototype.common.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 11/8/17.
 */

public class Facture implements Parcelable {

    @SerializedName("ID")
    private String idFacture;
    @SerializedName("MerchantId")
    private String merchantId;
    @SerializedName("NumEcheance")
    private String numEcheance;
    @SerializedName("Type")
    private String type;
    @SerializedName("DesignationType")
    private String designationType;
    @SerializedName("Produit")
    private String produit;
    @SerializedName("LocalId")
    private String localId;
    @SerializedName("NumLigne")
    private String numLigne;
    @SerializedName("description")
    private String description;
    @SerializedName("Echeance")
    private String echeance;
    @SerializedName("DateLimite")
    private String dateLimite;
    @SerializedName("Date")
    private String date;
    @SerializedName("NumContrat")
    private String numContrat;
    @SerializedName("Reference")
    private String reference;
    @SerializedName("Tournee")
    private String tournee;
    @SerializedName("NumClient")
    private String numClient;
    @SerializedName("NomClient")
    private String nomClient;
    @SerializedName("CIN")
    private String cin;
    @SerializedName("Tel")
    private String tel;
    @SerializedName("Adresse")
    private String adresse;
    @SerializedName("Compteur")
    private String compteur;
    @SerializedName("MntHt")
    private String mntHt;
    @SerializedName("MntTVA")
    private String mntTVA;
    @SerializedName("MntTimbre")
    private String mntTimbre;
    @SerializedName("MntPenalite")
    private String mntPenalite;
    @SerializedName("MntMaj")
    private String mntMaj;
    @SerializedName("MntTTC")
    private String mntTTC;
    @SerializedName("MntAvance")
    private String mntAvance;
    @SerializedName("MntFrais")
    private String mntFrais;
    @SerializedName("MntFraisTva")
    private String mntFraisTva;
    @SerializedName("MntFraisHt")
    private String mntFraisHt;
    @SerializedName("MntFraisTimbre")
    private String mntFraisTimbre;
    @SerializedName("Obligatoire")
    private Boolean obligatoire;
    @SerializedName("Attachement")
    private Attachement attachement;
    @SerializedName("seq")
    private int seq;
    @SerializedName("Extra")
    private Extra extra;

    public Facture() {
    }

    protected Facture(Parcel in) {
        idFacture = in.readString();
        merchantId = in.readString();
        numEcheance = in.readString();
        type = in.readString();
        designationType = in.readString();
        produit = in.readString();
        localId = in.readString();
        numLigne = in.readString();
        description = in.readString();
        echeance = in.readString();
        dateLimite = in.readString();
        date = in.readString();
        numContrat = in.readString();
        reference = in.readString();
        tournee = in.readString();
        numClient = in.readString();
        nomClient = in.readString();
        cin = in.readString();
        tel = in.readString();
        adresse = in.readString();
        compteur = in.readString();
        mntHt = in.readString();
        mntTVA = in.readString();
        mntTimbre = in.readString();
        mntPenalite = in.readString();
        mntMaj = in.readString();
        mntTTC = in.readString();
        mntAvance = in.readString();
        mntFrais = in.readString();
        mntFraisTva = in.readString();
        mntFraisHt = in.readString();
        mntFraisTimbre = in.readString();
        byte tmpObligatoire = in.readByte();
        obligatoire = tmpObligatoire == 0 ? null : tmpObligatoire == 1;
        seq = in.readInt();
    }

    public static final Creator<Facture> CREATOR = new Creator<Facture>() {
        @Override
        public Facture createFromParcel(Parcel in) {
            return new Facture(in);
        }

        @Override
        public Facture[] newArray(int size) {
            return new Facture[size];
        }
    };

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getNumEcheance() {
        return numEcheance;
    }

    public void setNumEcheance(String numEcheance) {
        this.numEcheance = numEcheance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesignationType() {
        return designationType;
    }

    public void setDesignationType(String designationType) {
        this.designationType = designationType;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(String numLigne) {
        this.numLigne = numLigne;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEcheance() {
        return echeance;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public String getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(String dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(String numContrat) {
        this.numContrat = numContrat;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTournee() {
        return tournee;
    }

    public void setTournee(String tournee) {
        this.tournee = tournee;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCompteur() {
        return compteur;
    }

    public void setCompteur(String compteur) {
        this.compteur = compteur;
    }

    public String getMntHt() {
        return mntHt;
    }

    public void setMntHt(String mntHt) {
        this.mntHt = mntHt;
    }

    public String getMntTVA() {
        return mntTVA;
    }

    public void setMntTVA(String mntTVA) {
        this.mntTVA = mntTVA;
    }

    public String getMntTimbre() {
        return mntTimbre;
    }

    public void setMntTimbre(String mntTimbre) {
        this.mntTimbre = mntTimbre;
    }

    public String getMntPenalite() {
        return mntPenalite;
    }

    public void setMntPenalite(String mntPenalite) {
        this.mntPenalite = mntPenalite;
    }

    public String getMntMaj() {
        return mntMaj;
    }

    public void setMntMaj(String mntMaj) {
        this.mntMaj = mntMaj;
    }

    public String getMntTTC() {
        return mntTTC;
    }

    public void setMntTTC(String mntTTC) {
        this.mntTTC = mntTTC;
    }

    public String getMntAvance() {
        return mntAvance;
    }

    public void setMntAvance(String mntAvance) {
        this.mntAvance = mntAvance;
    }

    public String getMntFrais() {
        return mntFrais;
    }

    public void setMntFrais(String mntFrais) {
        this.mntFrais = mntFrais;
    }

    public String getMntFraisTva() {
        return mntFraisTva;
    }

    public void setMntFraisTva(String mntFraisTva) {
        this.mntFraisTva = mntFraisTva;
    }

    public String getMntFraisHt() {
        return mntFraisHt;
    }

    public void setMntFraisHt(String mntFraisHt) {
        this.mntFraisHt = mntFraisHt;
    }

    public String getMntFraisTimbre() {
        return mntFraisTimbre;
    }

    public void setMntFraisTimbre(String mntFraisTimbre) {
        this.mntFraisTimbre = mntFraisTimbre;
    }

    public Boolean getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public Attachement getAttachement() {
        return attachement;
    }

    public void setAttachement(Attachement attachement) {
        this.attachement = attachement;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idFacture);
        parcel.writeString(merchantId);
        parcel.writeString(numEcheance);
        parcel.writeString(type);
        parcel.writeString(designationType);
        parcel.writeString(produit);
        parcel.writeString(localId);
        parcel.writeString(numLigne);
        parcel.writeString(description);
        parcel.writeString(echeance);
        parcel.writeString(dateLimite);
        parcel.writeString(date);
        parcel.writeString(numContrat);
        parcel.writeString(reference);
        parcel.writeString(tournee);
        parcel.writeString(numClient);
        parcel.writeString(nomClient);
        parcel.writeString(cin);
        parcel.writeString(tel);
        parcel.writeString(adresse);
        parcel.writeString(compteur);
        parcel.writeString(mntHt);
        parcel.writeString(mntTVA);
        parcel.writeString(mntTimbre);
        parcel.writeString(mntPenalite);
        parcel.writeString(mntMaj);
        parcel.writeString(mntTTC);
        parcel.writeString(mntAvance);
        parcel.writeString(mntFrais);
        parcel.writeString(mntFraisTva);
        parcel.writeString(mntFraisHt);
        parcel.writeString(mntFraisTimbre);
        parcel.writeByte((byte) (obligatoire == null ? 0 : obligatoire ? 1 : 2));
        parcel.writeInt(seq);
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture='" + idFacture + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", numEcheance='" + numEcheance + '\'' +
                ", type='" + type + '\'' +
                ", designationType='" + designationType + '\'' +
                ", produit='" + produit + '\'' +
                ", localId='" + localId + '\'' +
                ", numLigne='" + numLigne + '\'' +
                ", description='" + description + '\'' +
                ", echeance='" + echeance + '\'' +
                ", dateLimite='" + dateLimite + '\'' +
                ", date='" + date + '\'' +
                ", numContrat='" + numContrat + '\'' +
                ", reference='" + reference + '\'' +
                ", tournee='" + tournee + '\'' +
                ", numClient='" + numClient + '\'' +
                ", nomClient='" + nomClient + '\'' +
                ", cin='" + cin + '\'' +
                ", tel='" + tel + '\'' +
                ", adresse='" + adresse + '\'' +
                ", compteur='" + compteur + '\'' +
                ", mntHt='" + mntHt + '\'' +
                ", mntTVA='" + mntTVA + '\'' +
                ", mntTimbre='" + mntTimbre + '\'' +
                ", mntPenalite='" + mntPenalite + '\'' +
                ", mntMaj='" + mntMaj + '\'' +
                ", mntTTC='" + mntTTC + '\'' +
                ", mntAvance='" + mntAvance + '\'' +
                ", mntFrais='" + mntFrais + '\'' +
                ", mntFraisTva='" + mntFraisTva + '\'' +
                ", mntFraisHt='" + mntFraisHt + '\'' +
                ", mntFraisTimbre='" + mntFraisTimbre + '\'' +
                ", obligatoire=" + obligatoire +
                ", attachement=" + attachement +
                ", seq=" + seq +
                ", extra=" + extra +
                '}';
    }
}
