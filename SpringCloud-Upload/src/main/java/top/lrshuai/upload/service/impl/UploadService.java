package top.lrshuai.upload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import top.lrshuai.common.dto.ApiException;
import top.lrshuai.common.dto.ApiResultEnum;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.utils.PathsUtils;
import top.lrshuai.common.utils.Tools;
import top.lrshuai.upload.service.IUploadService;
import top.lrshuai.upload.util.AliyunOSSClient;
import top.lrshuai.upload.util.AwsClient;
import top.lrshuai.upload.util.ImgUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UploadService implements IUploadService {

    //阿里云API的文件夹名称
    @Value("${upload.oss.aliyun.folder}")
    private  String folder;

    @Value("${upload.oss.aliyun.url}")
    public String aliyunUrlPre;

    @Value("${upload.oss.aws.url}")
    public String awsUrlPre;

    @Value("${upload.localhost.folder}")
    public String rootPath;

    @Value("${upload.localhost.url}")
    public String prePath;

    /**
     * 图片后缀
     */
    private static Set<String> picPrefix = new HashSet<String>();

    static {
        picPrefix.add(".jpg");
        picPrefix.add(".png");
    }

    @Autowired
    private AliyunOSSClient aliyunOSSClient;

    @Autowired
    private AwsClient awsClient;

    @Override
    public Result ossUploadImgByFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw  new ApiException(ApiResultEnum.FILE_IS_NULL);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (!picPrefix.contains(suffixName.toLowerCase())) {
            throw new ApiException(ApiResultEnum.FILE_NOT_PIC);
        }
        //生成新的名称，防止名字重复
        String newName = Tools.getUUID()+suffixName;
        String result =aliyunOSSClient.uploadObject2OSS(aliyunOSSClient.getOSSClient(),file.getInputStream(),folder,newName);
        return Result.ok(aliyunUrlPre+"/"+folder+"/"+newName);
    }

    @Override
    public Result ossUploadImgByBase64(String base64) throws Exception {
        String newName = Tools.getUUID()+picPrefix.iterator().next();
        InputStream stream = tranToInputStream(base64);
        String result = aliyunOSSClient.uploadObject2OSS(aliyunOSSClient.getOSSClient(),stream,folder,newName);
        return Result.ok(aliyunUrlPre+"/"+folder+"/"+newName);
    }

    @Override
    public Result awsUploadImgS3ByBase64(String base64) throws Exception {
        String newName = Tools.getUUID() +picPrefix.iterator().next();
        InputStream stream = tranToInputStream(base64);
        awsClient.uploadToS3(stream,newName);
        return Result.ok(awsUrlPre+"/"+newName);
    }

    @Override
    public Result uploadToLocalhost(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new ApiException(ApiResultEnum.FILE_IS_NULL,null);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String folder = "/"+ System.currentTimeMillis()+"/";
        fileName  = System.currentTimeMillis()+suffixName;
        File dest = ImgUtil.createFile(PathsUtils.getAbsolutePath(rootPath+folder+fileName));
        file.transferTo(dest);
        return Result.ok(prePath+folder+fileName);
    }



    @Override
    public Result uploadToLocalhostBase64(String base64) throws Exception {
        InputStream stream = tranToInputStream(base64);
        String folder = "/"+ System.currentTimeMillis()+"/";
        String filePath = folder+ System.currentTimeMillis()+picPrefix.iterator().next();
        ImgUtil.uploadImg(PathsUtils.getAbsolutePath(rootPath),filePath,stream);
        return Result.ok(prePath+filePath);
    }

    /**
     * base64 转inputstram
     * @param base64
     * @return
     * @throws IOException
     */
    public InputStream tranToInputStream(String base64) throws IOException {
        //对字节数组字符串进行Base64解码并生成图片
        BASE64Decoder decoder = new BASE64Decoder();
        base64 = base64.substring(base64.indexOf(",") + 1);
        //Http请求参数中加号会变空格，要替换回来
        base64 = base64.replaceAll(" ","+");
        //Base64解码
        byte[] bytes = decoder.decodeBuffer(base64);
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {//调整异常数据
                bytes[i] += 256;
            }
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        return stream;
    }

}
