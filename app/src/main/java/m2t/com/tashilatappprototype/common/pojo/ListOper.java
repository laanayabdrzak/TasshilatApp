package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by laanaya on 10/31/17.
 */

public class ListOper {

    @SerializedName("Oper")
    private List<OperatorWS> operatorWSList;

    public ListOper(List<OperatorWS> operatorWSList) {
        this.operatorWSList = operatorWSList;

    }

    public List<OperatorWS> getOperatorWSList() {
        return operatorWSList;
    }

    public void setOperatorWSList(List<OperatorWS> operatorWSList) {
        this.operatorWSList = operatorWSList;
    }
}
