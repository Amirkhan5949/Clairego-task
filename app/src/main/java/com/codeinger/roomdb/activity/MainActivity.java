package com.codeinger.roomdb.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.codeinger.roomdb.R;
import com.codeinger.roomdb.db.enitiy.Category;
import com.codeinger.roomdb.fragment.AddFragment;
import com.codeinger.roomdb.fragment.CategoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ImageView iv_Back;


    private BottomNavigationView bottomNavigation;
    private NavigationView navigation;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        navigation.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.games:
                    replace(AddFragment.newInstance(Category.Type.GAMES));
                    break;

                case R.id.apps:
                    replace(AddFragment.newInstance(Category.Type.APPS));
                    break;

                case R.id.movies:
                    replace(AddFragment.newInstance(Category.Type.MOVIES));
                    break;

                case R.id.book:
                    replace(AddFragment.newInstance(Category.Type.BOOKS));
                    break;

            }
            drawer.closeDrawer(GravityCompat.START,true);
            return true;
        });

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.games:
                    replace(CategoryFragment.newInstance(Category.Type.GAMES));
                    break;

                case R.id.apps:
                    replace(CategoryFragment.newInstance(Category.Type.APPS));
                    break;

                case R.id.movies:
                    replace(CategoryFragment.newInstance(Category.Type.MOVIES));
                    break;

                case R.id.book:
                    replace(CategoryFragment.newInstance(Category.Type.BOOKS));
                    break;

            }
            return true;
        });



    }

    private void init() {


        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    void replace(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }


}