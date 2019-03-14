package top.lrshuai.upload.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Component
public class AwsClient {

    private AmazonS3 s3;

    @Value("${upload.oss.aws.secret_access_key}")
    private String AWS_SECRET_KEY;

    @Value("${upload.oss.aws.access_key_id}")
    private String AWS_ACCESS_KEY;

    @Value("${upload.oss.aws.bucket}")
    private String bucketName;


    public AmazonS3 getAmazonS3(){
        s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
        // 注意：这个要选择 你这个aws的区域
        Region usWest2 = Region.getRegion(Regions.AP_SOUTHEAST_1);
        s3.setRegion(usWest2);
        return s3;
    }

    /**
     * @param @param  tempFile 目标文件
     * @param @param  remoteFileName 文件名
     * @param @return
     * @param @throws IOException    设定文件
     * @return String    返回类型
     * @throws
     * @Title: uploadToS3
     * @Description: 将文件上传至S3上并且返回url
     */
    public String uploadToS3(File tempFile, String remoteFileName) throws IOException {
        try {
            setS3();
            //上传文件
            s3.putObject(new PutObjectRequest(bucketName, remoteFileName, tempFile).withCannedAcl(CannedAccessControlList.PublicRead));
            //获取一个request
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    bucketName, remoteFileName);
            //生成公用的url
            URL url = s3.generatePresignedUrl(urlRequest);
            return url.toString();
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件到S3
     * @param in
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    public String uploadToS3(InputStream in, String remoteFileName) throws IOException {
        try {
            setS3();
            ObjectMetadata metadata = new ObjectMetadata();
            //上传文件
            s3.putObject(new PutObjectRequest(bucketName, remoteFileName, in,metadata).withCannedAcl(CannedAccessControlList.PublicRead));
            //获取一个request
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    bucketName, remoteFileName);
            //生成公用的url
            URL url = s3.generatePresignedUrl(urlRequest);
            return url.toString();
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
        return null;
    }

    public void setS3(){
        if(s3 == null){
            s3= getAmazonS3();
        }
    }

    /**
     * @param @param  remoteFileName
     * @param @throws IOException    设定文件
     * @return S3ObjectInputStream    返回类型  数据流
     * @throws
     * @Title: getContentFromS3
     * @Description: 获取文件2进制流
     */
    public S3ObjectInputStream getContentFromS3(String remoteFileName) throws IOException {
        try {
            setS3();
            GetObjectRequest request = new GetObjectRequest(bucketName, remoteFileName);
            S3Object object = s3.getObject(request);
            S3ObjectInputStream inputStream = object.getObjectContent();
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param @param  remoteFileName 文件名
     * @param @param  path 下载的路径
     * @param @throws IOException    设定文件
     * @return void    返回类型
     * @throws
     * @Title: downFromS3
     * @Description: 将文件下载到本地路径
     */
    public void downFromS3(String remoteFileName, String path) throws IOException {
        try {
            setS3();
            GetObjectRequest request = new GetObjectRequest(bucketName, remoteFileName);
            s3.getObject(request, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param @param  remoteFileName 文件名
     * @param @return
     * @param @throws IOException    设定文件
     * @return String    返回类型
     * @throws
     * @Title: getUrlFromS3
     * @Description: 获取文件的url
     */
    public String getUrlFromS3(String remoteFileName) throws IOException {
        try {
            setS3();
            GeneratePresignedUrlRequest httpRequest = new GeneratePresignedUrlRequest(bucketName, remoteFileName);
            String url = s3.generatePresignedUrl(httpRequest).toString();//临时链接
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证s3上是否存在名称为bucketName的Bucket
     *
     * @param s3
     * @param bucketName
     * @return
     */
    public boolean checkBucketExists(AmazonS3 s3, String bucketName) {
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket bucket : buckets) {
            if (Objects.equals(bucket.getName(), bucketName)) {
                return true;
            }
        }
        return false;
    }

    public void delFromS3(String remoteFileName) throws IOException {
        try {
            setS3();
            s3.deleteObject(bucketName, remoteFileName);
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
    }

}
