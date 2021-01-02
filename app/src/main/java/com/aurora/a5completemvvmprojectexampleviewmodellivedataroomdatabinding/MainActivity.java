package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.Recycler.BooksAdapter;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.databinding.ActivityMainBinding;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Category;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    MainActivityViewModel main_ActivityViewModel;
    ActivityMainBinding activityMainBinding;
    MainActivityClickHandler handler;
    private Category selectCategory;
    private ArrayList<Category> categoriesList;
    private ArrayList<Book> booksList;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler();
        activityMainBinding.setMainclickHandler(handler);


        main_ActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        main_ActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoriesList=(ArrayList<Category>) categories;
                for (Category c : categories) {
                    Log.i("MyTAG", c.getCategoryName());
                }
                Log.i("MyTAG", "//////////////////////////////////////////////\n");
                showOnSpiner();
            }
        });




    }

    private void initRecycler() {
        booksRecyclerView=activityMainBinding.recycler;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);
        booksAdapter=new BooksAdapter();
        booksRecyclerView.setAdapter(booksAdapter);
        booksAdapter.setBooks(booksList);

    }

    private void showOnSpiner() {
        ArrayAdapter<Category> categoryArrayAdapter=new ArrayAdapter<Category>(this,R.layout.spiner_item,categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
        activityMainBinding.setSpinerAdapter(categoryArrayAdapter);

    }
    private void LoadBookArrayList(int idCategory){
        main_ActivityViewModel.getBooksOfASelectionCategory(idCategory).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
            booksList=(ArrayList<Book>) books;
                initRecycler();
                for (Book b : books) {
                    Log.i("MyTAG", b.getBookName());
                }
            }
        });
    }


    public class MainActivityClickHandler {
        public void onFabClick(View view) {
            Toast.makeText(view.getContext(), "onFabClicked", Toast.LENGTH_SHORT).show();
        }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {

            selectCategory = (Category) parent.getItemAtPosition(pos);

            String message = " id is " + selectCategory.getId() + "\n name is " + selectCategory.getCategoryName() + "\n email is " + selectCategory.getCategoryDescription();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), message, Toast.LENGTH_LONG).show();

            LoadBookArrayList(selectCategory.getId());

        }

    }

}