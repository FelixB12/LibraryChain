package com.example.felix_pc.librarychain;


import android.annotation.TargetApi;
import android.util.Log;
import android.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by joerimmer on 2017-03-06.
 */
public class Blockchain
{
    public ArrayList<Pair<Block, Object>> Chain;

    public Blockchain()
    {
        Chain = new ArrayList<>(); // block-signature pairs
    }
    @TargetApi(5)
    public Block GetCurrentBlock()
    {
        return Chain.get(Chain.size()-1).first;
        //TODO (Eduard) check with Joe if that works
    }

    public ArrayList<Pair<Block, BigInteger>> ReadChain()
    {
        return (ArrayList<Pair<Block,BigInteger>>) Chain.clone();
    }
    @TargetApi(5)
    public void AddTransaction(Transaction tx, RSA rsa)
    {
        // if chain is empty, add a fresh block
        if(Chain.isEmpty())
        {
            int i = 0;
            i++;
            Log.d("IsEmpty called ", String.valueOf(i));
            Chain.add(new Pair<>(new Block(null), null));
        }

        Block block = GetCurrentBlock();

        // check if we need a new block
        if(block.Length() >= 2)
        {
            BigInteger signature = rsa.sign(block);
            Pair<Block, Object> signedBlock = new Pair<Block, Object>(block, signature);

            Chain.remove(block);
            Chain.add(signedBlock);
            //If i enable Chain.add(new Pair<>(new Block(signature), null)); it removes the first 2 blocks and shows the third
            //Chain.add(new Pair<>(new Block(signature), null));

            block = GetCurrentBlock();
        }

        block.AddTransaction(tx, rsa);
    }

}
