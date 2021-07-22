package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sin.sin.domain.notification.Notification;
import sin.sin.domain.notification.NotificationRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/board")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @GetMapping("/list")
    public Page<Notification> list(){
        Page<Notification> notifications=notificationRepository.findAll(PageRequest.of(0, 20));

        return notifications;
    }
}
