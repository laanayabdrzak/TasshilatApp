package m2t.com.tashilatappprototype.ui.signIn;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.LogInResponse;
import m2t.com.tashilatappprototype.common.pojo.Operator;
import m2t.com.tashilatappprototype.common.pojo.User;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utility;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.ui.MainActivity;
import m2t.com.tashilatappprototype.ui.signUp.SignUpActivity;
import m2t.com.tashilatappprototype.ui.transitions.MorphTransform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInDialogActivity extends Activity {

    private static final String TAG = SignInDialogActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_GET_ACCOUNTS = 0;
    private boolean shouldPromptForPermission = false;
    boolean isDismissing = false;
    private ProgressBar loading;
    private ViewGroup container;
    private TextInputLayout usernameLabel;
    private AutoCompleteTextView username;
    private CheckBox permissionPrimer;
    private TextInputLayout passwordLabel;
    private EditText password;
    private TextView motPassOublie;
    private FrameLayout actionsContainer;
    private Button signup;
    private SessionManager sessionManager;
    private DatabaseHandler db;
    private Button login;
    private TextView title;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_dialog);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        loading = findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordLabel = findViewById(R.id.password_float_label);
        usernameLabel = findViewById(R.id.username_float_label);
        permissionPrimer = findViewById(R.id.permission_primer);
        actionsContainer = findViewById(R.id.actions_container);
        container = findViewById(R.id.container);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        title = findViewById(R.id.dialog_title);
        motPassOublie = findViewById(R.id.mot_pass_oublie);
        sessionManager = new SessionManager(this.getApplicationContext());
        db = new DatabaseHandler(this.getApplicationContext());
        setupAccountAutocomplete();

            MorphTransform.setup(this, container,
                    ContextCompat.getColor(this, R.color.background_light),
                    getResources().getDimensionPixelSize(R.dimen.dialog_corners));

        // the primer checkbox messes with focus order so force it
        username.addTextChangedListener(loginFieldWatcher);
        username.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                password.requestFocus();
                return true;
            }
            return false;
        });
        password.addTextChangedListener(loginFieldWatcher);
        password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && isLoginValid()) {
                login.performClick();
                return true;
            }
            return false;
        });

        motPassOublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.tasshilat.ma/")));
            }
        });

    }
    @Override @SuppressLint("NewApi")
    public void onEnterAnimationComplete() {
        /* Postpone some of the setup steps so that we can run it after the enter transition (if
        there is one). Otherwise we may show the permissions dialog or account dropdown during the
        enter animation which is jarring. */
        if (shouldPromptForPermission) {
            requestPermissions(new String[]{Manifest.permission.GET_ACCOUNTS},
                    PERMISSIONS_REQUEST_GET_ACCOUNTS);
            shouldPromptForPermission = false;
        }
        username.setOnFocusChangeListener((v, hasFocus) -> maybeShowAccounts());
        maybeShowAccounts();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        dismiss(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void dismiss(View view) {
        isDismissing = true;
        setResult(Activity.RESULT_CANCELED);
        finishAfterTransition();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void signup(View view) {
        Intent i = new Intent(SignInDialogActivity.this, SignUpActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void doLogin(View view) {
        Utility.hideSoftKeyboard(SignInDialogActivity.this);
        showLoading();
        login();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void maybeShowAccounts() {
        if (username.hasFocus()
                && username.isAttachedToWindow()
                && username.getAdapter() != null
                && username.getAdapter().getCount() > 0) {
            username.showDropDown();
        }
    }

    private TextWatcher loginFieldWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            login.setEnabled(isLoginValid());
        }
    };

    boolean isLoginValid() {
        return username.length() > 0 && password.length() > 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void showLoginFailed(String msg) {
        Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
        showLogin();
        password.requestFocus();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showLoading() {
        TransitionManager.beginDelayedTransition(container);
        title.setVisibility(View.GONE);
        usernameLabel.setVisibility(View.GONE);
        permissionPrimer.setVisibility(View.GONE);
        passwordLabel.setVisibility(View.GONE);
        actionsContainer.setVisibility(View.GONE);
        motPassOublie.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showLogin() {
        TransitionManager.beginDelayedTransition(container);
        title.setVisibility(View.VISIBLE);
        usernameLabel.setVisibility(View.VISIBLE);
        passwordLabel.setVisibility(View.VISIBLE);
        actionsContainer.setVisibility(View.VISIBLE);
        motPassOublie.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    @SuppressLint("NewApi")
    private void setupAccountAutocomplete() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) ==
                PackageManager.PERMISSION_GRANTED) {
            permissionPrimer.setVisibility(View.GONE);
            final Account[] accounts = AccountManager.get(this).getAccounts();
            final Set<String> emailSet = new HashSet<>();
            for (Account account : accounts) {
                if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
                    emailSet.add(account.name);
                }
            }
            username.setAdapter(new ArrayAdapter<>(this,
                    R.layout.account_dropdown_item, new ArrayList<>(emailSet)));
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.GET_ACCOUNTS)) {
                setupPermissionPrimer();
            } else {
                permissionPrimer.setVisibility(View.GONE);
                shouldPromptForPermission = true;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setupPermissionPrimer() {
        permissionPrimer.setChecked(false);
        permissionPrimer.setVisibility(View.VISIBLE);
        permissionPrimer.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                requestPermissions(new String[]{ Manifest.permission.GET_ACCOUNTS },
                        PERMISSIONS_REQUEST_GET_ACCOUNTS);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        String email = username.getText().toString();
        String pasword = password.getText().toString();
        final String token = getIntent().getStringExtra("TOKEN");

        // TODO: Implement your own authentication logic here.
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<LogInResponse> call = apiService.setLogIn(new User(email, pasword), token);
        call.enqueue(new Callback<LogInResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @NonNull
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {

                Log.d("ERROOR", response.toString());
                if (response.code() == 400) {
                    showLoginFailed("Une erreur s'est produite merci de reessayer plus tard !");

                } else if (response.body().getCoderet()!= null && response.body().getCoderet().equals("0")) {
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
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    showLoginFailed("Erreur Code : " + response.body().getCoderet()
                            + "\nMessage : " + response.body().getMessage());
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Log.d("Login Error ", t.toString());
                showLoginFailed(t.toString());

            }
        });
    }

    public void onLoginSuccess() {
        finish();
        Intent i = new Intent(SignInDialogActivity.this, MainActivity.class);
        i.putExtra("FLAG", "client");
        startActivity(i);
    }
}

