package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;


@Entity
public class Book extends Model{
    
    @Id
    public Integer id;
    public String title;
    public Integer price;
    public String author;
    
//    public Book() {
//        
//    }

//    public Book(Integer id, String title, Integer price, String author) {
//        super();
//        this.id = id;
//        this.title = title;
//        this.price = price;
//        this.author = author;
//    }
//    
    public static Finder<Integer, Book> find = new Finder<>(Book.class);

}
