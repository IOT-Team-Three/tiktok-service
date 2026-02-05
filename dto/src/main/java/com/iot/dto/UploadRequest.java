package com.iot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequest {
    /**
     * 视频文件 或 文档文件
     */
    private MultipartFile file;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    private String description = null;
}
