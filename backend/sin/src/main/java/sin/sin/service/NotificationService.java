package sin.sin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.member.Member;
import sin.sin.domain.notification.Notification;

@Service
public class NotificationService {

    /**
     * 공지사항 쓰기
     */
    //TODO : 파라미터로 member를 줄 지 member.name을 줄 지 선택
    @Transactional
    public void write(Notification notification, Member member){

    }
}
