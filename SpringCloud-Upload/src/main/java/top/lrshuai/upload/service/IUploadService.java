package top.lrshuai.upload.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.lrshuai.common.dto.Result;

@Component
public interface IUploadService {
    public Result ossUploadImgByFile(MultipartFile file) throws Exception;
    public Result ossUploadImgByBase64(String base64) throws Exception;
    public Result awsUploadImgS3ByBase64(String base64) throws Exception;
    public Result uploadToLocalhost(MultipartFile file) throws Exception;
    public Result uploadToLocalhostBase64(String base64) throws Exception;
}
