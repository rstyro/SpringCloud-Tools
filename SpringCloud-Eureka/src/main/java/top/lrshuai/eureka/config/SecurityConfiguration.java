package top.lrshuai.eureka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * springboot2.* 之后，如下配置会报错
 * security:
 *   basic:
 *     enabled: true # 启用SpringSecurity的安全配置项
 */
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/eureka/**");
//        http.authorizeRequests()
//                // swagger页面需要添加登录校验
//                .antMatchers("/swagger-ui.html").authenticated()
//                //普通的接口不需要校验
//                .antMatchers("/**").permitAll()
//                .and()
//                .formLogin();
//        http.csrf().disable();
        super.configure(http);
    }
}
