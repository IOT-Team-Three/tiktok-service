package com.iot.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iot.entity.Video;
import com.iot.service.VideoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoSearch videoService;

    @GetMapping("/search")
    public Page<Video> search(@RequestParam(required = false) String keyword,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return videoService.searchVideos(keyword, page, size);
    }

}