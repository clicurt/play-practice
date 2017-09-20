package controllers;

import play.mvc.Controller;

public class GridController extends Controller{
    
//    @Inject
//    FormFactory formFactory;
//    
//    public Result index() {
//        List<Book> books = Book.find.all();
//        return ok(views.html.grid.index.render(books));
//    }
//    
//    public Result create() {
//        Form<Book> bookForm = formFactory.form(Book.class);
//        return ok(views.html.grid.create.render(bookForm));
//    }
//    
//    public Result save() {
//        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
//        Book book = bookForm.get();
//        book.save();
//        return redirect(routes.GridController.index());
//    }
//    
//    public Result edit(Integer id) {
//        Book book = Book.find.byId(id);
//        if(book == null) {
//            return notFound("Book Not Found");
//        }
//        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
//        
//        return ok(views.html.grid.edit.render(bookForm));
//    }
//    
//    public Result update() {
//        Book book = formFactory.form(Book.class).bindFromRequest().get();
//        Book oldBook = Book.find.byId(book.id);
//        if(oldBook == null) {
//            return notFound("Book Not Found");
//        }
//        oldBook.title = book.title;
//        oldBook.author = book.author;
//        oldBook.price = book.price;
//        oldBook.update();
//        
//        return redirect(routes.GridController.index());
//    }
//    
//    public Result show(Integer id) {
//        Book book = Book.find.byId(id);
//        if(book == null) {
//            return notFound("Book Not Found");
//        }
//        return ok(views.html.grid.show.render(book));
//    }
//    
//    public Result destroy(Integer id) {
//        Book book = Book.find.byId(id);
//        if(book == null) {
//            return notFound("Book Not Found");
//        }
//        book.delete();
//        return redirect(routes.GridController.index());
//    }

}
