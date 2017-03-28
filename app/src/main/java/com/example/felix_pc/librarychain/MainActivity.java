package com.example.felix_pc.librarychain;
import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.math.BigInteger;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    public Button mCancel;
    public Button mConfirm;
    public EditText mBookName;
    public EditText mBookOwner;
    int id = 0;
    Blockchain chain = new Blockchain();
    RSA key = new RSA(1024);
    Transaction[] tx = new Transaction[500];
    ArrayList<Pair<Block, BigInteger>> read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBook(view);
            }
        });
    }
        public void AddBook(View view){
            final View mView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_book, null);
            mBookName = (EditText)mView.findViewById(R.id.BookName);
            mBookOwner = (EditText)mView.findViewById(R.id.BookOwner);//Add the owner of the book
            mCancel = (Button)mView.findViewById(R.id.cancelButton);
            mConfirm = (Button)mView.findViewById(R.id.confirmButton);
            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            mConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int check = 0;
                    for(int i = 0; i < chain.ReadChain().size() ;i++) {
                        for(int j = 0; j < chain.ReadChain().get(i).first.Length(); j++) {
                            if (String.valueOf(mBookName) == chain.ReadChain().get(i).first.getTransactions().get(j).first.getBookName()){
                                //Dieses if checked ob dort ein gleicher owner ist, wie can man das updates in the list??
                                check = 1;
                            }
                        }
                    }
                    if(check == 0) {
                        tx[id] = new Transaction(String.valueOf(mBookOwner.getText()), String.valueOf(mBookName.getText()), id);
                        chain.AddTransaction(tx[id], key);
                        read = chain.ReadChain();
                        ListView();
                        Log.d("Block size : ", String.valueOf(read.size()));
                        id++;
                    }
                    dialog.dismiss();

                }
            });
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        public void ListView(){//this function calls the adapter to update view every time a book gets added
            BookAdapter adapter = new BookAdapter(this,read);
            adapter.notifyDataSetChanged();
            RecyclerView rBooks = (RecyclerView) findViewById(R.id.rBooks);
            rBooks.setAdapter(adapter);
            rBooks.setLayoutManager(new LinearLayoutManager(this));
        }
}
