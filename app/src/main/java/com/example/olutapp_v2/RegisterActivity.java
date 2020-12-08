package com.example.olutapp_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olutapp_v2.ui.login.LoginActivity;
import com.example.olutapp_v2.ui.login.MainActivity;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    TextView btn;
    private EditText inputUsername,inputEmail,inputPassword,inputConformPassword;
    Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("OlutApp");

        btn=findViewById(R.id.allreadyRegistered);
        inputUsername=findViewById(R.id.inputUsername);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputConformPassword=findViewById(R.id.inputConformPassword);
        mAuth= FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(RegisterActivity.this);
        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });

    }

    private void checkCredentials(){
        String username=inputUsername.getText().toString();
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String conformPassword=inputConformPassword.getText().toString();

        if (username.isEmpty() || username.length()<4)
        {
            showError(inputUsername,"Käyttäjänimessä pitää olla vähintään 4 merkkiä");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"Väärä sähköposti!");
        }
        else if (password.isEmpty() || password.length()<5)
        {
            showError(inputPassword,"Salasanassa pitää olla vähintään 5 merkkiä!");
        }
        else if (conformPassword.isEmpty() || !conformPassword.equals(password))
        {
            showError(inputConformPassword,"Salasana ei täsmää!");
        }
        else
        {
            mLoadingBar.setTitle("Rekisteröityminen");
            mLoadingBar.setTitle("Hetkinen...");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful())
                   {
                       Toast.makeText(RegisterActivity.this,"Rekisteröitymien onnistui!",Toast.LENGTH_SHORT).show();
                       mLoadingBar.dismiss();
                       Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(RegisterActivity.this,"Tarkista sähköpostiosoite tai salasana",Toast.LENGTH_SHORT).show();
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

}