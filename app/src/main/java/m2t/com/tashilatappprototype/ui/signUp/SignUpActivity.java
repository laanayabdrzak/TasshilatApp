package m2t.com.tashilatappprototype.ui.signUp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import m2t.com.tashilatappprototype.R;

public class SignUpActivity extends AppCompatActivity {

    private AppCompatButton signUpBtn;
    private CheckBox termsConditionsTv;
    private TextView termsTv;
    private TextInputLayout nameSignupLabel;
    private AutoCompleteTextView nameSignup;
    private TextInputLayout mobileSignupLabel;
    private AutoCompleteTextView mobileSignup;
    private TextInputLayout emailSignupLabel;
    private AutoCompleteTextView emailSignup;
    private TextInputLayout passwordSignupLabel;
    private EditText passwordSignup;
    private TextInputLayout confirmPasswordSignupLabel;
    private EditText confirmPassword;
    private CheckBox permissionTerms;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        termsConditionsTv = findViewById(R.id.permission_agremnt);
        nameSignup = findViewById(R.id.name_complet);
        nameSignupLabel = findViewById(R.id.name_complet_float_label_sign_up);
        mobileSignup = findViewById(R.id.mobile_number);
        mobileSignupLabel = findViewById(R.id.mobile_number_float_label_sign_up);
        emailSignup = findViewById(R.id.email_sign_up);
        emailSignupLabel = findViewById(R.id.email_float_label_sign_up);
        passwordSignup  = findViewById(R.id.password_sign_up);
        passwordSignupLabel  = findViewById(R.id.password_sign_up_float_label);
        confirmPassword = findViewById(R.id.password_confirm);
        confirmPasswordSignupLabel = findViewById(R.id.confirm_password_float_label);
        permissionTerms = findViewById(R.id.permission_agremnt);
        register = findViewById(R.id.register);

        termsTv = findViewById(R.id.terms_tv);
        SpannableString terms = new SpannableString(getResources().getString(R.string.termsetservices));
        terms.setSpan(new UnderlineSpan(),21,terms.length(),0);
        terms.setSpan(new URLSpan("http://www.google.com"), 21, terms.length(), 0);
        terms.setSpan(new StyleSpan(Typeface.BOLD), 21, terms.length(), 0);
        terms.setSpan(new ForegroundColorSpan(Color.BLUE), 21, terms.length(), 0);
        termsTv.setText(terms);
        termsTv.setMovementMethod(LinkMovementMethod.getInstance());

        nameSignup.addTextChangedListener(registerFieldWatcher);
        mobileSignup.addTextChangedListener(registerFieldWatcher);
        emailSignup.addTextChangedListener(registerFieldWatcher);
        passwordSignup.addTextChangedListener(registerFieldWatcher);
        confirmPassword.addTextChangedListener(registerFieldWatcher);
    }

    private TextWatcher registerFieldWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            register.setEnabled(isRegisterValid());
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    boolean isRegisterValid(){
        return nameSignup.length()>0 && mobileSignup.length()>0
                && emailSignup.length()>0 && passwordSignup.length()>0
                && confirmPassword.length()>0 && permissionTerms.isChecked();
    }

    /*public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }*/
}
