package com.example.personalinstructor_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {



    EditText name, mail, age, weight, height,password;
    Button btn;
    FirebaseAuth fauth;

    private String curent_user_id;

    FirebaseFirestore firebaseFirestore;



    userDetails users;
    String userId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);




        fauth=FirebaseAuth.getInstance();
        curent_user_id = fauth.getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();





/////////////////////////////////////////////////////////

        users = new userDetails();

//////////////////////////////////////////////////////////////////
        name = (EditText) findViewById(R.id.nametxt);
        mail = (EditText) findViewById(R.id.mailtxt);
        age = (EditText) findViewById(R.id.agetxt);
        weight = (EditText) findViewById(R.id.weighttxt);
        height = (EditText) findViewById(R.id.heighttxt);

        password = (EditText) findViewById(R.id.passtxt);

        btn = (Button) findViewById(R.id.btn_next);

//////////////////////////////////////////////////////////////////

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String namex=name.getText().toString();
                final String agex=age.getText().toString();
                final String emalexx=mail.getText().toString();
                final String passxx=password.getText().toString();
                final String weightx=weight.getText().toString();
                final String heightx=height.getText().toString();


            /*    if(fauth.getCurrentUser()!= null){

                    Intent mainIntent = new Intent(Registration.this,login01.class);
                    startActivity(mainIntent);
                    finish();

                }*/



                 if(TextUtils.isEmpty(emalexx)){
                    mail.setError("email required");
                    mail.requestFocus();
                }


                else if(TextUtils.isEmpty(passxx)){
                    password.setError("email required");
                    password.requestFocus();
                }

                else if(emalexx.isEmpty() && passxx.isEmpty()){
                    password.setError("Add data to the field");
                    password.requestFocus();
                }

                else if(!(emalexx.isEmpty() && passxx.isEmpty())) {


                     Bundle bundle = new Bundle();
                     bundle.putString("name",namex);
                     bundle.putString("age",agex);
                     bundle.putString("mail",emalexx);
                     bundle.putString("pass",passxx);
                     bundle.putString("we",weightx);
                     bundle.putString("he",heightx);


                     Intent intent = new Intent(Registration.this, registrationtwo.class);
                     intent.putExtras(bundle);
                     startActivity(intent);









////////////////////////////////////////////////////////////////////////////////////////
                     //////////////////////////////////////////////////////////////////////////
                }


            }
        });


       /* reff = FirebaseDatabase.getInstance().getReference().child("Users");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                namex = name.getText().toString().trim();
                mailx = mail.getText().toString().trim();
                agex = age.getText().toString().trim();
                weightx = weight.getText().toString().trim();
                heightx = height.getText().toString().trim();

                users.setName(namex);
                users.setAge(agex);
                users.setMail(mailx);
                users.setHeight(heightx);
                users.setWeight(weightx);

                reff.push().setValue(users);


                Intent mainIntent = new Intent(Registration.this,useractivity.class);
                startActivity(mainIntent);

                

            }
        });*/


    }
}
/*  Intent intent=new Intent(MainActivity.this, registrationtwo.class);
                intent.putExtra("name",namex);
                intent.putExtra("mail",mailx);
                intent.putExtra("age",agex);
                intent.putExtra("weight",weightx);
                intent.putExtra("height",heightx);

                startActivity(intent);
               */


