package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by laanaya on 1/4/18.
 */

public class CodeCenRes {

    @SerializedName("MsgError")
    private String msgError;
    @SerializedName("CodeError")
    private String codeError;
    @SerializedName("Params")
    private List<Param> params;
    @SerializedName("dateSystem")
    private String dateSystem;

    public CodeCenRes() {
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getDateSystem() {
        return dateSystem;
    }

    public void setDateSystem(String dateSystem) {
        this.dateSystem = dateSystem;
    }
}
