package sin.sin.domain.notification;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SearchNotificationRepository {

    private final JPAQueryFactory queryFactory;
    QNotification notification = QNotification.notification;

    public Optional<Page<Notification>> searchNotification(String title, String content, String writer, String word, Pageable pageable) {

        JPAQuery<Notification> query;

        if (word == null) {
            query = queryFactory.selectFrom(notification);
        } else {
            query = queryFactory.selectFrom(notification)
                    .where(containsTitle(title, word).or(containsContent(content, word)).or(containsWriter(writer, word)));
        }

        QueryResults<Notification> queryResults = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
//                .orderBy(pageable.getSort())
                .fetchResults();

        if (queryResults.getTotal() == 0) {
            return Optional.empty();
        }

        return Optional.of(new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal()));
    }

    private BooleanExpression containsTitle(String title, String word) {
        if ("on".equals(title) || word != null)
            return notification.title.contains(word);
        else
            return null;
    }

    private BooleanExpression containsContent(String content, String word) {
        if ("on".equals(content) || word != null)
            return notification.content.contains(word);
        else
            return null;
    }

    private BooleanExpression containsWriter(String writer, String word) {
        if ("on".equals(writer) || word != null)
            return notification.writer.contains(word);
        else
            return null;
    }

}
