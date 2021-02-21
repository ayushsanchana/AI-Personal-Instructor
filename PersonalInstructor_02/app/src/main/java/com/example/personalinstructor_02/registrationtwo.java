package com.example.personalinstructor_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class registrationtwo extends AppCompatActivity {


    String name, age, mail, height, weight;
    Button btnSignUp;
    FirebaseAuth mFirebaseAuth;
    TextView tvSignIn;
    ProgressDialog progressDialog;
    FirebaseFirestore mfirebaseFirestore;
    String userId;
    Button btn;
    Button bbutton;
    Button imgbtn;
    Button hut;
    ImageView ImgUserPhoto;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;
    String namex,agex,mailx,weightx,heightx,passx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationtwo);


        mfirebaseFirestore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        name = getIntent().getStringExtra("name");
        mail = getIntent().getStringExtra("mail");
        age = getIntent().getStringExtra("age");
        weight = getIntent().getStringExtra("weight");
        height = getIntent().getStringExtra("height");
        btn = (Button) findViewById(R.id.btn_next);
        imgbtn = (Button) findViewById(R.id.img);
        ImgUserPhoto = findViewById(R.id.fuck);
        btnSignUp = findViewById(R.id.btn_next);




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    namex = bundle.getString("name");
                    agex= bundle.getString("age");
                    mailx= bundle.getString("mail");
                    passx= bundle.getString("pass");
                    weightx= bundle.getString("we");
                    heightx= bundle.getString("he");
                }

                    mFirebaseAuth.createUserWithEmailAndPassword(mailx, passx).addOnCompleteListener(registrationtwo.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                userId = mFirebaseAuth.getCurrentUser().getUid();


                                // first we need to upload user photo to firebase storage and get url

                                StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
                                final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
                                imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                        // image uploaded succesfully
                                        // now we can get our image url

                                        imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                // uri contain user image url

                                                String url = uri.toString();


                                                DocumentReference documentReference = mfirebaseFirestore.collection("users").document(userId);
                                                Map<String, Object> user = new HashMap<>();
                                                user.put("Uid", userId);
                                                user.put("name", namex);
                                                user.put("age", agex);
                                                user.put("mail", mailx);
                                                user.put("password", passx);
                                                user.put("weight", weightx);
                                                user.put("height", heightx);
                                                user.put("userimage", url);
                                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        Toast.makeText(registrationtwo.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("Uid", userId);

                                                        Intent intent = new Intent(registrationtwo.this, login01.class);
                                                        intent.putExtras(bundle);
                                                        startActivity(intent);

                                                    }
                                                });
                                                //startActivity(new Intent(registrationtwo.this, useractivity.class));

                                            }

                                        });


                                    }
                                });



/////////////////////////////////////////////////////



















                        }
                    });


            }
        });






        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                }
                else
                {
                    openGallery();
                }





            }
        });




    }


    ////////////////////////////////////////////////////////////////////////////////
    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }
////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////
    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(registrationtwo.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(registrationtwo.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(registrationtwo.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(registrationtwo.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }
///////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData();
            ImgUserPhoto.setImageURI(pickedImgUri);


        }


    }

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////