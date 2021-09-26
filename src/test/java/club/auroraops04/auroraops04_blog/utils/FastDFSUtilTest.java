package club.auroraops04.auroraops04_blog.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDFSUtilTest {
    @Test
    public void testUpload() throws MyException, IOException {
        ClientGlobal.init("fastdfs_client.cfg");
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageServer server=null;
        StorageClient storageClient=new StorageClient(trackerServer, server);
        String[] strings = storageClient.upload_file("/Users/apple/Pictures/1.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
