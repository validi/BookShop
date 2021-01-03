package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.R;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.databinding.BookListItemBinding;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    public OnItemClickListener onItemClickListener;
    private ArrayList<Book> books=new ArrayList<>();

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookListItemBinding  bookListItemBinding=
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.book_list_item,parent,false);

        return new BookViewHolder(bookListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book=books.get(position);
        holder.bookListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> newBooks) {
      //  this.books = books;
       // notifyDataSetChanged();

        final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new BooksDiffCallback(books,newBooks),false);
        books=newBooks;
        result.dispatchUpdatesTo(BooksAdapter.this);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        BookListItemBinding bookListItemBinding;

        public BookViewHolder(@NonNull BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            this.bookListItemBinding = bookListItemBinding;
            bookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPosition=getAdapterPosition();
                    if(onItemClickListener!=null && clickPosition!=RecyclerView.NO_POSITION)
                    onItemClickListener.onItemClick(books.get(clickPosition));
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Book book);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
