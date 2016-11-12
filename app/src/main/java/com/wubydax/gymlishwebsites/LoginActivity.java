package com.wubydax.gymlishwebsites;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements TextWatcher{

    private static final String[] LOGIN_CREDENTIALS = {"gymlish", "admin"}; //Fake login credentials
    private TextInputEditText mUsername, mPassword;
    private TextInputLayout mUserLayout, mPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setContentView(R.layout.activity_login);
        mUsername = (TextInputEditText) findViewById(R.id.username_input);
        mPassword = (TextInputEditText) findViewById(R.id.password_input);
        mUserLayout = (TextInputLayout) findViewById(R.id.username_layout);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.password_layout);

        mUsername.addTextChangedListener(this);
        mPassword.addTextChangedListener(this);

        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    submitLogin();
                    return true;
                }
                return false;
            }
        });

        (findViewById(R.id.login_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitLogin();
            }
        });
    }

    private void submitLogin() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        int count = 0; // count of 2 means both fields are a match. To avoid additional comparison before logging in

        if (TextUtils.isEmpty(username) || !username.equalsIgnoreCase(LOGIN_CREDENTIALS[0])) {
            mUserLayout.setError(getString(R.string.invalid_username_error));

        } else {
            mUserLayout.setError(null);
            count++;
        }

        if (TextUtils.isEmpty(password) || !password.equalsIgnoreCase(LOGIN_CREDENTIALS[1])) {
            mPasswordLayout.setError(getString(R.string.invalid_password_error));

        } else {
            mPasswordLayout.setError(null);
            count++;
        }

        if(count == 2) { //both credentials are a match
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //No action needed
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //Resetting the error fields to empty when attempt to retype is made
        mUserLayout.setError(null);
        mPasswordLayout.setError(null);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //No action neede
    }
}
