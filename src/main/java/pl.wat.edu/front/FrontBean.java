package pl.edu.wat.front;

import org.apache.log4j.Logger;
import pl.edu.wat.Notification;
import pl.edu.wat.middle.MiddleBean;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@Asynchronous
public class FrontBean implements FrontBeanRemote {

    @EJB
    private MiddleBean middleBean;

    private static Logger logger = Logger.getLogger(FrontBean.class);

    @Override
    public void notify(Notification notification) {
        logger.info("Sending to MiddleBean: " + notification);
        middleBean.writeToFileSystem(notification);
    }
}
