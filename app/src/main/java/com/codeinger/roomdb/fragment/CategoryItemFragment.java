package com.codeinger.roomdb.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeinger.roomdb.R;
import com.codeinger.roomdb.adapter.ProductAdapter;
import com.codeinger.roomdb.db.dao.CategoryDao;
import com.codeinger.roomdb.db.dao.ProductDao;
import com.codeinger.roomdb.db.database.MyDatabase;
import com.codeinger.roomdb.db.enitiy.Category;


public class CategoryItemFragment extends Fragment {

    private RecyclerView recycler;

    private MyDatabase database;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    private long id;
    private static String ID = "id";

    public static CategoryItemFragment newInstance(long id){
        Bundle bundle = new Bundle();
        bundle.putLong(ID,id);
        CategoryItemFragment fragment = new CategoryItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_item, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        id = getArguments().getLong(ID);

        database = MyDatabase.getDatabase(getContext());
        categoryDao = database.getCategoryDao();
        productDao = database.getProductDao();

        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new ProductAdapter(productDao.getProductList(id)));
    }
}