package models;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.jodconverter.process.PureJavaProcessManager;

import play.Configuration;
import play.Logger;

public class PdfConvertor {

    // public static final String OFFICE_HOME = "C:/Program Files/LibreOffice
    // 5";
    // String location =
    // Play.application().configuration().getString("location");
    @Inject
//    public static OfficeManager officeManager = null;

    public synchronized static boolean convert(File uploadedFile) throws OfficeException, InterruptedException {
        Runnable conversion = new Runnable() {
            @Override
            public void run() {
                // 1) Start LibreOffice in headless mode.
                DefaultOfficeManagerBuilder configuration = new DefaultOfficeManagerBuilder()
                        .setOfficeHome(new File(Configuration.root().getString("OFFICE_HOME")))
                        .setProcessManager(new PureJavaProcessManager())
                        .setTaskExecutionTimeout(300000L);
                OfficeManager officeManager = configuration.build();
                try {
                    officeManager.start();

                    // 2) Create JODConverter converter
                    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

                    // 3) Create PDF
                    createPDF(converter, uploadedFile);
                }
                catch (OfficeException e) {
                    Logger.info("Office manager cannot start");
                }
                finally {
                    // 4) Stop LibreOffice in headless mode.
                    if (officeManager != null) {
                        try {
                            officeManager.stop();
                        }
                        catch (OfficeException e) {
                            Logger.info("Error! office manager malfunction");
                        }
                    }
                }
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(conversion);
        Logger.info("Thread submitted ..:" + executor);
        executor.shutdown();
        Logger.info("State:  {} ", executor.isShutdown());
        executor.awaitTermination(5L, TimeUnit.NANOSECONDS);
        Logger.info("Running in background...:" + executor);
        return true;
    }

    private static void createPDF(OfficeDocumentConverter converter, File uploadedFile) {
        try {
            long start = System.currentTimeMillis();
            String downloadPath = (Configuration.root().getString("DOWNLOAD_PATH"));
            String downloadedFileName = FilenameUtils.getBaseName(uploadedFile.getName())
                    + FilenameUtils.EXTENSION_SEPARATOR + "pdf";
            Logger.info("Download File {}:" + downloadedFileName);
            if ( !downloadedFileName.isEmpty() ) {
                File downloadedFile = new File(downloadPath + downloadedFileName);
                if( !downloadedFile.exists() ){
                    converter.convert(uploadedFile, downloadedFile);
                }
            }
            Logger.info("Generate " + downloadPath + downloadedFileName + " with "
                    + (System.currentTimeMillis() - start) + "ms");
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
