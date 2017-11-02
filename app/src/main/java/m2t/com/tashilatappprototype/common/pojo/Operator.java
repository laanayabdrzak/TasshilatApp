package m2t.com.tashilatappprototype.common.pojo;

import java.util.List;

/**
 * Created by laanaya on 10/27/17.
 */


public class Operator {


    private String ID_OPER;

    private String name;

    private String description;

    private int isFavorite;

    private String categorie_id;

    private String categorie_name;

    private List<Categories> categoriesList;

    public Operator() {
    }

    public Operator(String ID_OPER, String name, String description) {
        this.ID_OPER = ID_OPER;
        this.name = name;
        this.description = description;
    }

    public Operator(String ID_OPER, String name, String description, int isFavorite, String categorie_id, String categorie_name) {
        this.ID_OPER = ID_OPER;
        this.name = name;
        this.description = description;
        this.isFavorite = isFavorite;
        this.categorie_id = categorie_id;
        this.categorie_name = categorie_name;
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
}
