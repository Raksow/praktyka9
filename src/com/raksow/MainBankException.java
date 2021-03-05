package com.raksow;

import java.util.Scanner;

public class MainBankException
{

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("Czy chcesz stworzyc konto ?");
        boolean doCreateAccount = getYesNoAnswer();

        if(!doCreateAccount)
        {
            endProgram();
        }
        Account account = createNewAccount();
        System.out.println("Czy chcesz wyplacic pieniadze?");
        boolean shouldWithdrawMoney = getYesNoAnswer();

        if(!shouldWithdrawMoney)
        {
            endProgram();
        }
        System.out.println("Ile chcesz wyplacic ?");
        int amount = getAmount();

        try{
            InsufficientFundsException(amount, account.getBalance());
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            endProgram();
        }

        account.withdraw(amount);
        System.out.printf("Dziekujemy za skorzystanie z serwisu. Twoj stan konta to: %.2f $", account.getBalance());

    }

    private static Account createNewAccount()
    {
        System.out.println("Jak masz na imie ?");
        String name = scanner.nextLine();

        try{
            nameCheck(name);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            endProgram();
        }

        System.out.println("Ile srodkow chcesz dodac do swojego konta?");
        int initialAmount = getAmount();
        return new Account(name,initialAmount);
    }

    private static void endProgram()
    {
        System.out.println("Dziekujemy za korzystanie z serwisu");
        System.exit(0);
    }

    private static int getAmount()
    {
        return Integer.parseInt(scanner.nextLine());
    }

    private static boolean getYesNoAnswer()
    {
        String userAnswer = scanner.nextLine();

        if(userAnswer.toLowerCase().contains("tak")) {
            return true;
        }else if(userAnswer.toLowerCase().contains("nie")) {
            return false;
        } else {
            System.out.println("Napisz tak lub nie");
            return getYesNoAnswer();
        }
    }

    private static void nameCheck(String name) throws Exception
    {
        if(name == null || name.equals(""))
        {
            throw new Exception("Błędne imię");
        }
    }

    private static void InsufficientFundsException(int amount, double balance) throws Exception {
        if(amount > balance)
        {
            throw new Exception("Chcesz wypłacić więcej niż masz a my na to nie pozwalamy");
        }
        if(amount < 0)
        {
            throw new Exception("Chcesz wypłacić kwote na minusie");
        }
    }
}