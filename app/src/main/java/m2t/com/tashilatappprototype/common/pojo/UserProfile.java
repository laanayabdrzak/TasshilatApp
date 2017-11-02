package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 10/27/17.
 */

public class UserProfile {

    @SerializedName("Theme")
    private String theme;
    @SerializedName("ListOper")
    private ListOper listOper;

    public UserProfile() {
    }

    public UserProfile(String theme, ListOper listOper) {
        this.theme = theme;
        this.listOper = listOper;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public ListOper getListOper() {
        return listOper;
    }

    public void setListOper(ListOper listOper) {
        this.listOper = listOper;
    }
}
