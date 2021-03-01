package ecx.io.book.Controller;

import ecx.io.book.Model.BazaPodataka;
import ecx.io.book.Model.Book;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Matija Ivanić
 */
@Controller
@RequestMapping("books")
public class BookController {
    
    @RequestMapping(value = "")
    public String index(Model model){
        ArrayList<Book> books = BazaPodataka.getInstance().graspBooks();
        ArrayList<Book> borrowedBooks = BazaPodataka.getInstance().graspBorrowedBooks();
        model.addAttribute("listOfBooks", books);
        model.addAttribute("listOfBorrowedBooks", borrowedBooks);
        model.addAttribute("podNaslov", "Zadatak iz Ecx.io!");
        model.addAttribute("tocniPodaci", "");
        model.addAttribute("tocniPodaciVracanja", "");
        return "books/booksList";
    }

    @RequestMapping(value = "", method = RequestMethod.POST, params = "action=Borrow")
    public String processBorrowBook(
    @RequestParam(value = "ime", required = false)String ime,
    @RequestParam(value = "prezime", required = false)String prezime,
    @RequestParam(value = "id", required = false)String id, 
    Model model) {
        Book book = BazaPodataka.getInstance().PostojiLiKnjiga(id);
        if(book != null && !ime.isEmpty() && !prezime.isEmpty()){
            BazaPodataka.getInstance().PosudiKnjigu(book, ime, prezime);
            model.addAttribute("tocniPodaci", "");
            model.addAttribute("tocniPodaciVracanja", "");
        }else{
            model.addAttribute("tocniPodaci", "Niste dobro popunili podatke!");
        }
        ArrayList<Book> books = BazaPodataka.getInstance().graspBooks();
        ArrayList<Book> borrowedBooks = BazaPodataka.getInstance().graspBorrowedBooks();
        model.addAttribute("listOfBooks", books);
        model.addAttribute("listOfBorrowedBooks", borrowedBooks);
        model.addAttribute("podNaslov", "Zadatak iz Ecx.io!");
        return "books/booksList";
    }
    
    
    @RequestMapping(value = "", method = RequestMethod.POST, params = "action=Return")
    public String processReturning(
    @RequestParam(value = "imePosudenog", required = false)String ime,
    @RequestParam(value = "prezimePosudenog", required = false)String prezime,
    @RequestParam(value = "idKnjige", required = false)String id, 
    Model model) {
        Book book = BazaPodataka.getInstance().PostojiLiPosudenaKnjiga(id, ime, prezime);
        
        if(book != null){
            BazaPodataka.getInstance().VratiKnjigu(book);
            model.addAttribute("tocniPodaci", "");
            model.addAttribute("tocniPodaciVracanja", "");
        }else{
            model.addAttribute("tocniPodaciVracanja", "Podaci nisu ispravni!");
        }
        ArrayList<Book> books = BazaPodataka.getInstance().graspBooks();
        ArrayList<Book> borrowedBooks = BazaPodataka.getInstance().graspBorrowedBooks();
        model.addAttribute("listOfBooks", books);
        model.addAttribute("listOfBorrowedBooks", borrowedBooks);
        model.addAttribute("podNaslov", "Zadatak iz Ecx.io!");
        return "books/booksList";
    }
}
