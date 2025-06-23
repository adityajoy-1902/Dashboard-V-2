package com.example.dashboard.service;

import com.example.dashboard.model.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.LoaderOptions;
import java.io.InputStream;

@Service
public class YamlParserService {
    
    private static final Logger logger = LoggerFactory.getLogger(YamlParserService.class);
    
    public Application parseApplicationYaml(String fileName) {
        logger.info("🔄 Starting to parse YAML file: {}", fileName);
        long startTime = System.currentTimeMillis();
        
        try {
            LoaderOptions options = new LoaderOptions();
            Constructor constructor = new Constructor(Application.class, options);
            TypeDescription applicationType = new TypeDescription(Application.class);
            applicationType.addPropertyParameters("environments", com.example.dashboard.model.Environment.class);
            constructor.addTypeDescription(applicationType);
            
            Yaml yaml = new Yaml(constructor);
            InputStream inputStream = new ClassPathResource("applications/" + fileName).getInputStream();
            Application application = yaml.load(inputStream);
            
            long endTime = System.currentTimeMillis();
            logger.info("✅ Successfully parsed YAML file: {} in {}ms", fileName, (endTime - startTime));
            logger.info("📊 Application '{}' loaded with {} environments", 
                       application.getName(), 
                       application.getEnvironments() != null ? application.getEnvironments().size() : 0);
            
            return application;
        } catch (Exception e) {
            logger.error("❌ Error parsing YAML file: {}", fileName, e);
            throw new RuntimeException("Error parsing application YAML file: " + fileName, e);
        }
    }
} 