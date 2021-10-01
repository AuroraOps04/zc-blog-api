package club.auroraops04.auroraops04_blog.service;

import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author AuroraOps04
 * @date 2021/9/30 10:05:26
 * @description
 */
public interface UploadService {
    public ApiResponse<String> uploadToCos(File file, String filename);
}
