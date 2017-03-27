package com.example.felix_pc.librarychain;

import android.annotation.TargetApi;
import android.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by joerimmer on 2017-03-06.
 */


public class Block
{
    public BigInteger PreviousBlockSignature;
    private ArrayList<Pair<Transaction, BigInteger>> Transactions;   // tx-signature pairs

    public Block(BigInteger PreviousBlockSignature)
    {
        this.PreviousBlockSignature = PreviousBlockSignature;
        Transactions = new ArrayList<Pair<Transaction, BigInteger>>();
    }
    @TargetApi(5)
    public void AddTransaction(Transaction tx, RSA rsa)
    {
        BigInteger signature = rsa.sign(tx);
        Transactions.add(new Pair<>(tx, signature));
    }
    public  ArrayList<Pair<Transaction, BigInteger>> getTransactions(){
        return Transactions;
    }
    public int Length()
    {
        return Transactions.size();
    }
}
