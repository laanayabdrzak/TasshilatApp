package m2t.com.tashilatappprototype.Common.POJO;

/**
 * Created by laanaya on 8/11/17.
 */

public class Account {

    private String name;
    private int uan;
    private int thumbnail;
    private float solde;

    public Account() {
    }

    public Account(String name, int uan, int thumbnail) {
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

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
