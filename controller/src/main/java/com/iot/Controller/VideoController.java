package com.iot.Controller;
import com.iot.VideoResourceService;
import com.iot.dto.Result;
import com.iot.dto.UploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 视频接口
 */
@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
@Validated
public class VideoController {

    @Autowired
    private final VideoResourceService videoResourceService;

    @PostMapping("/upload/video")
    public Result<String> uploadVideo(@RequestBody UploadRequest uploadRequest) {
        return videoResourceService.uploadVideo(uploadRequest);
    }
}
