package sin.sin.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
                event.getFileCode(),
                event.getFileName(),
                event.getClassification()
            )).collect(Collectors.toList());

        return eventResponses;
    }

    public byte[] getFile(String fileName){
        File file = new File("src/main/resources/eventImg");

        String Root = file.getAbsolutePath();
        String fileDir = Root + File.separator + fileName;

        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            fis = new FileInputStream(fileDir);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }

        return fileArray;
    }
}
