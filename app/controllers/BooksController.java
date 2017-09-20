package controllers;

import java.util.List;

import javax.inject.Inject;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class BooksController extends Controller{
    
    @Inject
    FormFactory formFactory;
    
    public Result index() {
        List<Book> books = Book.find.all();
        return ok(views.html.books.index.render(books));
    }
    
    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(views.html.books.create.render(bookForm));
    }
    
    public Result save() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        book.save();
        return redirect(routes.BooksController.index());
    }
    
    public Result edit(Integer id) {
        Book book = Book.find.byId(id);
        if(book == null) {
            return notFound("Book Not Found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        
        return ok(views.html.books.edit.render(bookForm));
    }
    
    public Result update() {
        Book book = formFactory.form(Book.class).bindFromRequest().get();
        Book oldBook = Book.find.byId(book.id);
        if(oldBook == null) {
            return notFound("Book Not Found");
        }
        oldBook.title = book.title;
        oldBook.author = book.author;
        oldBook.price = book.price;
        oldBook.update();
        
        return redirect(routes.BooksController.index());
    }
    
    public Result show(Integer id) {
        Book book = Book.find.byId(id);
        if(book == null) {
            return notFound("Book Not Found");
        }
        return ok(views.html.books.show.render(book));
    }
    
    public Result destroy(Integer id) {
        Book book = Book.find.byId(id);
        if(book == null) {
            return notFound("Book Not Found");
        }
        book.delete();
        return redirect(routes.BooksController.index());
    }

}
