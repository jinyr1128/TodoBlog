package yull.todoblog.common.thread;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ThreadRepositoryQueryImpl implements ThreadRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Thread> search(ThreadSearchCond cond) {
        QThread thread = QThread.thread;
        var query = jpaQueryFactory.selectFrom(thread)
                .leftJoin(thread.comments).fetchJoin()
                .where(
                        thread.message.contains(cond.getKeyword()),
                        thread.channel.id.eq(cond.getChannelId())
                );

        return query.fetch();
    }

    private BooleanExpression messageLike(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return QThread.thread.message.contains(keyword);
    }

    private BooleanExpression channelIdEq(Long channelId) {
        if (channelId == null) {
            return null;
        }
        return QThread.thread.channel.id.eq(channelId);
    }
}
//어 뭐야...왜 이젠 되지????
