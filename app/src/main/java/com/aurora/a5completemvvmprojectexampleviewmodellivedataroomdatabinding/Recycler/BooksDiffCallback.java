package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.Recycler;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.db.entity.Book;

import java.util.ArrayList;

public class BooksDiffCallback extends DiffUtil.Callback {

    ArrayList<Book>oldBookList;

    public BooksDiffCallback(ArrayList<Book> oldBookList, ArrayList<Book> newBookList) {
        this.oldBookList = oldBookList;
        this.newBookList = newBookList;
    }

    ArrayList<Book>newBookList;

    @Override
    public int getOldListSize() {
        return oldBookList==null?0:oldBookList.size();
    }

    @Override
    public int getNewListSize() {
        return newBookList==null?0: newBookList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldBookList.get(oldItemPosition).getBookId()==newBookList.get(newItemPosition).getBookId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldBookList.get(oldItemPosition).equals(newBookList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
