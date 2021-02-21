package com.example.personalinstructor_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


import javax.annotation.Nullable;

import static java.lang.System.load;

public class useractivity extends AppCompatActivity {


    String uid;

    TextView x1,x2,x3,x4;
    String nm;

    ImageView imgkk;

    FirebaseAuth fauth;
    FirebaseFirestore ffirebase;
    String url;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);
        imgkk=findViewById(R.id.immid);
        x1=(TextView) findViewById(R.id.name);
        x2=findViewById(R.id.weight);
        x3=findViewById(R.id.height);
        x4=findViewById(R.id.tt);


        fauth=FirebaseAuth.getInstance();
        ffirebase=FirebaseFirestore.getInstance();
///////////////////////////////////////////////////////////////////////
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            uid = bundle.getString("uid");
            Toast.makeText(useractivity.this,""+uid,Toast.LENGTH_SHORT).show();
        }
 /////////////////////////////////////////////////////////////////////

        DocumentReference documentReference=ffirebase.collection("users").document(uid);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                x1.setText(documentSnapshot.getString("mail"));
                x2.setText(documentSnapshot.getString("weight"));
                x3.setText(documentSnapshot.getString("height"));
                nm=documentSnapshot.getString("name");
                url=documentSnapshot.getString("userimage");

                x4.setText("Welcome "+nm);


                Glide.with(useractivity.this)
                    .load(url)
                    .into(imgkk);

            }
        });







    }
}
