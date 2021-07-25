package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.notification.Notification;
import sin.sin.domain.notification.NotificationRepository;
import sin.sin.dto.NotificationRequest;
import sin.sin.dto.NotificationResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * 공지사항 쓰기
     */
    //TODO : 파라미터로 member를 줄 지 member.name을 줄 지 선택
    @Transactional
    public Notification write(Notification notification, Member member) {
        Notification notificationEntity = NotificationRequest.builder()
                .writer(member.getName())
                .content(notification.getContent())
                .title(notification.getTitle())
                .build()
                .toEntity();

        notificationRepository.save(notificationEntity);

        return notificationEntity;
    }

    /**
     * 공지사항 검색
     */
    //TODO : 공지사항 검색 리팩토링 필요
    @Transactional(readOnly = true)
    public Page<Notification> notificationList(String title, String content, String writer, String word, Pageable pageable) {
        int notEqualCnt = 0;

        if ("on".equals(title)) {
            title = word;
        } else {
            title = "null";
            notEqualCnt += 1;
        }
        if ("on".equals(content)) {
            content = word;
        } else {
            content = "null";
            notEqualCnt += 1;
        }

        if ("on".equals(writer)) {
            writer = word;
        } else {
            writer = "null";
            notEqualCnt += 1;
        }

        Optional<Page<Notification>> notifications;
        if (notEqualCnt == 3) {
            notifications = Optional.of(notificationRepository.findAll(pageable));
        } else {
            notifications = notificationRepository.findByTitleOrContentOrWriter(title, content, writer, pageable);
        }

        if (notifications.isEmpty()) {
            return Page.empty();
        }

        return notifications.get();
    }

    @Transactional
    public Map<String, Object> notificationView(Long id) {
        Map<String, Object> map = new HashMap<>();

        //save 연산
        int notificationFlag = notificationRepository.updateById(id);

        if (notificationFlag == 0) {
            throw new IllegalArgumentException("일치하는 id 값이 없습니다");
        }

        List<Notification> notifications = notificationRepository.findByIdNotifications(id);

        for (Notification n : notifications) {
            if (n.getId() == id) {
                map.put("cur", n);
            } else if (n.getId() < id) {
                NotificationResponse prev = NotificationResponse.builder()
                        .id(n.getId())
                        .title(n.getTitle())
                        .build();
                map.put("prev", prev);
            } else if (n.getId() > id) {
                NotificationResponse next = NotificationResponse.builder()
                        .id(n.getId())
                        .title(n.getTitle())
                        .build();
                map.put("next", next);
            }
        }

        map.putIfAbsent("prev", "");
        map.putIfAbsent("next", "");

        return map;

    }
}
