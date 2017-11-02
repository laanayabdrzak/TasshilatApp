package m2t.com.tashilatappprototype.common.pojo;

/**
 * Created by laanaya on 8/11/17.
 */

public class Account {

    private String name;
    private int uan;
    private String thumbnail;
    private float solde;

    public Account() {
    }

    public Account(String name, int uan, String thumbnail) {
        this.name = name;
        this.uan = uan;
        this.thumbnail = thumbnail;
    }

    public Account(String name, int uan, float solde) {
        this.name = name;
        this.uan = uan;
        this.solde = solde;
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
}
