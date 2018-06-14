package pl.edu.wat.middle;

import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import pl.edu.wat.Configuration;
import pl.edu.wat.Notification;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Singleton
public class MiddleBean {

    private static final String DATE_FORMAT = "ddMMyyyy";
    private static final String DIRECTORY = Configuration.FILES_DIRECTORY;
    private static final String FILENAME_PATTERN = "ZGL_%s_%s.xml";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
    private static final XStream xstream = new XStream();
    private static Logger logger = Logger.getLogger(MiddleBean.class);

    @PostConstruct
    public void init() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
            logger.info("Created directory: " + directory.getName());
        }
    }

    public void writeToFileSystem(Notification notification) {
        String fileName = String.format(FILENAME_PATTERN,
                SIMPLE_DATE_FORMAT.format(notification.getDate()), notification.hashCode());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORY + "/" + fileName))) {
            writer.write(xstream.toXML(notification));
            logger.info("Created file " + fileName + " in file system");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
