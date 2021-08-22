package sin.sin.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.domain.event.Event;
import sin.sin.domain.event.EventRepository;
import sin.sin.dto.EventResponse;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AwsS3Config awsS3Config;

    @Transactional
    public List<EventResponse> findAllEvent() {
        List<Event> events = eventRepository.findAll();
        List<EventResponse> eventResponses = events.stream().
            map((event)-> new EventResponse(
                event.getFileCode(),
                getImageUrl(event.getFileName()),
                event.getClassification()
            )).collect(Collectors.toList());

        return eventResponses;
    }

    private String getImageUrl(String fileName){
        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }
}
