package com.lector.webtest.server.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/9/2016
 */
@Configuration
@EnableAspectJAutoProxy
@ImportResource("classpath:spring/applicationContext-tx.xml")
public class SpringBootConfiguration extends WebMvcConfigurerAdapter {

    private static final String FTL_SUFFIX = ".ftl";
    private static final String RESOURCE_FOLDER = "src/main/resource";
    private static final String TEMPLATE_FOLDER = RESOURCE_FOLDER + "/templates";
    private static final String UTF_ENCODING = "UTF-8";
    private static final String TEXT_HTML_CHARSET = "text/html; charset=" + UTF_ENCODING;
    private static final String CLASSPATH_TEMPLATES_KEY = "classpath:templates";

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ViewResolver viewResolver() {
        final FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(FTL_SUFFIX);
        resolver.setContentType(TEXT_HTML_CHARSET + UTF_ENCODING);
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
        final FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths(CLASSPATH_TEMPLATES_KEY, TEMPLATE_FOLDER);
        factory.setDefaultEncoding(UTF_ENCODING);
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setConfiguration(factory.createConfiguration());
        return result;
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.mysql")
    public DataSource dataSource() {
        return new MysqlDataSource();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        final DataSource dataSource = applicationContext.getBean(DataSource.class);
        return new DataSourceTransactionManager(dataSource);
    }

}