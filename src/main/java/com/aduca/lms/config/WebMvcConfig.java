package com.aduca.lms.config;

import com.aduca.lms.domain.EmailSetting;
import com.aduca.lms.repository.EmailSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserInterceptor userInterceptor;
    @Autowired
    private EmailSettingRepository emailConfigRepository;

    @Bean
    public JavaMailSender javaMailSender() {
      EmailSetting emailConfig = emailConfigRepository.findTopByOrderByIdAsc();

      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setHost(emailConfig.getHost());
      mailSender.setPort(emailConfig.getPort());
      mailSender.setUsername(emailConfig.getUsername());
      mailSender.setPassword(emailConfig.getPassword());

      Properties props = mailSender.getJavaMailProperties();
      props.put("mail.smtp.auth", emailConfig.isSmtpAuth());
      props.put("mail.smtp.starttls.enable", emailConfig.isStarttlsEnable());
      props.put("mail.smtp.starttls.required", emailConfig.isStarttlsRequired());

      return mailSender;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(userInterceptor).addPathPatterns("/**");
    }
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/**").addResourceLocations("/resources/admin/");
        registry.addResourceHandler("/client/**").addResourceLocations("/resources/client/");
      registry.addResourceHandler("/upload/**").addResourceLocations("/resources/upload/");
    }

}
