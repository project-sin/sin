package sin.sin.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.event.Classification;
import sin.sin.domain.event.Event;
import sin.sin.domain.event.EventRepository;
import sin.sin.dto.EventResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void findAllEvent() {
        //given
        Event event1 = Event.builder()
            .fileCode("345")
            .fileName("'GoodsList1.PNG'.PNG")
            .classification(Classification.Products)
            .build();
        eventRepository.save(event1);

        //when&then
        List<EventResponse> events1 = eventService.findAllEvent();
        assertThat(events1.size()).isEqualTo(1);
        assertThat(events1.get(0).getFileCode()).isEqualTo(event1.getFileCode());

        //given
        Event event2 = Event.builder()
            .fileCode("samsung")
            .fileName("'EventList1.PNG'.PNG")
            .classification(Classification.NonProducts)
            .build();
        eventRepository.save(event2);

        //when&then
        List<EventResponse> events2 = eventService.findAllEvent();
        assertThat(events2.size()).isEqualTo(2);
    }
}