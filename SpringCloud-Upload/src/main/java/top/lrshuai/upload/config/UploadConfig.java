package top.lrshuai.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.lrshuai.common.utils.PathsUtils;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {

	/**
	 * 这个bean是为了自定义上传路径
	 * @return
	 */
	@Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(PathsUtils.getAbsolutePath(""));
        return factory.createMultipartConfig();
    }
}
