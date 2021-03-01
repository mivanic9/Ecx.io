package ecx.io.book.Model;

import java.util.ArrayList;

/**
 *
 * @author Matija Ivanić
 */
public class BazaPodataka {
    
    private static BazaPodataka instance = new BazaPodataka();
    
    private BazaPodataka(){}
    
    public static BazaPodataka getInstance(){
      return instance;
   }
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    
    public ArrayList<Book> graspBooks(){
        return books;
    }
    public ArrayList<Book> graspBorrowedBooks(){
        return borrowedBooks;
    }
    
    public void addBook(Book b){
        books.add(b);
    }
    public void addBorrowedBook(Book b) {
        borrowedBooks.add(b);
    }

    public Book PostojiLiKnjiga(String id) {
        Book pronadenaKnjiga = books.stream().filter(books -> id.equals(books.getId()))
                .findAny()
                .orElse(null);
        return pronadenaKnjiga;
    }
    
    public void PosudiKnjigu(Book book, String ime, String prezime){
        book.setIme(ime);
        book.setPrezime(prezime);
        addBorrowedBook(book);
        books.remove(book);
    }
    
    public Book PostojiLiPosudenaKnjiga(String id, String ime, String prezime) {
        Book pronadenaKnjiga = null;
        if (!borrowedBooks.isEmpty()) {
            for (Book b : borrowedBooks) {
                if(b.getId().contains(id)){
                    pronadenaKnjiga = b;
                }
            }
        }
        if (pronadenaKnjiga != null) {
            if (pronadenaKnjiga.getIme().contains(ime) && pronadenaKnjiga.getPrezime().contains(prezime)) {
                return pronadenaKnjiga;
            } else {
                return null;
            }
        }
        return null;
    }
    
    public void VratiKnjigu(Book book){
        book.setIme("");
        book.setPrezime("");
        books.add(book);
        borrowedBooks.remove(book);
    }
}
