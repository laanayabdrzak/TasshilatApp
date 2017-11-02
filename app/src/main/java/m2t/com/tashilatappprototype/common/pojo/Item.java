package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 10/27/17.
 */

public class Item {

    @SerializedName("ID")
    private String ID;
    @SerializedName("Name")
    private String name;

    public Item() {
    }

    public Item(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
