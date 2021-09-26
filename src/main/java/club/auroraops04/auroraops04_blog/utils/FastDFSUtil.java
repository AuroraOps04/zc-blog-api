package club.auroraops04.auroraops04_blog.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FastDFSUtil {
    @Value("${fastdfs.tracker.ip}")
    private String trackerIp;
    @Value("${fastdfs.tracker.httpPort}")
    private Integer trackerPort;
    private final String CONFIG_PATH = "fastdfs_client.cfg";
    private final TrackerClient trackerClient;
    private final TrackerServer trackerServer;
    private final StorageClient1 storageClient;

    public FastDFSUtil() throws MyException, IOException {
        ClientGlobal.init(CONFIG_PATH);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getTrackerServer();
        storageClient = new StorageClient1(trackerServer, null);
    }
    /**
     上传文件,参数是文件的路径,后缀名和元数据
     */
    public String  upload_file(String fileName, String ext_name, NameValuePair[] pairs) throws Exception {
        return storageClient.upload_file1(fileName, ext_name, pairs);
    }

    public String upload_file(String fileName)  throws Exception{
        return upload_file(fileName, null, null);
    }


    public String upload_file(String fileName,String ext_name)  throws Exception{
        return upload_file(fileName, ext_name, null);
    }

    public String upload_file(String fileName,NameValuePair[] pairs)  throws Exception{
        return upload_file(fileName, null, pairs);
    }
    /**
     上传二进制数据,需要将文件先转换为二进制
     */
    public String upload_file(byte[]source,String ext_name,NameValuePair[] pairs) throws Exception{

        String path =  storageClient.upload_file1(source, ext_name, pairs);
        return String.format("http://%s:%d/%s", trackerIp, trackerPort, path);
    }

}
