package com.example.loginfire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class regActivity extends AppCompatActivity {
    private TextView email;
    private TextView pass;
    private Button regbutton;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        email = (TextView) findViewById(R.id.email);
        pass = (TextView) findViewById(R.id.pass);
        regbutton = (Button) findViewById(R.id.regbutton);

        auth = FirebaseAuth.getInstance();

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email = email.getText().toString();
                String text_pass = pass.getText().toString();

                if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_pass)) {
                    Toast.makeText(regActivity.this, "fill the above !", Toast.LENGTH_SHORT).show();
                } else if (text_pass.length() < 6) {
                    Toast.makeText(regActivity.this, "Password is too short !", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(text_email, text_pass);

                }
            }
        });
    }

    private void registerUser(String email, String pass) {

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(regActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(regActivity.this, "Registration is successful !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(regActivity.this, "Register is un successful !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

        );}
}
