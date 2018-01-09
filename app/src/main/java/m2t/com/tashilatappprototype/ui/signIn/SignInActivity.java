package m2t.com.tashilatappprototype.ui.signIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.LogInResponse;
import m2t.com.tashilatappprototype.common.pojo.Operator;
import m2t.com.tashilatappprototype.common.pojo.User;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.ui.MainActivity;
import m2t.com.tashilatappprototype.ui.accueil.AccueilActivity;
import m2t.com.tashilatappprototype.ui.signUp.SignUpActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInActivity extends AppCompatActivity {

    private static final String TAG = SignInActivity.class.getSimpleName();
    private static final int REQUEST_SIGNUP = 0;
    SessionManager sessionManager;
    DatabaseHandler db;
    private Button signInBtn;
    private TextView linkSignUp, linkHome;
    private EditText emailText, passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailText = (EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        signInBtn = (Button) findViewById(R.id.sign_in_btn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                //onLoginSuccess();
            }
        });

        linkSignUp = (TextView) findViewById(R.id.link_signup);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        linkHome = (TextView) findViewById(R.id.link_home);
        linkHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccueilActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        sessionManager = new SessionManager(getApplicationContext());
        db = new DatabaseHandler(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void login() {
        Log.d(TAG, "Login");

        /*if (!validate()) {
            onLoginFailed();
            return;
        }*/

        signInBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("S'authentifier...");
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        final String token = getIntent().getStringExtra("TOKEN");

        // TODO: Implement your own authentication logic here.
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<LogInResponse> call = apiService.setLogIn(new User(email, password), token);
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if (response.body().getCoderet().equals("0")) {
                    sessionManager.createLoginSession(response.headers().get("Set-Cookie").split(";")[0],
                            response.body().getCodeES(),
                            response.body().getTokenCSFR(),
                            token,
                            response.body().getAccountType(),
                            response.body().getSolde());
                    if (response.body().getUserProfile() != null) {
                        List<Operator> operators =
                                Utils.fromWSToDB(response.body().getUserProfile().getListOper().getOperatorWSList());
                        for (Operator op : operators) {
                            Log.i("Operator name: ", op.getName() + " id = " + op.getID_OPER());
                            db.addOperator(op);
                        }
                    }

                    else Log.d(TAG, "null object reference");

                    onLoginSuccess();
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur Code : "+ response.body().getCoderet()
                                    + "\nMessage : "+response.body().getMessage(),
                            Toast.LENGTH_LONG).show();
                    signInBtn.setEnabled(true);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Log.d("Login Error ", t.toString());
                onLoginFailed();
                progressDialog.dismiss();
            }
        });
    }

    public void onLoginSuccess() {
        signInBtn.setEnabled(true);
        finish();
        Intent i = new Intent(SignInActivity.this, MainActivity.class);
        i.putExtra("FLAG", "client");
        startActivity(i);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login Error :Requette non valide", Toast.LENGTH_LONG).show();
        signInBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Entrer une adresse email valide");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("Entrer entre 4 et 10 alphanumeric");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
