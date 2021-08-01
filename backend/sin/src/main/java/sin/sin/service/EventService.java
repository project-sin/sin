package sin.sin.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.event.Event;
import sin.sin.domain.event.EventRepository;
import sin.sin.dto.EventResponse;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public List<EventResponse> findAllEvent() {
        List<Event> events = eventRepository.findAll();
        List<EventResponse> eventResponses = events.stream().
            map((event)-> new EventResponse(
                event.getFileName(),
                getFile(event.getFilePath()),
                event.getClassification()
            )).collect(Collectors.toList());

        return eventResponses;
    }

    private byte[] getFile(String filePath){
        int read;
        byte[] data = new byte[16384];
        try {
            InputStream imageStream = new FileInputStream(filePath);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while ((read = imageStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
