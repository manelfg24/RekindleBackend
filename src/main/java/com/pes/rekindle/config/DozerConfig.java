
package com.pes.rekindle.config;

import java.util.ArrayList;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper getMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        ArrayList<String> config = new ArrayList<>();
        config.add("dozerConfig.xml");
        dozerBeanMapper.setMappingFiles(config);
        return dozerBeanMapper;
    }

}
