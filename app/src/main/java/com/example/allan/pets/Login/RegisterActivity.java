package com.example.allan.pets.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allan.pets.R;
import com.example.allan.pets.Util.FirebaseMethod;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity{

    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;
    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private Button btnRegister;
    private FirebaseMethod firebaseMethod;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private String append = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: starting");
        //mAuth = FirebaseAuth.getInstance();
        mContext = RegisterActivity.this;
        firebaseMethod = new FirebaseMethod(mContext);
        initWidget();
        setupFirebase();
        init();

    }
    private void init() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if(checkInput(email, username, password)) {

                    firebaseMethod.registerNewEmail(email, password, username);
                }
            }
        });
    }

    private boolean checkInput(String email, String username, String password) {
        if(email.equals("") || (password.equals("")) || username.equals("")) {
            Toast.makeText(mContext, "All fields must be filled out ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    private void initWidget() {
        mUsername = (EditText) findViewById(R.id.input_username);
        btnRegister = (Button) findViewById(R.id.btn_register);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mContext = RegisterActivity.this;
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void setupFirebase() {

        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();


        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + currentUser.getUid());


                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //check to make sure the username is not already in use
                            if (firebaseMethod.checkIfUsernameExists(username, dataSnapshot)) {
                                append = myRef.push().getKey().substring(3, 10);

                            }
                            username = username + append;
                            //add new user to the database
                            firebaseMethod.addNewUser(email, username, "", "");

                            Toast.makeText(mContext, "Signup Sucessful", Toast.LENGTH_SHORT).show();

                            //add new user_account_setting to the database
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        };




    }
}
