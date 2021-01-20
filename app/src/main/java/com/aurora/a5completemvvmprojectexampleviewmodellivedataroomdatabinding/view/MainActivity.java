package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.R;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.Recycler.BooksAdapter;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.databinding.ActivityMainBinding;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Category;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.viewmodel.MainActivityViewModel;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.viewmodel.MainActivityViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

public class MainActivity extends AppCompatActivity {


    MainActivityViewModel main_ActivityViewModel;
    ActivityMainBinding binding;
    MainActivityClickHandler handler;
    private Category selectCategory;
    private ArrayList<Category> categoriesList;
    private ArrayList<Book> booksList;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;

    private static int ADD_BOOK_REQUEST_CODE=1;
    private static int EDIT_BOOK_REQUEST_CODE=2;
    private int selectBookId;

    @Inject
    MainActivityViewModelFactory mainActivityViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler();
        binding.setMainclickHandler(handler);

        binding.toolbar.setTitle("List Book");
        binding.toolbar.setTitleTextColor(0xffffffff);

        //main_ActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        main_ActivityViewModel = ViewModelProviders.of(this,mainActivityViewModelFactory).get(MainActivityViewModel.class);

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
        booksRecyclerView=binding.recycler;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);
        booksAdapter=new BooksAdapter();
        booksRecyclerView.setAdapter(booksAdapter);
        booksAdapter.setBooks(booksList);
        booksAdapter.setOnItemClickListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                selectBookId=book.getBookId();
                Intent intent=new Intent(getApplicationContext(),AddAndEditActivity.class);
                intent.putExtra(AddAndEditActivity.BookID,book.getBookId());
                intent.putExtra(AddAndEditActivity.BookName,book.getBookName());
                intent.putExtra(AddAndEditActivity.UnitPrice,book.getUnitPrice());
                startActivityForResult(intent,EDIT_BOOK_REQUEST_CODE);
            }
        });

        new  ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                  try {
                      Book bookToDelete=booksList.get(viewHolder.getAdapterPosition());
                      main_ActivityViewModel.deleteBook(bookToDelete);
                      booksList.remove(bookToDelete);
                  }catch (Exception e){
                      Log.i("MYTAG",e.getMessage());
                  }

            }
        }).attachToRecyclerView(booksRecyclerView);

    }

    private void showOnSpiner() {
        ArrayAdapter<Category> categoryArrayAdapter=new ArrayAdapter<Category>(this,R.layout.spiner_item,categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
        binding.setSpinerAdapter(categoryArrayAdapter);

    }

    private void LoadBookArrayList(int idCategory){

        main_ActivityViewModel.getBooksOfASelectionCategory(idCategory).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {



                if(idCategory==selectCategory.getId()) {
                    booksList=(ArrayList<Book>) books;
                    initRecycler();
                    for (Book b : booksList) {
                        Log.i("MyTAG", b.getBookName());
                    }

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==ADD_BOOK_REQUEST_CODE){

                Book book=new Book();
                book.setBookName(data.getStringExtra(AddAndEditActivity.BookName));
                book.setUnitPrice("$"+data.getStringExtra(AddAndEditActivity.UnitPrice));
                book.setCategoryId(selectCategory.getId());
                main_ActivityViewModel.addNewBook(book);


            }else if(requestCode==EDIT_BOOK_REQUEST_CODE){
                Book book=new Book();
                book.setBookName(data.getStringExtra(AddAndEditActivity.BookName));
                book.setUnitPrice(data.getStringExtra(AddAndEditActivity.UnitPrice));
                book.setCategoryId(selectCategory.getId());
                book.setBookId(selectBookId);
                main_ActivityViewModel.updateBook(book);
            }

        }
    }

    public class MainActivityClickHandler {
        public void onFabClick(View view) {

            Intent intent=new Intent(getApplicationContext(),AddAndEditActivity.class);
            startActivityForResult(intent,ADD_BOOK_REQUEST_CODE);

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