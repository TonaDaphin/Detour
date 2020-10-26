package com.example.detour2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Category1Activity extends AppCompatActivity {
    private TextView arch100;
    private ImageView mProfilePhoto;
    private Button verifyEmailBtn;
    private TextView mUserNameC;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseFirestore fStore;
    String userID;

    GoogleSignInClient mGoogleSignInClient;
    Dialog myDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

        arch100 = (TextView) findViewById(R.id.arch100);
        mUserNameC = (TextView) findViewById(R.id.username);
        mProfilePhoto = (ImageView) findViewById(R.id.profilePicture);
        verifyEmailBtn = (Button) findViewById(R.id.verifyButton);

        myDialog = new Dialog(this);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mUserNameC.setText(user.getDisplayName());
                }
                else {

                }
            }
        };

        final FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null){
            String userName = mUser.getDisplayName();
            mUserNameC.setText(userName);

        }
        if (!mAuth.getCurrentUser().isEmailVerified()) {

            verifyEmailBtn.setVisibility(View.VISIBLE);
            verifyEmailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Category1Activity.this,
                                        "Verification Email Sent",
                                        Toast.LENGTH_LONG).show();

                                new CountDownTimer(3000, 1000) {
                                    public void onFinish() {
                                        Toast.makeText(Category1Activity.this,
                                                "Log in again to update your account after verifying",
                                                Toast.LENGTH_LONG).show();
                                    }

                                    public void onTick(long millisUntilFinished) {

                                    }
                                }.start();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Category1Activity.this,
                                        "Error! Email not Sent",
                                        Toast.LENGTH_LONG).show();
                                Log.d("TAG", "onFailure: verification email " + e.getMessage());
                            }
                        });
                    }
                }
            });

        }



        arch100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category1Activity.this, Level1Activity.class);
                startActivity(intent);
            }
        });

        //Initializing Facebook SDK
        FacebookSdk.sdkInitialize(Category1Activity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Category1Activity.this,"Logged Out Successfully!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Category1Activity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void showPopUp(View v){
        Button logOutButton;
        ImageView cancelDialog;
        TextView userNameDialog;
        TextView userEmailDialog;
        TextView userMobileDialog;

        myDialog.setContentView(R.layout.profile_layout);

        logOutButton = (Button) myDialog.findViewById(R.id.logOutButton);
        cancelDialog =(ImageView) myDialog.findViewById(R.id.cancel);
        userNameDialog =(TextView) myDialog.findViewById(R.id.userName);
        userEmailDialog =(TextView) myDialog.findViewById(R.id.userEmail);
        userMobileDialog =(TextView) myDialog.findViewById(R.id.userMobile);


        for (UserInfo user: FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {
            if (user.getProviderId().equals("facebook.com") || user.getProviderId().equals("google.com")) {
                final FirebaseUser mUser = mAuth.getCurrentUser();
                String userName = mUser.getDisplayName();
                String email = mUser.getEmail();
                String phone = mUser.getPhoneNumber();

                userNameDialog.setText(userName);
                userEmailDialog.setText(email);
                userMobileDialog.setText(phone);
            }
            else if (user.getProviderId().equals("password")) {
                userMobileDialog.setVisibility(View.VISIBLE);

                userID = mAuth.getCurrentUser().getUid();

                DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        userNameDialog.setText(documentSnapshot.getString("Username"));
                        userEmailDialog.setText(documentSnapshot.getString("Email"));
                        userMobileDialog.setText(documentSnapshot.getString("Phone"));
                    }
                });
            }
        }

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                // Facebook sign out
                LoginManager.getInstance().logOut();
                // Google sign out
                mGoogleSignInClient.signOut();
                finish();
            }
        });

        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }


}
