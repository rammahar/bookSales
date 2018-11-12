package com.prima.seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookSalesUtility {

    Map<String, Book> books = new HashMap<>();
    List<Sales> sales = new ArrayList<>();

    /**
     * Method for parsing the book details into book object
     *
     * @param filePath
     */
    public void parseBookDetails(String filePath) {
        List<String[]> bookDetails = FileReadUtility.readFile(filePath);

//        123A,A	brief	history	of	time,	Stephen	Hawking,	23.22

        bookDetails.forEach(book -> {
            Book b = new Book(book[0].trim(), book[1].trim(), book[2].trim(), Float.valueOf
                (book[3].trim()));
            books.put(book[0].trim(), b);
        });
    }

    /**
     * Method for parsing sales details into sales object
     *
     * @param filePath
     */
    public void parseSalesDetails(String filePath) {
        List<String[]> salesDetails = new FileReadUtility().readFile(filePath);

//        2018-08-01,foo@bar.com,CASH,2,123A;3,1S45;1
        salesDetails.forEach(sale -> {
            Map<String, Integer> itemCount = new HashMap<>();
            Integer count = Integer.valueOf(sale[3]);
            for (int i = 0; i < count; i++) {
                String[] it = sale[4 + i].split(";");
                itemCount.put(it[0], Integer.parseInt(it[1]));
            }
            Date date = null;
            try {
                date = new SimpleDateFormat(Constants.DATE_FORMAT).parse(sale[0].trim());
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Could not covert to date properly. Please check the date");
            }

            PaymentMethodEnum p = PaymentMethodEnum.valueOf(sale[2].trim());
            Sales s = new Sales(date, sale[1].trim(), p, itemCount);
            sales.add(s);
        });
    }

    /**
     * Method for getting specific count top selling books
     *
     * @param nTopSellingBooks
     */
    public void getTopSellingBooks(int nTopSellingBooks) {

        // top selling books
        Map<String, Integer> countMap = new HashMap<>();
        sales.forEach(s -> {
            Map<String, Integer> m = s.getItemCounts();
            m.forEach((k, v) -> {
                if (countMap.containsKey(k)) {
                    countMap.put(k, countMap.get(k) + v);
                } else {
                    countMap.put(k, v);
                }
            });
        });
        Map<String, Integer> hm = sortByValue(countMap);
        System.out.print("top_selling_books");
        printNItemsOfMap(hm,nTopSellingBooks);
    }

    /**
     * Method for getting n top customers
     *
     * @param nTopCustomers
     */
    public void getTopCustomers(int nTopCustomers) {
        // top customers
        Map<String, Integer> customersMap = new HashMap<>();
        sales.forEach(s -> {
            int countOfBooks = s.getItemCounts().values().stream().mapToInt(Number::intValue).sum();
            if (customersMap.containsKey(s.getEmail())) {
                customersMap.put(s.getEmail(), customersMap.get(s.getEmail()) + countOfBooks);
            } else {
                customersMap.put(s.getEmail(), countOfBooks);
            }
        });
        Map<String, Integer> hm = sortByValue(customersMap);
        System.out.print("top_customers");
        printNItemsOfMap(hm,nTopCustomers);

    }

    /**
     * Method for getting sales amount on given date
     *
     * @param strDate
     */
    public void getSalesOnDate(String strDate) {
        // sales on date
        Date date = null;
        try {
            date = new SimpleDateFormat(Constants.DATE_FORMAT).parse(strDate);
        } catch (ParseException e) {
            System.out.println("Unable to parse the date as given input");
            return;
        }
        Date finalDate = date;
        float amt = 0.0f;
        for (Sales s : sales) {
            if (s.getDate().equals(finalDate)) {
                for (Map.Entry<String, Integer> map : s.getItemCounts().entrySet()) {
                    Book b = books.get(map.getKey());
                    amt += (map.getValue() * b.getPrice());
                }

            }
        }
        System.out.println("sales_on_date\t" + strDate + "\t" + amt);
    }

    /**
     * Method for sorting the map on the basis of value
     *
     * @param hm
     * @return
     */
    private HashMap<String, Integer> sortByValue(Map<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm
            .entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Method for printing map keys for given k items.
     * @param map
     * @param n
     */
    private void printNItemsOfMap(Map<String, Integer> map, int n) {
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            if (n > 0) {
                System.out.print("\t" + m.getKey());
                n--;
            } else {
                break;
            }
        }
        System.out.println();
    }
}
