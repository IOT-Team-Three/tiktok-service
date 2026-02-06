package com.iot.Controller;

import com.iot.VideoResourceService;
import com.iot.dto.Result;
import com.iot.dto.UploadRequest;
import com.iot.entity.Video;
import com.iot.service.VideoSearch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 视频接口
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
@Validated
@Tag(name = "视频管理", description = "视频上传、搜索等接口")
public class VideoController {

    private final VideoResourceService videoResourceService;
    private final VideoSearch videoSearch;

    @Operation(summary = "上传视频")
    @PostMapping("/upload/video")
    public Result<String> uploadVideo(@RequestBody UploadRequest uploadRequest) {
        log.info("收到上传视频请求: title={}", uploadRequest.getTitle());
        return videoResourceService.uploadVideo(uploadRequest);
    }

    /**
     * 搜索视频
     */
    @Operation(summary = "搜索视频")
    @GetMapping("/search")
    public Result<?> searchVideos(
            @Parameter(description = "搜索关键词") @RequestParam(value = "keyword", required = false) String keyword,
            @Parameter(description = "页码，默认1") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @Parameter(description = "每页大小，默认10") @RequestParam(value = "size", defaultValue = "10") Integer size) {

        log.info("搜索视频请求 - 关键词: {}, 页码: {}, 大小: {}", keyword, page, size);

        try {
            // 调用现有的 VideoSearch 服务
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<Video> resultPage =
                    videoSearch.searchVideos(keyword, page, size);

            // 构建返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("list", resultPage.getRecords());
            result.put("total", resultPage.getTotal());
            result.put("page", page);
            result.put("size", size);
            result.put("pages", resultPage.getPages());

            if (keyword != null && !keyword.trim().isEmpty()) {
                result.put("keyword", keyword.trim());
            }

            log.info("搜索成功: 找到 {} 条结果, 总记录数: {}",
                    resultPage.getRecords().size(), resultPage.getTotal());

            return Result.success(result);

        } catch (Exception e) {
            log.error("搜索视频失败, keyword: {}, page: {}, size: {}", keyword, page, size, e);
            return Result.error("搜索失败: " + e.getMessage());
        }
    }

    /**
     * 获取视频列表（全部视频）
     */
    @Operation(summary = "获取视频列表")
    @GetMapping("/list")
    public Result<?> getVideoList(
            @Parameter(description = "页码，默认1") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @Parameter(description = "每页大小，默认10") @RequestParam(value = "size", defaultValue = "10") Integer size) {

        log.info("获取视频列表请求 - 页码: {}, 大小: {}", page, size);

        try {
            // 计算偏移量
            int offset = (page - 1) * size;

            // 直接使用 Mapper 查询所有视频
            var videos = videoSearch.getVideoMapper().findAllVideos(offset, size);
            int total = videoSearch.getVideoMapper().countAllVideos();

            // 计算总页数
            int pages = (int) Math.ceil((double) total / size);

            // 构建返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("list", videos);
            result.put("total", total);
            result.put("page", page);
            result.put("size", size);
            result.put("pages", pages);

            log.info("获取列表成功: 当前页 {} 条, 总记录数: {}", videos.size(), total);
            return Result.success(result);

        } catch (Exception e) {
            log.error("获取视频列表失败, page: {}, size: {}", page, size, e);
            return Result.error("获取列表失败: " + e.getMessage());
        }
    }

    /**
     * 测试接口 - 验证Token是否有效
     */
    @Operation(summary = "测试Token")
    @GetMapping("/test")
    public Result<String> testToken() {
        log.info("测试Token接口被调用");
        return Result.success("Token验证成功！接口可以正常访问");
    }

    /**
     * 健康检查接口
     */
    @Operation(summary = "健康检查")
    @GetMapping("/health")
    public Result<String> health() {
        return Result.success("视频服务正常运行中");
    }
}