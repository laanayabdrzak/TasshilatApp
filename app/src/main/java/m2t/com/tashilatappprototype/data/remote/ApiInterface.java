package m2t.com.tashilatappprototype.data.remote;

import m2t.com.tashilatappprototype.common.pojo.CodeCenReq;
import m2t.com.tashilatappprototype.common.pojo.CodeCenRes;
import m2t.com.tashilatappprototype.common.pojo.EncaisseRequest;
import m2t.com.tashilatappprototype.common.pojo.EncaisseResponse;
import m2t.com.tashilatappprototype.common.pojo.FacturieRequest;
import m2t.com.tashilatappprototype.common.pojo.FacturieResponse;
import m2t.com.tashilatappprototype.common.pojo.LogOutResponse;
import m2t.com.tashilatappprototype.common.pojo.LogInResponse;
import m2t.com.tashilatappprototype.common.pojo.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by laanaya on 10/4/17.
 */

public interface ApiInterface {

    @GET("filltoken/7001/laanaya")
    Call<String> getToken();

    @POST("connect")
    Call<LogInResponse> setLogIn(@Body User user, @Header("Cookie") String cookie);

    @POST("deconnect")
    Call<LogOutResponse> setLogOut(@Header("Cookie") String cookie);

    @POST("/getListFactures")
    Call<FacturieResponse> getListFactures(@Body FacturieRequest facturieRequest, @Header("Cookie") String cookie);

    @POST("/encaisser")
    Call<EncaisseResponse> encaisserFacture(@Body EncaisseRequest encaisseFacture, @Header("Cookie") String cookie);

    @POST("/getParams")
    Call<CodeCenRes> getCodeCenter(@Body CodeCenReq codeCenReq, @Header("Cookie") String cookie);

}
