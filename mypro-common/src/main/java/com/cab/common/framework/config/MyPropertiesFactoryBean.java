package com.cab.common.framework.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by admin on 2018/5/18.
 */
public class MyPropertiesFactoryBean extends PropertiesFactoryBean {

    private String envPrefix;

    public void setEnv(Resource env) {
        super.setLocation(env);
        try {
            Properties properties = super.mergeProperties();
            this.envPrefix = properties.getProperty("env");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLocations(Resource... locations) {
        List<Resource> envResources = new ArrayList<Resource>();
        for (Resource resource : locations) {
            try {
                ClassPathResource pathResource = (ClassPathResource)resource;
                String path = pathResource.getPath().replace("dev", this.envPrefix);
                Resource envResource = new ClassPathResource(path);
                envResources.add(envResource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.setLocations(envResources.toArray(new Resource[]{}));
    }
}
