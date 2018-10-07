package m2t.com.tashilatappprototype.common.pojo;

import com.google.gson.annotations.SerializedName;

public class ConsultationTrxRes {

    @SerializedName("MsgError")
    private String msgError;
    @SerializedName("CodeError")
    private String codeError;
    @SerializedName("AgentCode")
    private String agentCode;
    @SerializedName("Transactions")
    private String transactions;
    @SerializedName("dateTrx")
    private String dateTrx;


}
