package com.prima.seller;

import java.util.HashMap;
import java.util.Map;

public class BookSales {


    public static void main(String[] args) {

        BookSalesUtility bookSales = new BookSalesUtility();
        Map<String, String> arguments = new HashMap<>();
        if (args.length < 2) {
            System.out.println("At least 2 args are required --books and --sales ");
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                String str = args[i].substring(2);
                String s[] = str.split("=");
                arguments.put(s[0], s[1]);
            }
        }
        bookSales.parseBookDetails(arguments.get(Constants.BOOKS_FILE));
        bookSales.parseSalesDetails(arguments.get(Constants.SALES_FILE));

        if(arguments.containsKey(Constants.TOP_SELLING_BOOKS)){
            bookSales.getTopSellingBooks(Integer.valueOf(arguments.get(Constants.TOP_SELLING_BOOKS)));
        }
        if(arguments.containsKey(Constants.TOP_CUSTOMERS)){
            bookSales.getTopCustomers(Integer.valueOf(arguments.get(Constants.TOP_CUSTOMERS)));
        }
        if(arguments.containsKey(Constants.SALES_ON_DATE)){
            bookSales.getSalesOnDate(arguments.get(Constants.SALES_ON_DATE));
        }
    }
}
