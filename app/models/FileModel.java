package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.BOMInputStream;

import play.Configuration;
import play.Logger;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

public class FileModel {

    public static void getAll(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "mkyong");
        map.put("age", 29);
    }
    
    public static Map<Integer, String>  map() {
        Map<Integer, String> productMap = new  HashMap<>();
        productMap.put(1, "Keyboard");
        productMap.put(2, "Mouse");
        productMap.put(3, "Monitor");
        return productMap;
    }
    
    public static void processUploadedFile(FilePart<File> file, MultipartFormData<File> body) {
        file = body.getFile("file");
        String fileName = "";
        if (file == null) {
            Logger.info("error", "Missing file");
        }
        else {
            fileName = file.getFilename();
            final String FILE_NAME = FilenameUtils.getBaseName(fileName);
            final String FILE_EXT = FilenameUtils.getExtension(fileName);
            final char POINT = FilenameUtils.EXTENSION_SEPARATOR;
            
            String[] baseName = fileName.split("\\.(?=[^\\.]+$)");
            String contentType = file.getContentType();
            File inputFile = (File) file.getFile();
            Logger.info("This is inputStream {}:", inputFile.toPath());
            String fullPath = Configuration.root().getString("UPLOAD_PATH");

            if (!contentType.isEmpty()) {
                try {
                    String encodedString = URLEncoder.encode(baseName[0], "UTF-8");
                    String repEncoded = encodedString.replace("+", "_");
                    File modDir = new File(fullPath + repEncoded + "." + baseName[1]);
                    InputStream inputStream;
                    inputStream = new FileInputStream(inputFile.getAbsolutePath());
                    String srcEncode = getBom(inputStream).replace("ByteOrderMark[UTF-8: 0xEF,0xBB,0xBF]", "UTF-8");
                    Logger.info("srcEncode {} ", srcEncode);
                    inputStream.close();
                    if(FILE_EXT.equalsIgnoreCase("csv")) {
                        if (srcEncode.contains("UTF-8")) {
                            File outDir = new File(fullPath +"/tmp/"+ FILE_NAME + POINT + FILE_EXT);
                            Logger.info("outDir {} ", outDir);
                            Paths.get(fullPath, "/tmp/").toFile().mkdirs();
                            String prefix = "temp";
                            String suffix = ".tmp";
                            File tempFile = File.createTempFile(prefix, suffix);
                            tempFile.deleteOnExit();
                            Logger.info("tempFile {}: ", tempFile);
                            transform(inputFile, srcEncode, outDir, "SHIFT-JIS", tempFile);
                            try( BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile), "SHIFT-JIS"));
                                    BufferedWriter bw = new BufferedWriter(
                                            new OutputStreamWriter(new FileOutputStream(outDir), "SHIFT-JIS"));){
                                int read;
                                while ((read = in.read()) != -1) {
                                    char asChar = (char) read;
                                    if (asChar != '?') {
                                      bw.write(asChar);
                                    }
                                  }
                            }
                        }
                    } else {
                        File newdir = new File(fullPath + FILE_NAME + POINT + FILE_EXT);
                        Logger.info("Directory newdir {}: ", newdir.toPath());
                        newdir.mkdirs();
                        Files.copy(inputFile.toPath(), newdir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        PdfConvertor.convert(newdir);
                    }
                    Logger.info("inputStream toPath {}: ", inputFile.toPath());
                    modDir.mkdirs();
                    Files.copy(inputFile.toPath(), modDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    public static void transform(File source, String srcEncoding, File destination, String tgtEncoding, File tempFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), srcEncoding));
                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(destination), tgtEncoding));
                BufferedWriter tempBw = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(tempFile), tgtEncoding));
                BufferedReader brJIS = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile), tgtEncoding));) {
            char[] buffer = new char[16384];
            int read;
            while ((read = br.read(buffer)) != -1) {
                tempBw.write(buffer, 0, read);
            }
        }
    }

    public static String getBom(InputStream inputStream) throws IOException {
        BOMInputStream bis = new BOMInputStream(inputStream);
        String temp = "";
        if (bis.hasBOM()) {
            temp = bis.getBOM().toString();
        }
        
        String encodeString = temp;
        bis.close();
        Logger.info("encodeString {} ", encodeString);
        return encodeString;
    }
}
