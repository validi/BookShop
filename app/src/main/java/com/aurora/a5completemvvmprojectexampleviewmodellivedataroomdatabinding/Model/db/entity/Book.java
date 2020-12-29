package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.Model.db.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.BR;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "books_table"
        ,foreignKeys =@ForeignKey
        (entity = Category.class,parentColumns = "id",childColumns = "categoryId",onDelete = CASCADE))

public class Book extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int bookId;
    private String bookName;
    private String unitPrice;
    private int categoryId;

    @Ignore
    public Book() {
    }

    public Book(int bookId, String bookName, String unitPrice, int categoryId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }

    @Bindable
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
        notifyPropertyChanged(BR.bookId);
    }
    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);
    }
    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(BR.unitPrice);
    }
    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }
}
