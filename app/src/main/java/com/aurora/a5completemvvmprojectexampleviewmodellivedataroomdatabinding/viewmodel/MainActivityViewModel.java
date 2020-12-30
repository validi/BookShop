package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.EBookShopRepository;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Category;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private EBookShopRepository eBookShopRepository;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Book>> booksOfASelectionCategory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        eBookShopRepository = new EBookShopRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        allCategories = eBookShopRepository.getCategories();
        return allCategories;
    }

    public LiveData<List<Book>> getBooksOfASelectionCategory(int idCategory) {
        booksOfASelectionCategory = eBookShopRepository.getBooks(idCategory);
        return booksOfASelectionCategory;
    }

    public void addNewCategory(Category category) {
        eBookShopRepository.insertCategory(category);
    }

    public void deleteCategory(Category category) {
        eBookShopRepository.deleteCategory(category);
    }

    public void UpdateCategory(Category category) {
        eBookShopRepository.updateCategory(category);
    }

    public void addNewBook(Book book) {
        eBookShopRepository.insertBook(book);
    }

    public void deleteBook(Book book) {
        eBookShopRepository.deleteBook(book);
    }

    public void UpdateBook(Book book) {
        eBookShopRepository.updateBook(book);
    }


}
