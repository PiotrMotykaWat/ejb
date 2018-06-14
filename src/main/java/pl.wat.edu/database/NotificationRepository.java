package pl.edu.wat.database;

import pl.edu.wat.Notification;

import java.util.List;

public interface NotificationRepository {

    void saveAll(List<Notification> notifications);
}
