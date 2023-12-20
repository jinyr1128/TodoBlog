package yull.todoblog.common.thread;

import java.util.List;

public interface ThreadRepositoryQuery {
    List<Thread> search(ThreadSearchCond cond);
}
