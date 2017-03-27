package com.example.felix_pc.librarychain;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by joerimmer on 2017-03-06.
 */
public class RSA {
    BigInteger p, q, n, d, e;

    public RSA(int bits)
    {
        Random rand = new Random();
        p = new BigInteger(bits/2, 100, rand);
        q = new BigInteger(bits/2, 100, rand);
        n = p.multiply(q);

        BigInteger totient = lcm(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
        e = new BigInteger("5");

        while (totient.gcd(e).intValue() > 1)
        {
            e = e.add(new BigInteger("3"));
        }

        d = e.modInverse(totient);
    }

    // we want ANYONE to be able to prove we encrypted it, so encrypt with private key
    // and decrypt with public key
    public BigInteger encrypt(BigInteger message)
    {
        return message.modPow(d, n);
    }

    public BigInteger decrypt(BigInteger message)
    {
        return message.modPow(e, n);
    }

    public static BigInteger lcm(BigInteger a, BigInteger b)
    {
        return a.multiply(b.divide(a.gcd(b)));
    }

    public BigInteger sign(Object obj)
    {
        Random rand = new Random();
        int hash = obj.hashCode();

        BigInteger signedHash = encrypt(new BigInteger(hash, rand));

        return signedHash;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RSA rsa = new RSA(1024);

        System.out.println("(e, d) = \n("+rsa.e.toString()+"\n, \n"+rsa.d.toString()+")");

        while(true) {
            System.out.println("Enter a string to be encrypted: ");
            String str = scanner.nextLine();
            BigInteger eStr = rsa.encrypt(new BigInteger(str.getBytes()));
            String dStr = new String(rsa.decrypt(eStr).toByteArray());

            System.out.println("Encrypted string: " + eStr);
            System.out.println("Decrypted string: " + dStr);
        }
    }
}
