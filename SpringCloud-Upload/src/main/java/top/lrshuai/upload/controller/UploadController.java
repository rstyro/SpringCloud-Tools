package top.lrshuai.upload.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.lrshuai.common.dto.Result;
import top.lrshuai.upload.service.IUploadService;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    /**
     * aliyun oss图片 file 上传
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/imgFile")
    @ApiOperation("aliyun oss图片 file 上传")
    public Result imgFile(@RequestParam(value = "file") MultipartFile file) throws Exception{
        return uploadService.ossUploadImgByFile(file);
    }

    /**
     * aliyun oss 图片 base64 上传
     * @param base64
     * @return
     * @throws Exception
     */
    @ApiOperation("aaliyun oss 图片 base64 上传")
    @PostMapping("/imgBase64")
    public Result imgBase64(@RequestParam(value = "base64") String base64) throws Exception{
        return uploadService.ossUploadImgByBase64(base64);
    }

    /**
     * aws s3 图片 base64 上传
     * @param base64
     * @return
     * @throws Exception
     */
    @ApiOperation("AWS s3 图片 base64 上传")
    @PostMapping("/imgBase64ToS3")
    public Result s3(@RequestParam(value = "base64") String base64) throws Exception{
        return uploadService.awsUploadImgS3ByBase64(base64);
    }

    /**
     * 上传到本地
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation("file 本地上传图片的")
    @PostMapping("/toLocalhostImage")
    public Result localhostImg(@RequestParam(value = "file") MultipartFile file) throws Exception{
        return uploadService.uploadToLocalhost(file);
    }


    /**
     * base64 上传到本地
     * @return
     * @throws Exception
     */
    @ApiOperation("base64 本地上传图片的")
    @PostMapping("/base64ToLocalhostImage")
    public Result base64ToLocalhostImage(String base64) throws Exception{
        return uploadService.uploadToLocalhostBase64(base64);
    }


}
