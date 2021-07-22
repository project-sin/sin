package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.notification.Notification;
import sin.sin.domain.notification.NotificationRepository;
import sin.sin.dto.NotificationReqDTO;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * 공지사항 쓰기
     */
    //TODO : 파라미터로 member를 줄 지 member.name을 줄 지 선택
    @Transactional
    public Notification write(Notification notification, Member member){
        Notification notificationEntity = NotificationReqDTO.builder()
                .writer(member.getName())
                .content(notification.getContent())
                .title(notification.getTitle())
                .build()
                .toEntity();

        notificationRepository.save(notificationEntity);

        return notificationEntity;
    }

    public Page<Notification> notificationList(Pageable pageable){
        return notificationRepository.findAll(pageable);
    }


}
