package com.example.dashboard.controller;

import com.example.dashboard.model.Application;
import com.example.dashboard.model.ApplicationMetadata;
import com.example.dashboard.service.ApplicationConfigService;
import com.example.dashboard.service.YamlParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private ApplicationConfigService applicationConfigService;

    @Autowired
    private YamlParserService yamlParserService;

    @GetMapping("/")
    public String dashboard(Model model) {
        logger.info("🌐 Dashboard page requested - loading application list");
        List<ApplicationMetadata> applications = applicationConfigService.getApplicationList();
        model.addAttribute("applications", applications);
        logger.info("📄 Dashboard page rendered with {} applications", applications.size());
        return "dashboard";
    }

    @GetMapping("/api/applications/{appName}")
    @ResponseBody
    public Application getApplicationData(@PathVariable String appName) {
        logger.info("🔍 API request received for application: {}", appName);
        long startTime = System.currentTimeMillis();
        
        // Find the application metadata
        List<ApplicationMetadata> applications = applicationConfigService.getApplicationList();
        ApplicationMetadata appMetadata = applications.stream()
                .filter(app -> app.getName().equals(appName))
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("❌ Application not found: {}", appName);
                    return new RuntimeException("Application not found: " + appName);
                });
        
        logger.info("📁 Found application metadata: {} -> {}", appName, appMetadata.getFile());
        
        // Parse the specific application YAML file
        Application application = yamlParserService.parseApplicationYaml(appMetadata.getFile());
        
        long endTime = System.currentTimeMillis();
        logger.info("✅ API response sent for application: {} in {}ms", appName, (endTime - startTime));
        
        return application;
    }
} 