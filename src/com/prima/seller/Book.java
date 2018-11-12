package com.prima.seller;

/**
 * Responsible for storing the info of Book object
 */
public class Book {
    private String id;
    private String title;
    private String author;
    private Float price;

    public Book(String id, String title, String author, Float price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", author='" + author
            + '\'' + ", price=" + price + '}';
    }
}
