package tn.esprit.spring.config;

import org.ocpsoft.rewrite.servlet.config.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

@EnableWebMvc()
@Configuration
public class ViewConfiguration extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/static/js/**")

                .addResourceLocations("/resources/static/js/");

        registry.addResourceHandler("/resources/static/css/**")

                .addResourceLocations("/resources/static/css/");

        registry.addResourceHandler("/resources/static/views/**")

                .addResourceLocations("/resources/static/views/");

        registry.addResourceHandler("/resources/static/**")

                .addResourceLocations("/resources/static/");

        registry.addResourceHandler("/webjars/**")

                .addResourceLocations("/webjars/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/views/index.html")
                .setCachePeriod(60).resourceChain(true)
                ;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/resources/static/views/");
        resolver.setSuffix(".html");
        return resolver;
    }
}
