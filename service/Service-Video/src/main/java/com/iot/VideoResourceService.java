package com.iot;

import com.iot.dto.Result;
import com.iot.dto.UploadRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public interface VideoResourceService {
    /**
     * 上传视频
     *
     * @param uploadRequest 上传请求
     * @return 视频路径
     */
    @Schema(description = "上传视频")
    Result<String> uploadVideo(UploadRequest uploadRequest);
}
