package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;


@Entity
public class Grid extends Model{
    
    @Id
    public Integer id;
    public Date date;
    public String client;
    public Integer amount;
    public Integer tax;
    public Integer total;
    public String notes;
    public String comment;
    
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
    public static Finder<Integer, Grid> find = new Finder<>(Grid.class);

}
