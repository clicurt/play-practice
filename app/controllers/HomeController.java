package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import org.jodconverter.office.OfficeException;

import com.fasterxml.jackson.databind.JsonNode;

import models.FileModel;
import play.Logger;
import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a <code>GET</code> request with a
     * path of <code>/</code>.
     */
    public Result index() {
        Connection con = DB.getConnection();
        return ok(views.html.index.render("Welcome"));
    }

    public Result receiveData() {
        JsonNode json = request().body().asJson().get("id");
        return ok(json);
    }

    public Result upload() throws IOException{

        MultipartFormData<File> body = request().body().asMultipartFormData();
        if (body == null) {
            return badRequest("Invalid request");
        }
        FilePart<File> file = body.getFile("file");
        FileModel.processUploadedFile(file, body);
        return ok(Json.toJson("File uploaded"));
    }

    public Result receiveDataDB() {
        Map<String, String[]> params = request().body().asFormUrlEncoded();
        String name = params.get("name")[0].toString();
        String comment = params.get("comment")[0].toString();

        return ok(Json.toJson("success: " + name + " " + comment));
    }
    
    public Result getGridData() {
        String data = "{id:\"1\", invdate:\"2007-10-01\",name:\"test\",note:\"note\",amount:\"200.00\",tax:\"10.00\",total:\"210.00\",rating:\"4\"}";
        Logger.info(data);
        return ok(Json.toJson(data));
    }
    
    public Result editGridData() {
        Map<String, String[]> params = request().body().asFormUrlEncoded();
        String name = params.get("names")[0];
        String comment = params.get("comment")[0];

        return ok(Json.toJson("success: " + name + " " + comment));
    }
    
    public Result convert() throws OfficeException, InterruptedException {
//       if(!PdfConvertor.convert()) {
//           return badRequest("Failed");
//       }
        return ok(Json.toJson("Success"));
    }
    
//    public Result pTable() {
//       String searchTerm = request().getQueryString("element");
//       return ok(Json.toJson(element));
//    }

//    public static Result getImage(Long id) {
//        Entity entity = Entity.find.byId(id);
//        InputStream input = null;
//
//        if (entity.getImage() != null) {
//            input = new ByteArrayInputStream(entity.getImage());
//        } else {
//            try {
//                byte[] byteArray;
//                File file = Play.getFile("/public/images/no_photo.jpg", Play.current());
//                byteArray = IOUtils.toByteArray(new FileInputStream(file));
//                input = new ByteArrayInputStream(byteArray);
//            } catch (Exception e) {
//
//            }
//        }
//
//        return ok(input).as("image/jpeg");
//    }

}
