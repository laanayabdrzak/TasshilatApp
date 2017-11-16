package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 11/7/17.
 */

public class SearchForm {


    @SerializedName("search_crit")
    private String searchCrit;
    @SerializedName("nopolice")
    private String nopolice;
    @SerializedName("nocompteur")
    private String nocompteur;
    @SerializedName("notournee")
    private String notournee;
    @SerializedName("nofacture")
    private String nofacture;
    @SerializedName("nocin")
    private String nocin;


    public SearchForm() {
    }

    public String getSearchCrit() {
        return searchCrit;
    }

    public void setSearchCrit(String searchCrit) {
        this.searchCrit = searchCrit;
    }

    public String getNopolice() {
        return nopolice;
    }

    public void setNopolice(String nopolice) {
        this.nopolice = nopolice;
    }

    public String getNocompteur() {
        return nocompteur;
    }

    public void setNocompteur(String nocompteur) {
        this.nocompteur = nocompteur;
    }

    public String getNotournee() {
        return notournee;
    }

    public void setNotournee(String notournee) {
        this.notournee = notournee;
    }

    public String getNofacture() {
        return nofacture;
    }

    public void setNofacture(String nofacture) {
        this.nofacture = nofacture;
    }

    public String getNocin() {
        return nocin;
    }

    public void setNocin(String nocin) {
        this.nocin = nocin;
    }
}
