package m2t.com.tashilatappprototype.common.pojo;

/**
 * Created by laanaya on 1/30/18.
 */

public class Balance {

    private String title;
    private String numAcc;
    private int isUp;
    private String solde;

    public Balance(String title, String numAcc, int isUp, String solde) {
        this.title = title;
        this.numAcc = numAcc;
        this.isUp = isUp;
        this.solde = solde;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumAcc() {
        return numAcc;
    }

    public void setNumAcc(String numAcc) {
        this.numAcc = numAcc;
    }

    public int getIsUp() {
        return isUp;
    }

    public void setIsUp(int isUp) {
        this.isUp = isUp;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }
}
