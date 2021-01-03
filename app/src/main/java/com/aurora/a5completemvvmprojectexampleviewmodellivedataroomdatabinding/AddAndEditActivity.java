package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.databinding.ActivityAddAndEditBinding;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;

public class AddAndEditActivity extends AppCompatActivity {

    ActivityAddAndEditBinding binding;
    Book book;
    public static String BookID="Book_ID";
    public static String BookName="Book_Name";
    public static String UnitPrice="Unit_Price";
    AddAndEditActivityClickHandler  addAndEditActivityClickHandler=new AddAndEditActivityClickHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_and_edit);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_and_edit);
        book=new Book();

        binding.setClickHandler(addAndEditActivityClickHandler);
        binding.setBook(book);
        Intent intent=getIntent();
        binding.toolbar2.setTitleTextColor(0xffffffff);
        if(intent.hasExtra(BookID)){
            binding.toolbar2.setTitle("Edit Book");
            book.setBookName(intent.getStringExtra(BookName));
            book.setUnitPrice(intent.getStringExtra(UnitPrice));
        }
        else {
            binding.toolbar2.setTitle("Add New Book");
        }


    }

    public class AddAndEditActivityClickHandler{

        private Context context;
        public AddAndEditActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClick(View view){

            if(book.getBookName()==null){
                binding.edtName.setError("Name field connect be empty");
            }else if(book.getUnitPrice()==null){
                binding.edtUnitPrice.setError("Unit price field connect be empty");
            }
            else {
                Intent intent=new Intent();
                intent.putExtra(BookName,book.getBookName());
                intent.putExtra(UnitPrice,book.getUnitPrice());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}