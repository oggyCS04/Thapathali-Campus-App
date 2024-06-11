package com.sagar.thapathaliapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sagar.thapathaliapp2.fragment.ClassroomFragment;
import com.sagar.thapathaliapp2.fragment.FeedFragment;
import com.sagar.thapathaliapp2.fragment.HomeFragment;
import com.sagar.thapathaliapp2.fragment.LibraryFragment;
import com.sagar.thapathaliapp2.fragment.NoticeFragment;
import com.sagar.thapathaliapp2.session.SessionManager;
import com.sagar.thapathaliapp2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        sessionManager = new SessionManager(getApplicationContext());

        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {

            if (menuItem.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (menuItem.getItemId() == R.id.feed) {
                replaceFragment(new FeedFragment());
            } else if (menuItem.getItemId() == R.id.classroom) {
                replaceFragment(new ClassroomFragment());
            } else if (menuItem.getItemId() == R.id.notice) {
                replaceFragment(new NoticeFragment());
            } else if (menuItem.getItemId() == R.id.library) {
                replaceFragment(new LibraryFragment());
            }

            return true;
        });


        binding.settingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutSession();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                Toast.makeText(getApplicationContext(),"You logged out",Toast.LENGTH_LONG).show();
            }
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}