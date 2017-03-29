package com.example.felix_pc.librarychain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Felix-PC on 2017-03-23.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    int pos = 0;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView ownerTextView;
        public TextView dateTextView;
        public TextView idTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            ownerTextView = (TextView) itemView.findViewById(R.id.book_owner);
            dateTextView = (TextView) itemView.findViewById(R.id.date_added);
            idTextView = (TextView) itemView.findViewById(R.id.book_id);
        }
    }


    ArrayList<Pair<Block, BigInteger>> books;
    private Context mContext;

    public BookAdapter(Context context, ArrayList<Pair<Block, BigInteger>> book) {
        books = book;
        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.booklayout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        //pos = position;
        //update count
        int i = position / 2;
        //Above might be not correct.
        Log.d("Block size in Adapter ", String.valueOf(i));
        Log.d("Position in Adapter ", String.valueOf(position));
        Log.d("getTrans in Adapter ", String.valueOf(books.get(i).first.getTransactions().size()));
        Log.d("i", String.valueOf(i));


        TextView textView = holder.nameTextView;
        TextView datetextView = holder.dateTextView;
        TextView onwertextView = holder.ownerTextView;
        TextView idtextVIew = holder.idTextView;
        int j = position % 2;
        Log.d("j", String.valueOf(j));
        textView.setText("Book Name : " + books.get(i).first.getTransactions().get(j).first.getBookName());
        datetextView.setText("Date Added : " + books.get(i).first.getTransactions().get(j).first.getDate());
        onwertextView.setText("Book Owner : " + books.get(i).first.getTransactions().get(j).first.getOwner());
        idtextVIew.setText("Book id : " + String.valueOf(books.get(i).first.getTransactions().get(j).first.getId()));
    }

    @Override
    public int getItemCount() {
//        int count = 0;
//        //for(int i = 0; i < books.size(); i++)
//        int i = books.size() - 1;
//        count += books.get(i).first.getTransactions().size();
//        Log.d("Count size ", String.valueOf(count));
//        return count;
//
        int count = 0;
        for(int i = 0; i < books.size(); i++)
        {
            count += books.get(i).first.Length();
        }
        return count;
    }

    public void updateData(ArrayList<Pair<Block, BigInteger>> newBook) {
        books = newBook;
        notifyDataSetChanged();
    }
}
