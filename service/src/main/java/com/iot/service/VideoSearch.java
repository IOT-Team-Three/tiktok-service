package com.iot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iot.entity.Video;
import com.iot.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VideoSearch {
    @Autowired
    private VideoMapper videoMapper;

    //搜索视频方法 - 和Controller调用匹配
    public Page<Video> searchVideos(String keyword, int page, int size) {
        // 参数校验
        if (page < 1) page = 1;
        if (size < 1) size = 10;
        int offset = (page - 1) * size;

        // 创建 Page 对象
        Page<Video> resultPage = new Page<>(page, size);

        // 关键词为空：返回空结果
        if (keyword == null || keyword.trim().isEmpty()) {
            resultPage.setRecords(List.of());
            resultPage.setTotal(0);
        } else {
            keyword = keyword.trim();

            // 查询数据
            List<Video> videos = videoMapper.searchByKeyword(keyword, offset, size);
            int total = videoMapper.countSearchResults(keyword);

            resultPage.setRecords(videos);
            resultPage.setTotal(total);
        }

        return resultPage;
    }




        }
