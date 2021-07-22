package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.domain.notification.Notification;
import sin.sin.domain.notification.NotificationRepository;
import sin.sin.service.NotificationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop/board")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/list")
    public ResponseEntity<List<Notification>> list(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Notification> pagingNotification=notificationService.notificationList(pageable);

        List<Notification> notifications = pagingNotification.getContent();
        return ResponseEntity.ok().body(notifications);
    }
}
