package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 11/8/17.
 */

public class ItemFacture {

    @SerializedName("Name")
    private String name;
    @SerializedName("Value")
    private String value;

    public ItemFacture() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
