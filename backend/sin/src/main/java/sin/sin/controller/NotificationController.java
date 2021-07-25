package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.domain.notification.Notification;
import sin.sin.dto.NotificationResponse;
import sin.sin.service.NotificationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop/board")
public class NotificationController {

    private final NotificationService notificationService;

    //TODO : PageableDefault sort createdDate순으로 하기
    @GetMapping("/list")
    public ResponseEntity<Page<Notification>> searchList(@RequestParam String id,
                                                         @RequestParam(value="search[subject]", required=false) String subject,
                                                         @RequestParam(value="search[name]", required=false) String name,
                                                         @RequestParam(value="search[contents]", required=false) String contents,
                                                         @RequestParam(value="search[word]", required=false) String word,
                                                         @PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Notification> pagingNotification = notificationService.notificationList(subject, contents, name, word, pageable);

        return ResponseEntity.ok().body(pagingNotification);
    }

    @GetMapping("/view")
    public ResponseEntity<Map<String, Object>> Notice(@RequestParam String id,
                                                      @RequestParam Long no){
        Map<String, Object> map = notificationService.notificationView(no);

        return ResponseEntity.ok().body(map);
    }
}
