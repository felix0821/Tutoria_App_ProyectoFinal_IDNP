package com.idnp.tutoria_proyecto_final_idnp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idnp.tutoria_proyecto_final_idnp.R;

public class MainActivity extends AppCompatActivity {

    MainFragmentView mainFragment = new MainFragmentView();
    SessionFragmentView sessionFragment = new SessionFragmentView();
    ForumFragmentView forumFragment = new ForumFragmentView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(mainFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.mainFragment:
                    loadFragment(mainFragment);
                    return true;
                case R.id.mapsFragment:
                    loadFragment(sessionFragment);
                    return true;
                case R.id.forumFragment:
                    loadFragment(forumFragment);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.commit();
    }

}