package ecx.io.book;

import ecx.io.book.Model.BazaPodataka;
import ecx.io.book.Model.Book;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {

            File fXmlFile = new File("books.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList nList = doc.getElementsByTagName("book");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    String author = eElement.getElementsByTagName("author").item(0).getTextContent();
                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
                    float price = Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent());
                    Date publicDate = new SimpleDateFormat("yyy-MM-dd").parse(eElement.getElementsByTagName("publish_date").item(0).getTextContent());
                    String description = eElement.getElementsByTagName("description").item(0).getTextContent();

                    Book newBook = new Book(id, author, title, genre, price, publicDate, description);
                    BazaPodataka.getInstance().addBook(newBook);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
