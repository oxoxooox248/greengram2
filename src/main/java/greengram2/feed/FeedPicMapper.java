package greengram2.feed;

import greengram2.feed.model.FeedDelDto;
import greengram2.feed.model.FeedInsProcDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicMapper {
    int insFeedPic(FeedInsProcDto dto);
    int delPicByIfeed(FeedDelDto dto);
    List<String> selPicAll(int ifeed);
}
