package m2t.com.tashilatappprototype.ui.accueil;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.ui.signIn.SignInDialogActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccueilActivity extends AppCompatActivity {

    private TextView authViaUserId, notClient;
    private String token;
    private static final int RC_NEW_DESIGNER_NEWS_LOGIN = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        authViaUserId = findViewById(R.id.auth_via_user_id);
        notClient = findViewById(R.id.not_client);
        getAccessToken();
        authViaUserId.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccueilActivity.this, SignInDialogActivity.class);
                intent.putExtra("TOKEN",token);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AccueilActivity.this, authViaUserId,
                        getString(R.string.transition_designer_news_login));
                startActivityForResult(intent, RC_NEW_DESIGNER_NEWS_LOGIN, options.toBundle());
            }
        });

        notClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(AccueilActivity.this, MainActivity.class);
                i.putExtra("FLAG", "notClient");
                i.putExtra("TOKEN",token);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);*/
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getAccessToken() {
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
