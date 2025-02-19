package kr.co.mini.project.web;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
  public void addViewControllers(@NonNull ViewControllerRegistry registry) {
      registry.addRedirectViewController("/", "/todos");
  }

  @Override
  public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/", "classpath:/static/");
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    registry.addResourceHandler("/font/**")
            .addResourceLocations("classpath:/static/font/")
            .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS)); 
  }
}
