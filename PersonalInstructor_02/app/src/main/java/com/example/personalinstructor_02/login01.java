package com.example.personalinstructor_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login01 extends AppCompatActivity {


    EditText logpass,logemail;
    Button login ;
    TextView regis;

    TextView reg;

    FirebaseAuth mFirebaseAuth;
    String xxx;

    private FirebaseAuth.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login01);


        mFirebaseAuth = FirebaseAuth.getInstance();


        login =(Button) findViewById(R.id.login);
        logemail =(EditText) findViewById(R.id.emailtxt);
        logpass =(EditText) findViewById(R.id.paswrdtxt);
        regis=findViewById(R.id.reg);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    //welcometext.setVisibility(View.INVISIBLE);
                   // logemail.setVisibility(View.INVISIBLE);
                    //logpass.setVisibility(View.INVISIBLE);
                    //login.setVisibility(View.INVISIBLE);
                    //registerlinier.setVisibility(View.INVISIBLE);


                }
                else{
                    Toast.makeText(login01.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };


        ///////////////////////
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = logemail.getText().toString();
                String pwd = logpass.getText().toString();
                if(email.isEmpty()){
                    logemail.setError("Please enter email id");
                    logemail.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    logpass.setError("Please enter your password");
                    logpass.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(login01.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(login01.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(login01.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                                if (mFirebaseUser != null) {

                                    xxx = mFirebaseUser.getUid();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("uid",xxx);



                                    Intent intent = new Intent(login01.this, useractivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }




                            }
                        }
                    });
                }
                else{
                    Toast.makeText(login01.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login01.this, Registration.class);
                startActivity(intent);


            }
        });




    }

    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
