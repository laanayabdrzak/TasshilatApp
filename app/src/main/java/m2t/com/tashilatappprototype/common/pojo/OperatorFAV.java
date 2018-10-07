package m2t.com.tashilatappprototype.common.pojo;

import java.util.List;

/**
 * Created by laanaya on 10/27/17.
 */


public class OperatorFAV {


    private String ID_OPER;

    private String name;

    private String description;

    private int isFavorite;

    private String categorie_id;

    private String categorie_name;

    private List<Categories> categoriesList;

    private String typeIdent;

    private String ident;

    private String payment;

    public OperatorFAV() {
    }

    public OperatorFAV(String ID_OPER, String name, String description) {
        this.ID_OPER = ID_OPER;
        this.name = name;
        this.description = description;
    }

    public OperatorFAV(String ID_OPER, String name, String description, int isFavorite, String categorie_id, String categorie_name, List<Categories> categoriesList, String typeIdent, String ident, String payment) {
        this.ID_OPER = ID_OPER;
        this.name = name;
        this.description = description;
        this.isFavorite = isFavorite;
        this.categorie_id = categorie_id;
        this.categorie_name = categorie_name;
        this.categoriesList = categoriesList;
        this.typeIdent = typeIdent;
        this.ident = ident;
        this.payment = payment;
    }

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

    public int getFavorite() {
        return isFavorite;
    }

    public void setFavorite(int favorite) {
        isFavorite = favorite;
    }

    public String getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getCategorie_name() {
        return categorie_name;
    }

    public void setCategorie_name(String categorie_name) {
        this.categorie_name = categorie_name;
    }

    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public String getTypeIdent() {
        return typeIdent;
    }

    public void setTypeIdent(String typeIdent) {
        this.typeIdent = typeIdent;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "OperatorFAV{" +
                "ID_OPER='" + ID_OPER + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isFavorite=" + isFavorite +
                ", categorie_id='" + categorie_id + '\'' +
                ", categorie_name='" + categorie_name + '\'' +
                ", categoriesList=" + categoriesList +
                ", typeIdent='" + typeIdent + '\'' +
                ", ident='" + ident + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
