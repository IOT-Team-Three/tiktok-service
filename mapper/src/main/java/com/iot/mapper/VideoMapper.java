package com.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iot.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    //自定义关键词搜索(标题+描述)
    @Select({
            "SELECT * FROM video",
            "WHERE title LIKE CONCAT('%', #{keyword}, '%')",
            "   OR text LIKE CONCAT('%', #{keyword}, '%')",
            "ORDER BY create DESC",
            "LIMIT #{offset}, #{limit}"
    })
    List<Video> searchByKeyword(@Param("keyword") String keyword,
                                @Param("offset") int offset,
                                @Param("limit") int limit);

    //统计搜索结果数
    @Select({
            "SELECT COUNT(*) FROM video",
            "WHERE title LIKE CONCAT('%', #{keyword}, '%')",
            "   OR text LIKE CONCAT('%', #{keyword}, '%')"
    })
    int countSearchResults(@Param("keyword") String keyword);

    //搜索全部视频
    // 搜索全部视频（带分页）- 修改SQL
    @Select("SELECT * FROM video ORDER BY create DESC LIMIT #{offset}, #{size}")
    List<Video> findAllVideos(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM video")
    int countAllVideos();

}
