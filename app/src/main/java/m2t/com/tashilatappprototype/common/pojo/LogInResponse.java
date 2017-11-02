package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laanaya on 10/27/17.
 */

public class LogInResponse {

    @SerializedName("UserProfile")
    private UserProfile userProfile;
    @SerializedName("CodeES")
    private String codeES;
    @SerializedName("coderet")
    private String coderet;
    @SerializedName("message")
    private String message;
    @SerializedName("tokenCSFR")
    private String tokenCSFR;
    @SerializedName("AccountType")
    private String AccountType;
    @SerializedName("Solde")
    private String Solde;

    public LogInResponse() {
    }

    public LogInResponse(UserProfile userProfile, String codeES, String coderet, String message, String tokenCSFR, String accountType, String solde) {
        this.userProfile = userProfile;
        this.codeES = codeES;
        this.coderet = coderet;
        this.message = message;
        this.tokenCSFR = tokenCSFR;
        AccountType = accountType;
        Solde = solde;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getCodeES() {
        return codeES;
    }

    public void setCodeES(String codeES) {
        this.codeES = codeES;
    }

    public String getCoderet() {
        return coderet;
    }

    public void setCoderet(String coderet) {
        this.coderet = coderet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTokenCSFR() {
        return tokenCSFR;
    }

    public void setTokenCSFR(String tokenCSFR) {
        this.tokenCSFR = tokenCSFR;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getSolde() {
        return Solde;
    }

    public void setSolde(String solde) {
        Solde = solde;
    }
}
