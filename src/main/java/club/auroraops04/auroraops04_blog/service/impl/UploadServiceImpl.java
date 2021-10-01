package club.auroraops04.auroraops04_blog.service.impl;

import club.auroraops04.auroraops04_blog.service.UploadService;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponseCode;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;

/**
 * @author AuroraOps04
 * @date 2021/9/30 10:05:42
 * @description
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Value("${cos.accessKey}")
    private String cosAccessKey;
    @Value("${cos.secretKey}")
    private String cosSecretKey;
    @Value("${cos.bucketArea}")
    private String cosBucketArea;
    @Value("${cos.bucketName}")
    private String cosBucketName;
    @Value("${cos.prefix}")
    private String cosPrefix;
    @Value("${cos.path}")
    private String cosPath;

    @Override
    public ApiResponse<String> uploadToCos(File file, String filename) {
        BasicCOSCredentials credentials = new BasicCOSCredentials(cosAccessKey, cosSecretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(cosBucketArea));
        COSClient cosClient = new COSClient(credentials, clientConfig);
        try{
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            String key = String.format("/%s/%d/%d/%s", cosPrefix, year, month, filename);
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosBucketName, key, file);
            cosClient.putObject(putObjectRequest);
           return new ApiResponse<>(ApiResponseCode.SUCCESS, cosPath + key);
        }catch (Exception e){
            return new ApiResponse<>(ApiResponseCode.SUCCESS, false, "");
        }finally {
            cosClient.shutdown();
        }
    }
}
