package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert
    public long insert(Category category);

    @Delete
    void delete(Category category);

    @Update
    void update(Category category);

    @Query("select * from categories_table")
    LiveData<List<Category>> getAll();

    @Query("select * from categories_table where id==:id")
    Category getById(int id);
}
