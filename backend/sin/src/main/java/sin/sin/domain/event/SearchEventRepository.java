package sin.sin.domain.event;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;
import sin.sin.dto.EventResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class SearchEventRepository {

    private final JPAQueryFactory queryFactory;
    private final AwsS3Config awsS3Config;
    private QEvent event = QEvent.event;

    public List<EventResponse> findTop3ByOrderByCreatedDateDesc() {
        List<Event> events = queryFactory.selectFrom(event)
                .where(event.classification.eq(Classification.NonProducts))
                .orderBy(event.id.desc()).fetch();

        return events.stream().
                map((event)-> new EventResponse(
                        event.getFileCode(),
                        getImageUrl(event.getFileName()),
                        event.getClassification()
                )).collect(Collectors.toList());
    }

    private String getImageUrl(String fileName) {
        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String imageUrl = s3Util.getFileURL(fileName);

        return imageUrl;
    }
}

