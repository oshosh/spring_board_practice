package kr.co.mini.project.web.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Override
  public void addViewControllers(@NonNull ViewControllerRegistry registry) {
      registry.addRedirectViewController("/", "/boards");
  }

  @Override
  public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
    registry.addResourceHandler("/font/**")
            .addResourceLocations("classpath:/static/font/")
            .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS)); 
  }
  
  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
      configurer.setUseTrailingSlashMatch(false);
  }
}
