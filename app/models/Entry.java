package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;

import play.data.validation.Constraints;

@Entity
public class Entry extends Model{
    @Id
    public long id;
    
    @Constraints.Required
    public String name;
    
    public static Finder<Long, Entry> find = new Finder<>(Entry.class);

}
