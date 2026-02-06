package com.iot.Impl;

import cn.hutool.http.HttpStatus;
import com.iot.VideoResourceService;
import com.iot.config.ResourceConfig;
import com.iot.dto.Result;
import com.iot.dto.UploadRequest;
import com.iot.entity.Video;
import com.iot.mapper.VideoMapper;
import jakarta.annotation.PostConstruct;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class VideoResourceServiceImpl implements VideoResourceService {

    @Autowired
    private ResourceConfig resourceConfig;

    @Autowired
    private VideoMapper videoMapper;

    private  String videoPath;
    @PostConstruct
    public void init() {
        this.videoPath = resourceConfig.getVIDEO_PATH();
    }

    @Override
    public Result<String> uploadVideo(UploadRequest uploadRequest) {
        return handleFileUpload(uploadRequest);
    }

    private Result<String> handleFileUpload(UploadRequest uploadRequest) {
        // 获取当前登录用户ID
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        MultipartFile file = uploadRequest.getFile();

        // 校验文件是否为空
        if (file.isEmpty()) {
            return Result.error(HttpStatus.HTTP_BAD_REQUEST,"文件不能为空");
        }

        // 校验文件是否为视频文件
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.endsWith(".mp4")) {
            return Result.error(HttpStatus.HTTP_UNSUPPORTED_TYPE,"文件格式必须为mp4",fileName);
        }

        // 检测文件夹是否存在，不存在则创建
        File folder = new File(videoPath + id);
        if (!folder.mkdirs()) {
            String massage = "该用户第一个视频";
        }

        try {
            // 保存文件到服务器
            file.transferTo(new File(videoPath + id + "/" + fileName));
        } catch (IOException e) {
            return Result.error(HttpStatus.HTTP_INTERNAL_ERROR,"上传视频失败",e.getMessage());
        }

        // 获取视频时长
        int duration = 0;
        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath + id + "/" + fileName);
            grabber.start();
            duration = Math.toIntExact(grabber.getLengthInTime() / 1000); // 转换为秒
            grabber.stop();
        } catch (Exception e) {
            return Result.error(HttpStatus.HTTP_INTERNAL_ERROR,"获取视频时长失败",e.getMessage());
        }

        // 数据库
        try{
            Video video = new Video(null, uploadRequest.getTitle(), uploadRequest.getDescription(), Long.parseLong(id), fileName, null, 1, null, null, duration);
            videoMapper.insert(video);
        } catch (Exception e) {
            return Result.error(HttpStatus.HTTP_INTERNAL_ERROR,"保存视频信息失败",e.getMessage());
        }

        // 返回成功
        return Result.success("视频上传成功");
    };
}
