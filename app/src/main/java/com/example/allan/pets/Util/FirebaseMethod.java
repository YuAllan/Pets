package com.example.allan.pets.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.allan.pets.R;
import com.example.allan.pets.models.User;
import com.example.allan.pets.models.UserAccountSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethod {

    private static final String TAG = "FireBaseMethods";

    private FirebaseAuth mAuth;
    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private Context mContext;

    public FirebaseMethod(Context context) {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mContext = context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        if(currentUser != null) {
            userID = currentUser.getUid();
        }
    }

    public void registerNewEmail(final  String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            userID = mAuth.getCurrentUser().getUid();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot) {

        User user = new User();

        for(DataSnapshot ds: dataSnapshot.child(userID).getChildren()) {

            user.setUsername(ds.getValue(User.class).getUsername());

            if(StringManipulation.expandUsername(user.getUsername()).equals(username)) {

                return true;
            }
        }

        return false;
    }

    public void addNewUser(String email, String username, String description, String profile_photo) {

        User user = new User(userID, 1, email, StringManipulation.condenseUsername(username));

        myRef.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(description, username, profile_photo, username);

        myRef.child(mContext.getString(R.string.dbname_user_account_setting))
                .child(userID)
                .setValue(settings);
    }


}
