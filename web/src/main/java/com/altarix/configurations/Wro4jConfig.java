package com.altarix.configurations;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.http.WroFilter;
import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.util.ObjectFactory;

import javax.servlet.Filter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@Configuration
public class Wro4jConfig {
    private static final List<String> OTHER_WRO_PROPS = Collections.unmodifiableList(Arrays.asList(
            ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,
            ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS
            ));

    @Bean
    public FilterRegistrationBean webResourcesManager(@NonNull Environment env) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(newWroFilter(env));
        filterRegistrationBean.addUrlPatterns("/wro/*");
        filterRegistrationBean.setName("WebResourceOptimizer");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    private Filter newWroFilter(Environment env) {
        ConfigurableWroFilter filter = new ConfigurableWroFilter();
        Properties prop = buildWroProperties(env);
        filter.setProperties(prop);
        filter.setWroManagerFactory(new Wro4jCustomXmlModelManagerFactory(prop));
        return filter;
    }

    private Properties buildWroProperties(Environment env)
    {
        Properties props = new Properties();
        Stream.of(ConfigConstants.values())
                .map(item -> item.name())
                .forEach(name -> addProperty(env, props, name));
        OTHER_WRO_PROPS.forEach(name -> addProperty(env, props, name));
        return props;
    }

    private void addProperty(Environment env, Properties props, String name) {
        String value = env.getProperty("wro." + name);
        if (!StringUtils.isBlank(value)) {
            props.put(name, value);
        }
    }

    private static class Wro4jCustomXmlModelManagerFactory extends ConfigurableWroManagerFactory {
        final private Properties props;

        public Wro4jCustomXmlModelManagerFactory(@NonNull Properties props) {
            this.props = props;
        }

        protected Properties newConfigProperties() {
            return props;
        }

        protected WroModelFactory newModelFactory() {
            return new XmlModelFactory() {
                protected InputStream getModelResourceAsStream() throws IOException {
                    String resource = "/wro.xml";
                    InputStream stream = getClass().getResourceAsStream(resource);
                    if (stream == null) {
                        throw new IOException("Invalid resource location: " + resource);
                    }
                    return stream;
                }
            };
        }
    }
}
