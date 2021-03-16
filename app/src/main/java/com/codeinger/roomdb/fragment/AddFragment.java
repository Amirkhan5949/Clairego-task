package com.codeinger.roomdb.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codeinger.roomdb.R;
import com.codeinger.roomdb.activity.MainActivity;
import com.codeinger.roomdb.adapter.CategorySpnAdapter;
import com.codeinger.roomdb.db.dao.CategoryDao;
import com.codeinger.roomdb.db.dao.ProductDao;
import com.codeinger.roomdb.db.database.MyDatabase;
import com.codeinger.roomdb.db.database.Product;
import com.codeinger.roomdb.db.enitiy.Category;

import java.util.List;


public class AddFragment extends Fragment {

    private View view;
    private TextView tv_Add;
    private EditText et_Add,et_Img_url,et_Product_name;
    private Button btn_Ok,btn_Done;
    private Spinner spinner;
    private LinearLayout product;

    private MyDatabase database;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    private Category.Type type;
    private static String TYPE = "type";

    private Category selectedCategory;

    public  static AddFragment newInstance(Category.Type type){
        Bundle bundle = new Bundle();
        bundle.putSerializable(TYPE,type);
        AddFragment fragment = new AddFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_add, container, false);

        init();
        loadCategory();

        return view;
    }

    private void init() {

        type = (Category.Type) getArguments().getSerializable(TYPE);

        database = MyDatabase.getDatabase(getContext());
        categoryDao = database.getCategoryDao();
        productDao = database.getProductDao();


        product=view.findViewById(R.id.product);
        tv_Add=view.findViewById(R.id.tv_Add);
        et_Add=view.findViewById(R.id.et_Add);
        btn_Ok=view.findViewById(R.id.btn_Ok);
        btn_Done=view.findViewById(R.id.btn_Done);
        et_Img_url=view.findViewById(R.id.et_Img_url);
        et_Product_name=view.findViewById(R.id.et_Product_name);
       spinner=view.findViewById(R.id.spinner);

       btn_Ok.setOnClickListener(view1 -> {
           if (et_Add.getText().toString().length()>0) {
               categoryDao.insetCategory(new Category(et_Add.getText().toString(),type));
               Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
               setEmpty();
               loadCategory();
           }else
               Toast.makeText(getContext(), "Enter Name", Toast.LENGTH_SHORT).show();
       });


       btn_Done.setOnClickListener(view1 -> {
           if (et_Product_name.getText().toString().length()==0) {
               Toast.makeText(getContext(), "Enter Name", Toast.LENGTH_SHORT).show();

           }
           else  if (et_Img_url.getText().toString().length()==0) {
               Toast.makeText(getContext(), "Enter img uRl", Toast.LENGTH_SHORT).show();

           }
           else {
               productDao.insetProduct(new Product(et_Product_name.getText().toString(),et_Img_url.getText().toString(),selectedCategory.getId()));
               startActivity(new Intent(getContext(), MainActivity.class));
               setEmpty();

           }
       });
    }

    void setEmpty(){
        et_Add.setText("");
        et_Img_url.setText("");
        et_Product_name.setText("");
    }

    void loadCategory(){
        List<Category> category = categoryDao.getCAtegoryList(type);

        if(category==null){
            product.setVisibility(View.GONE);
        }
        else {
            if(category.size()>0){
                selectedCategory = category.get(0);
                product.setVisibility(View.VISIBLE);
            }
            else {
                product.setVisibility(View.GONE);
            }

            spinner.setAdapter(new CategorySpnAdapter(getContext(),category));
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedCategory = category.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }
    }
}