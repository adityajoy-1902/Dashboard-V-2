package com.example.dashboard.service;

import com.example.dashboard.model.ApplicationConfig;
import com.example.dashboard.model.ApplicationMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.LoaderOptions;
import java.io.InputStream;
import java.util.List;

@Service
public class ApplicationConfigService {
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfigService.class);
    
    public List<ApplicationMetadata> getApplicationList() {
        logger.info("🔄 Loading master application configuration from application-list.yaml");
        long startTime = System.currentTimeMillis();
        
        try {
            LoaderOptions options = new LoaderOptions();
            Constructor constructor = new Constructor(ApplicationConfig.class, options);
            TypeDescription applicationConfigType = new TypeDescription(ApplicationConfig.class);
            applicationConfigType.addPropertyParameters("applications", ApplicationMetadata.class);
            constructor.addTypeDescription(applicationConfigType);
            
            Yaml yaml = new Yaml(constructor);
            InputStream inputStream = new ClassPathResource("application-list.yaml").getInputStream();
            ApplicationConfig applicationConfig = yaml.load(inputStream);
            
            long endTime = System.currentTimeMillis();
            logger.info("✅ Successfully loaded master configuration in {}ms", (endTime - startTime));
            logger.info("📋 Found {} applications: {}", 
                       applicationConfig.getApplications().size(),
                       applicationConfig.getApplications().stream()
                           .map(ApplicationMetadata::getName)
                           .reduce((a, b) -> a + ", " + b)
                           .orElse("none"));
            
            return applicationConfig.getApplications();
        } catch (Exception e) {
            logger.error("❌ Error loading master application configuration", e);
            throw new RuntimeException("Error parsing application list", e);
        }
    }
} 