package greengram2.feed;

import greengram2.feed.model.FeedDelDto;
import greengram2.feed.model.FeedInsProcDto;
import greengram2.feed.model.FeedSelDto;
import greengram2.feed.model.FeedSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsProcDto dto);
    Integer selFeed(FeedDelDto dto);
    int delFeed(FeedDelDto dto);
    List<FeedSelVo> selFeedAll(FeedSelDto dto);
}
