package com.iot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iot.entity.Video;
import com.iot.mapper.VideoMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter  // 添加这个注解，让 Controller 可以获取 Mapper
public class VideoSearch {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 搜索视频方法
     * @param keyword 搜索关键词
     * @param page 页码（从1开始）
     * @param size 每页大小
     * @return 分页结果
     */
    public Page<Video> searchVideos(String keyword, int page, int size) {
        // 参数校验
        if (page < 1) page = 1;
        if (size < 1) size = 10;
        if (size > 100) size = 100; // 防止过大查询

        int offset = (page - 1) * size;

        // 创建 Page 对象
        Page<Video> resultPage = new Page<>(page, size);

        // 关键词为空：返回空结果
        if (keyword == null || keyword.trim().isEmpty()) {
            resultPage.setRecords(List.of());
            resultPage.setTotal(0);
            return resultPage;
        }

        keyword = keyword.trim();

        try {
            // 查询数据
            List<Video> videos = videoMapper.searchByKeyword(keyword, offset, size);
            int total = videoMapper.countSearchResults(keyword);

            resultPage.setRecords(videos);
            resultPage.setTotal(total);

            return resultPage;

        } catch (Exception e) {
            // 记录错误并返回空结果
            resultPage.setRecords(List.of());
            resultPage.setTotal(0);
            return resultPage;
        }
    }

    /**
     * 获取所有视频（分页）
     */
    public Page<Video> getAllVideos(int page, int size) {
        // 参数校验
        if (page < 1) page = 1;
        if (size < 1) size = 10;
        if (size > 100) size = 100;

        int offset = (page - 1) * size;

        Page<Video> resultPage = new Page<>(page, size);

        try {
            List<Video> videos = videoMapper.findAllVideos(offset, size);
            int total = videoMapper.countAllVideos();

            resultPage.setRecords(videos);
            resultPage.setTotal(total);

            return resultPage;

        } catch (Exception e) {
            resultPage.setRecords(List.of());
            resultPage.setTotal(0);
            return resultPage;
        }
    }
}