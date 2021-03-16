package com.codeinger.roomdb.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeinger.roomdb.R;
import com.codeinger.roomdb.db.dao.CategoryDao;
import com.codeinger.roomdb.db.dao.ProductDao;
import com.codeinger.roomdb.db.database.MyDatabase;
import com.codeinger.roomdb.db.enitiy.Category;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class CategoryFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;

    private MyDatabase database;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    private Category.Type type;
    private static String TYPE = "type";

    public static CategoryFragment newInstance(Category.Type type){
        Bundle bundle = new Bundle();
        bundle.putSerializable(TYPE,type);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category, container, false);
        init();
        load();
        return view;
    }

    private void load() {
        List<Category> category = categoryDao.getCAtegoryList(type);

        if(category==null){
            tabLayout.setVisibility(View.GONE);
        }
        else {
            if(category.size()>0){
                for (Category item : category) {
                    tabLayout.addTab(tabLayout.newTab().setText(item.getName()));
                }

                replace(CategoryItemFragment.newInstance(category.get(0).getId()));

                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        replace(CategoryItemFragment.newInstance(category.get(tab.getPosition()).getId()));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

            }
            else {
                tabLayout.setVisibility(View.GONE);
            }
        }
    }

    private void init() {
        type = (Category.Type) getArguments().getSerializable(TYPE);

        database = MyDatabase.getDatabase(getContext());
        categoryDao = database.getCategoryDao();
        productDao = database.getProductDao();

        tabLayout = view.findViewById(R.id.tabLayout);

    }

    void replace(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}