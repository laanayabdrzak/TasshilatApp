package m2t.com.tashilatappprototype.ui.accueil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.ui.MainActivity;
import m2t.com.tashilatappprototype.ui.signIn.SignInActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccueilActivity extends AppCompatActivity {

    private TextView txtOne, txtVTwo;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        txtVTwo = (TextView) findViewById(R.id.txt_two);
        txtOne = (TextView) findViewById(R.id.txt_one);


        txtOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccueilActivity.this, MainActivity.class);
                i.putExtra("FLAG", "notClient");
                i.putExtra("TOKEN",token);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        txtVTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccueilActivity.this, SignInActivity.class);
                i.putExtra("TOKEN",token);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<String> call = apiService.getToken();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // get header value

                String cookie = response.headers().get("Set-Cookie");
                String[] parts = cookie.split(";");

                token = parts[0];
                Log.d("Token ", parts[0]);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Response onFailure:", t.toString());
            }
        });

    }
}
