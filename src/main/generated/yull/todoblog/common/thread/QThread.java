package yull.todoblog.common.thread;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QThread is a Querydsl query type for Thread
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThread extends EntityPathBase<Thread> {

    private static final long serialVersionUID = 554392639L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QThread thread = new QThread("thread");

    public final yull.todoblog.channel.domain.QChannel channel;

    public final SetPath<yull.todoblog.comment.domain.Comment, yull.todoblog.comment.domain.QComment> comments = this.<yull.todoblog.comment.domain.Comment, yull.todoblog.comment.domain.QComment>createSet("comments", yull.todoblog.comment.domain.Comment.class, yull.todoblog.comment.domain.QComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public QThread(String variable) {
        this(Thread.class, forVariable(variable), INITS);
    }

    public QThread(Path<? extends Thread> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QThread(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QThread(PathMetadata metadata, PathInits inits) {
        this(Thread.class, metadata, inits);
    }

    public QThread(Class<? extends Thread> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.channel = inits.isInitialized("channel") ? new yull.todoblog.channel.domain.QChannel(forProperty("channel")) : null;
    }

}

