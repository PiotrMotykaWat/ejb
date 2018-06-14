package pl.edu.wat.front;

import pl.edu.wat.Notification;

import javax.ejb.Remote;

@Remote
public interface FrontBeanRemote {

    void notify(Notification notification);
}
