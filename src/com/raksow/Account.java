package com.raksow;

public class Account
{

    private String accountName;
    private double balance;

    public Account(String accountName, double initialBalance)
    {
        this.accountName = accountName;
        this.balance = initialBalance;
    }

    public int withdraw(int amount)
    {
        balance -= amount;
        return amount;
    }

    public double getBalance()
    {
        return balance;
    }
}