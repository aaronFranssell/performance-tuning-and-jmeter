package tuning.controllers.wrappers;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JsonSerializationErrorFilter> jsonSerializationErrorFilter() {
        FilterRegistrationBean<JsonSerializationErrorFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JsonSerializationErrorFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Integer.MIN_VALUE);
        return registrationBean;
    }
}