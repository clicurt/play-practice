package controllers;

import java.io.File;

import javax.inject.Inject;

import models.Product;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;

public class Products extends Controller {

    @Inject
    static FormFactory formFactory;
//    final static Form<Product> productForm = formFactory.form(Product.class).bindFromRequest();

//     private final static Form<Product> productForm = formFactory.form(Product.class);
    // formFactory.form(Product.class);

    public Result list() {
        return ok(views.html.products.list.render(Product.findAll()));
    }

    public Result newProduct() {
//        Form<Product> productForm = formFactory.form(Product.class);
        return TODO;//ok(views.html.products.show.render(productForm));
    }

    public Result show(Long ean) {
        final Product product = Product.findByEan(ean);
        if (product == null) {
            return notFound(Json.toJson("Product %s does not exist."+ ean));
        }
//        Form<Product> filledForm = productForm.fill(product);
        return TODO; //(views.html.products.show.render(filledForm));
    }

    public Result save() {
        MultipartFormData body = request().body().asMultipartFormData();
//        Form<Product> boundForm = productForm.bindFromRequest();
//        if(boundForm.hasErrors()) {
//          flash("error", "Please correct the form below.");
//          return badRequest(views.html.products.show.render(boundForm));
//        }
//
//        Product product = boundForm.get();

        MultipartFormData.FilePart part = body.getFile("picture");
        if(part != null) {
          File picture = (File) part.getFile();

//          try {
////            product.picture = Files;
//          } catch (IOException e) {
//            return internalServerError("Error reading file upload");
//          }
//        }

//        List<Tag> tags = new ArrayList<Tag>();
//        for (Tag tag : product.tags) {
//          if (tag.id != null) {
//            tags.add(Tag.findById(tag.id));
          }
        return internalServerError("Error reading file upload");
    }
}
