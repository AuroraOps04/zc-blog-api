package club.auroraops04.auroraops04_blog.service;

import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author AuroraOps04
 * @date 2021/9/30 10:32:09
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadServiceTest {
    private File imgFile;
    @Autowired
    private UploadService uploadService;

    @Before
    public void doBefore(){
        imgFile = new File("C:\\Users\\coderplus-tr\\Pictures\\138-140R2095432.jpg");
    }

    @Test
    public void testUploadToCos(){
        ApiResponse<String> res = uploadService.uploadToCos(imgFile, imgFile.getName());
        System.out.println(res);
    }
}
