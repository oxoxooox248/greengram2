package greengram2.feed;

import greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insComment(FeedCommentInsProcDto dto);
    int delComment(FeedCommentDelDto dto);
    int delCommentByIfeed(FeedDelDto dto);
    List<FeedCommentSelVo> selComment(FeedCommentSelDto dto);
}
