package greengram2.feed;

import greengram2.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import greengram2.ResVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicMapper picMapper;
    private final FeedFavMapper favMapper;
    private final FeedCommentMapper commentMapper;
    //피드 등록
    public ResVo postFeed(FeedInsDto dto){
        if(dto.getPics().size()==0) {return new ResVo(2);}
        FeedInsProcDto pDto= FeedInsProcDto.builder().
                iuser(dto.getIuser()).contents(dto.getContents()).
                location(dto.getLocation()).pics(dto.getPics()).build();
        int affectedRows= mapper.insFeed(pDto);
        if(affectedRows==0||pDto.getIfeed()==0){return new ResVo(0);}
        int affectedRows2= picMapper.insFeedPic(pDto);
        if(affectedRows2!=dto.getPics().size()){return new ResVo(3);}
        return new ResVo(pDto.getIfeed());
    }
    //피드 리스트
    public List<FeedSelVo> getFeedAll(FeedSelDto dto){
        List<FeedSelVo> list= mapper.selFeedAll(dto);
        for(FeedSelVo vo: list){
            List<String> pics= picMapper.selPicAll(vo.getIfeed());
            vo.setPics(pics);
            List<FeedCommentSelVo> comments= commentMapper.selComment(FeedCommentSelDto.builder().
                    ifeed(vo.getIfeed()).startIdx(0).rowCount(4).build());
            if(comments.size()==4){
                vo.setIsMoreComment(1);
                comments.remove(comments.size()-1);
            }
            vo.setComments(comments);
        }
        return list;
    }
    //피드 삭제
    public ResVo delFeed(FeedDelDto dto){
        Integer targetIfeed= mapper.selFeed(dto);
        if(targetIfeed==null) {return new ResVo(0);}
        int affectedComments= commentMapper.delCommentByIfeed(dto);
        int affectedFavs= favMapper.delFavByIfeed(dto);
        int affectedPics= picMapper.delPicByIfeed(dto);
        int affectedRow= mapper.delFeed(dto);
        return new ResVo(1);
    }
    //좋아요 처리
    public ResVo toggleFeedFav(FeedFavDto dto){
        int delRows= favMapper.delFav(dto);
        if(delRows==1){return new ResVo(0);}
        int insRows= favMapper.insFav(dto);
        return new ResVo(1);
    }
    //댓글 등록
    public ResVo postComment(FeedCommentInsDto dto){
        FeedCommentInsProcDto pDto= new FeedCommentInsProcDto(dto);
        int affectRows= commentMapper.insComment(pDto);
        if(affectRows==0){return new ResVo(0);}
        return new ResVo(pDto.getIfeedComment());
    }
    //댓글 더보기
    public List<FeedCommentSelVo> getCommentAll(int ifeed){
        return commentMapper.selComment(FeedCommentSelDto.builder().
                ifeed(ifeed).startIdx(4).rowCount(9999).build());
    }
    //댓글 삭제
    public ResVo delComment(FeedCommentDelDto dto){
        int affectedRows= commentMapper.delComment(dto);
        return new ResVo(affectedRows);
    }

}
