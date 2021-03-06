package com.example.tung.hireme.Activities.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tung.hireme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    private Button register;
    private EditText mEmail, mPassword, mName;
    private RadioGroup radioGroup;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register = (Button) findViewById(R.id.register);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mName = (EditText) findViewById(R.id.name);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        auth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                int selectedType = radioGroup.getCheckedRadioButtonId();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            }
        };


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectId = radioGroup.getCheckedRadioButtonId();

                final RadioButton radioButton = (RadioButton) findViewById(selectId);
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String name = mName.getText().toString();

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistrationActivity.this, "Sign up Error", Toast.LENGTH_SHORT).show();
                                } else {
                                    String userId = auth.getCurrentUser().getUid();
                                    DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference()
                                            .child("Users")
                                            .child(userId);
                                    Map userInfo = new HashMap<>();
                                    userInfo.put("name", name);
                                    userInfo.put("type", radioButton.getText().toString());
                                    userInfo.put("profileImageUrl", "default");
                                    userInfo.put("summary", " ");
                                    currentUserDb.updateChildren(userInfo);

                                    Intent intent = new Intent(RegistrationActivity.this, EmptyActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
