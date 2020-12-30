package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.BooksDataBase;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.dao.BookDAO;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.dao.CategoryDAO;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Category;

import java.util.List;

public class EBookShopRepository {

    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> books;

    public EBookShopRepository(Application application) {

        BooksDataBase booksDataBase = BooksDataBase.getInstance(application);
        categoryDAO = booksDataBase.getCategoryDAO();
        bookDAO = booksDataBase.getBookDAO();

    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAll();
    }

    public LiveData<List<Book>> getBooks(int categoryId) {
        return bookDAO.getByCategoryId(categoryId);
    }


    public void insertBook(Book book) {
        new InsertBookAsyncTask(bookDAO).execute(book);
    }
    public void updateBook(Book book) {
        new UpdateBookAsyncTask(bookDAO).execute(book);
    }
    public void deleteBook(Book book) {
        new DeleteBookAsyncTask(bookDAO).execute(book);
    }

    public void deleteCategory(Category category) {
        new DeleteCategoryAsyncTask(categoryDAO).execute(category);
    }
    public void insertCategory(Category category) {
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }
    public void updateCategory(Category category) {
        new UpdateCategoryAsyncTask(categoryDAO).execute(category);
    }



    public static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.insert(categories[0]);
            return null;
        }
    }

    public static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.delete(categories[0]);
            return null;
        }
    }
    public static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        public UpdateCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.update(categories[0]);
            return null;
        }
    }

    public static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        public InsertBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDAO.insert(books[0]);
            return null;
        }
    }

    public static class DeleteBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        public DeleteBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDAO.delete(books[0]);
            return null;
        }
    }
    public static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        public UpdateBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDAO.update(books[0]);
            return null;
        }
    }

}
