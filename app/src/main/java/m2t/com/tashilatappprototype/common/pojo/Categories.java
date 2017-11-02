package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 10/27/17.
 */


public class Categories {

    @SerializedName("Item")
    private Item item;

    public Categories() {
    }

    public Categories(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
