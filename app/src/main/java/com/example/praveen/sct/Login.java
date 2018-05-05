package com.example.praveen.sct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin, username_clear, password_clear, btn_singup;
    TextView forget_password;

    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "125";


    GoogleSignInClient mGoogleSignInClient;


    FirebaseAuth mfirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mfirebaseAuth = FirebaseAuth.getInstance();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);


        btnLogin = (Button) findViewById(R.id.btn_login);
        btn_singup = (Button) findViewById(R.id.btn_register);
        username_clear = (Button) findViewById(R.id.username_clear);
        password_clear = (Button) findViewById(R.id.password_clear);
        forget_password = (TextView) findViewById(R.id.forget);

        password_clear.setVisibility(View.GONE);
        username_clear.setVisibility(View.GONE);
        etEmail.addTextChangedListener(textWatcher());
        etPassword.addTextChangedListener(textWatcher());

        btnLoginClick();
        btnForgetClick();
        btn_clearClick();
        btn_clear2Click();
        btn_singupClick();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        findViewById(R.id.sing_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mfirebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, Home.class));
            finish();
        }
    }

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        if (mfirebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, Home.class));
            finish();
        }


    }


    private void btn_singupClick() {

        btn_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registration.class));

            }
        });
    }

    private TextWatcher textWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etEmail.getText().toString().length() != 0) {
                    username_clear.setVisibility(View.VISIBLE);


                }
                if (etPassword.getText().toString().length() != 0) {
                    password_clear.setVisibility(View.VISIBLE);
                }
                if (etEmail.getText().toString().length() == 0) {

                    username_clear.setVisibility(View.GONE);
                }
                if (etPassword.getText().toString().length() == 0) {
                    password_clear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void btn_clear2Click() {

        username_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEmail.setText("");
            }
        });
    }

    private void btn_clearClick() {
        password_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPassword.setText("");
            }
        });
    }

    private void btnForgetClick() {
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Forget_Password.class));
            }
        });
    }

    private void btnLoginClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("This field required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    // Toast.makeText(MainActivity.this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
                    etPassword.setError("This field required");
                    return;
                }
                final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "Please Wait", "Progressing...", true);
                (mfirebaseAuth.signInWithEmailAndPassword(email, password))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Login.this, Home.class);
                                    i.putExtra("Email", mfirebaseAuth.getCurrentUser().getEmail());
                                    startActivity(i);
                                    finish();
                                } else {

                                    Toast.makeText(Login.this, "please retry", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mfirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mfirebaseAuth.getCurrentUser();

                            Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,Home.class));
                        } else {

                            Toast.makeText(Login.this, " Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

