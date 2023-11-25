package greengram2.feed;

import greengram2.feed.model.FeedDelDto;
import greengram2.feed.model.FeedFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFavMapper {
    int insFav(FeedFavDto dto);
    int delFav(FeedFavDto dto);
    int delFavByIfeed(FeedDelDto dto);
}
