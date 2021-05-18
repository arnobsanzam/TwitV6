package basepackage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("basepackage")
public class SpringMVC extends WebMvcConfigurerAdapter
{
    @Bean
    public ViewResolver configureViewResolver() {
        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/jsp/");
        viewResolve.setSuffix(".jsp");
        return viewResolve;
    }
}
