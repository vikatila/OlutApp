package com.example.olutapp_v2.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olutapp_v2.R;
import com.example.olutapp_v2.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView btn;
    private EditText inputEmail,inputPassword;
    Button btnlogin;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        mAuth= FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(LoginActivity.this);
        btnlogin=findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });

    }

    private void checkCredentials(){
        final String email=inputEmail.getText().toString();
        final String password=inputPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"Väärä sähköposti!");
        }
        else if (password.isEmpty() || password.length()<5)
        {
            showError(inputPassword,"Salasanassa pitää olla vähintään 5 merkkiä!");
        }
        else
            {
                        mLoadingBar.setTitle("Kirjaudutaan");
                        mLoadingBar.setMessage("Hetkinen...");
                        mLoadingBar.setCanceledOnTouchOutside(false);
                        mLoadingBar.show();

                        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    mLoadingBar.dismiss();
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                }
                                else
                                {

                                    Toast.makeText(LoginActivity.this,"Tarkista sähköpostiosoite tai salasana",Toast.LENGTH_SHORT).show();
                                    mLoadingBar.dismiss();

                                }
                            }
                        });
                    }

                }

    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();

    }





    public void goRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}