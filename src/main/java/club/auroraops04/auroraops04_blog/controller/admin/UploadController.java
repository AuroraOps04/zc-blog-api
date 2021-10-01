package club.auroraops04.auroraops04_blog.controller.admin;

import club.auroraops04.auroraops04_blog.core.BaseController;
import club.auroraops04.auroraops04_blog.service.UploadService;
import club.auroraops04.auroraops04_blog.utils.FastDFSUtil;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import cn.hutool.core.io.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@RequestMapping("/admin/upload")
@Api("文件上传相关 api")
public class UploadController extends BaseController {
    @Autowired
    private FastDFSUtil fastDFSUtil;
    @Autowired
    private UploadService uploadService;

    @PostMapping
    @ApiOperation("上传文件")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        String path = fastDFSUtil.upload_file(file.getBytes(), FileUtil.extName(file.getOriginalFilename()), null);
        return success(path);
    }
    @PostMapping("/cos")
    @ApiOperation("上传文件到腾讯云")
    public ApiResponse<String> uploadToCos(@RequestParam("file") MultipartFile file) throws Exception {
        File temp = File.createTempFile("temp", null);
        file.transferTo(temp);
        return uploadService.uploadToCos(temp, file.getOriginalFilename());
    }
}
