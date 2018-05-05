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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    EditText etREmail,etRPassword;
    Button btn_submit,Rpassword_clear,Rusername_clear;
    private FirebaseAuth mfirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etREmail=(EditText)findViewById(R.id.etREmail);
        etRPassword=(EditText)findViewById(R.id.etRPassword);
        btn_submit=(Button)findViewById(R.id.btn_submit);

        Rpassword_clear=(Button)findViewById(R.id.Rpassword_clear);
        Rusername_clear=(Button)findViewById(R.id.Rusername_clear);

        Rpassword_clear.setVisibility(View.GONE);
        Rusername_clear.setVisibility(View.GONE);

        etREmail.addTextChangedListener(textWatcher());
        etRPassword.addTextChangedListener(textWatcher());

        btn_Rpassword_click();
        btn_Rusername_click();

        mfirebaseAuth=FirebaseAuth.getInstance();

        registration();
    }

    private void btn_Rusername_click() {
        Rusername_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etREmail.setText("");
            }
        });
    }

    private void btn_Rpassword_click() {
        Rpassword_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRPassword.setText("");
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
                if (etREmail.getText().toString().length() != 0) {
                    Rusername_clear.setVisibility(View.VISIBLE);


                }
                if (etRPassword.getText().toString().length() != 0) {
                    Rpassword_clear.setVisibility(View.VISIBLE);
                }
                if (etREmail.getText().toString().length() == 0) {

                    Rusername_clear.setVisibility(View.GONE);
                }
                if (etRPassword.getText().toString().length() == 0) {
                    Rpassword_clear.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void registration() {

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= etREmail.getText().toString();
                String password= etRPassword.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    //Toast.makeText(Registration.this,"Enter Vaild Email address",Toast.LENGTH_SHORT).show();
                    etREmail.setError("Enter Vaild Email");
                    return;
                }

                if (TextUtils.isEmpty(password)){

                    Toast.makeText(Registration.this,"Password Empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<4){
                    Toast.makeText(Registration.this,"Password must min 5 characters",Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog progressDialog=ProgressDialog.show(Registration.this,"Please Wait","processing",true);

                (mfirebaseAuth.createUserWithEmailAndPassword(email,password)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(Registration.this,"Register Successful",Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(Registration.this,Login.class);
                            startActivity(i);
                            finish();
                        }else {

                            Toast.makeText(Registration.this,"Please retry",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}