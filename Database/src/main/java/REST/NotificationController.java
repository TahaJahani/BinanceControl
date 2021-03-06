package REST;

import HibernateEntities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Notification> getAllNotifications() {
        return repository.findAll();
    }
}
