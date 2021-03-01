package ecx.io.book.Model;

import java.util.Date;

/**
 *
 * @author Matija Ivanić
 */
public class Book {
    private String id;
    private String author;
    private String title;
    private String genre;
    private float price;
    private Date public_date;
    private String description;
    private String ime;
    private String prezime;

    public Book(String id, String author, String title, String genre, float price, Date public_date, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.public_date = public_date;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public float getPrice() {
        return price;
    }

    public Date getPublic_date() {
        return public_date;
    }

    public String getDescription() {
        return description;
    }
        public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }
}
