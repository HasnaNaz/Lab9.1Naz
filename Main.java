package edu.psu.abington.ist.ist242;
/*
Project: Lab 9
Purpose Details: Pizza ordering application
Course: IST 242
Author: Hasna
Date Developed: 6/14/2020
Last Date Changed: 6/14/2020
Rev: 2
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    int cCount = 1;

    //Add scanner as private static
    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char HELP_CODE = '?';
        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        Order order1 = new Order(1);
        Transaction trans1 = new Transaction(1, order1, PaymentType.cash);

        Menu menu1 = new Menu(1, "Plain", 10.5);
        Menu menu2 = new Menu(2, "Meat", 15.0);
        Menu menu3 = new Menu(3, "Extra", 5.0);
        Menu menu4 = new Menu(4, "Veg", 12.5);

        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);

        oList.add(order1);
        tList.add(trans1);

        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch (userAction) {
                case CUST_CODE:
                    cList.add(main.addCustomer());
                    break;
                case CUST_PRNT:
                    Customer.printCustomer(cList);
                    break;
                case MENU_CODE:
                    Menu.listMenu(mList);
                    break;
                case ORDE_CODE:
                    System.out.print("Enter customer ID : ");
                    int cid = scnr.nextInt();
                    if (cid < cList.size()) {
                        ArrayList<Menu> cMenu = (mList);
                        Order.addOrders(order1, cList.get(cid), cMenu);
                        oList.add(order1);
                        trans1 = makePaymet(order1);
                        tList.add(trans1);}
                    else {
                        System.out.println("No such customer ID !");
                    }
                    break;
                case TRAN_CODE:
                    Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    private static Transaction makePaymet(Order order1) {
        double total = 0;
        double amount;
        System.out.println("Your bill is:");
        for (Menu menu : order1.getMenuItem()) {
            System.out.print(menu.getmenuItem());
            System.out.print(" quantity: ");
            System.out.println(menu.getQuantity());
            System.out.print(" amount: ");
            amount = menu.getQuantity() * menu.getitemPrice();
            total = total + amount;
            System.out.println(amount);
        }
        System.out.print("Total bill amount is : ");
        System.out.println(total);
        int option;
        Transaction t;
        while (true) {
            System.out.print("Select Payment Option: ");
            System.out.println("1. Cash");
            System.out.println("2. Credit");
            option = scnr.nextInt();
            if (option == 1) {
                t = new Transaction(order1.getorderId(), order1, PaymentType.cash);
                return t;
            } else if (option == 2) {
                t = new Transaction(order1.getorderId(), order1, PaymentType.credit);
                return t;
            }
        }
    }

    public static char getAction(String prompt) {
        Scanner scnr = new Scanner(System.in);
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }


    public Customer addCustomer(){
        Customer cust = new Customer(cCount++);
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        return cust;
    }
}
