package com.example.praveen.sct;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_Password extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnResetPassword;
    private Button btnBack,forget_email_id_clear;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        edtEmail = (EditText) findViewById(R.id.edt_reset_email);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        forget_email_id_clear=(Button)findViewById(R.id.forget_email_id_clear);

        forget_email_id_clear.setVisibility(View.GONE);
        edtEmail.addTextChangedListener(textWatcher());

        btn_forget_email_id_clear();



        mAuth = FirebaseAuth.getInstance();
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Forget_Password.this, "Check email to reset password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Forget_Password.this, "Fail to send email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forget_Password.this,Login.class));
                finish();
            }
        });
    }

    private void btn_forget_email_id_clear() {

        forget_email_id_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtEmail.setText("");
            }
        });


    }

    private TextWatcher textWatcher() {
        return  new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtEmail.getText().toString().length()!=0){
                    forget_email_id_clear.setVisibility(View.VISIBLE);
                }
                if (edtEmail.getText().toString().length()==0){
                    forget_email_id_clear.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

}