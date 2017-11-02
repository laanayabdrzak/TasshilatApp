package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 10/27/17.
 */


public class OperatorWS {


    @SerializedName("Id")
    private String ID_OPER;
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;
    @SerializedName("isFavorite")
    private Boolean isFavorite;
    @SerializedName("categories")
    private Categories categorie;



    public String getID_OPER() {
        return ID_OPER;
    }

    public void setID_OPER(String ID_OPER) {
        this.ID_OPER = ID_OPER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Categories getCategorie() {
        return categorie;
    }

    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }
}
