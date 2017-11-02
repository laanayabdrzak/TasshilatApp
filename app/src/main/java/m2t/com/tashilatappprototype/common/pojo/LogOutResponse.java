package m2t.com.tashilatappprototype.common.pojo;

/**
 * Created by laanaya on 10/27/17.
 */

public class LogOutResponse {

    private  String codeError;
    private String msgError;

    public LogOutResponse(String codeError, String msgError) {
        this.codeError = codeError;
        this.msgError = msgError;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
