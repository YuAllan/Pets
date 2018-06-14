package com.example.allan.pets.Util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.allan.pets.Add.AddActivity;
import com.example.allan.pets.Favorites.FavoriteActivity;
import com.example.allan.pets.Home.HomeActivity;
import com.example.allan.pets.Message.MessageActivity;
import com.example.allan.pets.Profile.ProfileActivity;
import com.example.allan.pets.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHelp";

    /**
     *
     * @param bottomNavigationViewEx a third-party library github.com/ittianyu/BottomNavigationViewEx
     */
    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "SetupBottomNavigationView: Initliazing Bottom Navigation View");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        /**
         * change activities instead of fragment
         */
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()) {

                    case R.id.ic_home:
                        Intent intent1 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_favorite:
                        Intent intent2 = new Intent(context, FavoriteActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_add:
                        Intent intent3 = new Intent(context, AddActivity.class);
                        context.startActivity(intent3);
                        break;
                    case R.id.ic_message:
                        Intent intent4 = new Intent(context, MessageActivity.class);
                        context.startActivity(intent4);
                        break;
                    case R.id.ic_profile:
                        Intent intent5 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent5);
                        break;

                }
                return false;
            }
        });
    }
}
