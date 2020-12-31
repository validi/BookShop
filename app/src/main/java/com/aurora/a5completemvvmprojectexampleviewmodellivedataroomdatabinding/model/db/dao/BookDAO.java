package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    public long insert(Book book);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);

    @Query("select * from books_table")
    LiveData<List<Book>> getAll();

    @Query("select * from books_table where categoryId==:categoryId")
    LiveData<List<Book>> getByCategoryId(int categoryId);

    @Query("select * from books_table where bookId==:id")
    Book getById(int id);
}
