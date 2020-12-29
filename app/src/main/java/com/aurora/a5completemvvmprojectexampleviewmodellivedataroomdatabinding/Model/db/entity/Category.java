package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.Model.db.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.BR;


@Entity(tableName = "categories_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String categoryName;
    private String categoryDescription;

    @Ignore
    public Category() {

    }


    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.categoryId);
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    @Bindable
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        notifyPropertyChanged(BR.categoryDescription);
    }

    public Category(int id, String categoryName, String categoryDescription) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
