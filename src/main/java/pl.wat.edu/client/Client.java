package pl.edu.wat.client;

import org.apache.log4j.Logger;
import pl.edu.wat.Configuration;
import pl.edu.wat.Notification;
import pl.edu.wat.front.FrontBeanRemote;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import java.util.Date;
import java.util.UUID;
import java.util.stream.IntStream;

@ManagedBean
public class Client {

    @Resource
    private ManagedExecutorService executorService;

    @EJB
    private FrontBeanRemote frontBean;

    private static Logger logger = Logger.getLogger(Client.class);

    public void makeNotifications() {
        IntStream.range(0, Configuration.AMOUNT_OF_NOTIFICATIONS)
                .forEach(i -> executorService.execute(() -> frontBean.notify(createNotification())));
    }

    private Notification createNotification() {
        Notification notification = new Notification();

        notification.setDate(new Date());
        notification.setThreadName(Thread.currentThread().getName());
        notification.setRandomContent(UUID.randomUUID().toString());

        logger.info("Created: " + notification);
        return notification;
    }
}
