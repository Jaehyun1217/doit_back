package com.example.demo.home;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("text", "hello world!");
        return "index";
    }
    @GetMapping(value = "/css/{fileName:.+}", produces = "text/css")
    public ResponseEntity<Resource> getCssFile(@PathVariable String fileName) {
        // Construct the path to the CSS file based on the provided file name
        String cssFilePath = "static/css/" + fileName;

        // Load the CSS file as a resource from the classpath
        Resource resource = new ClassPathResource(cssFilePath);

        // Return a ResponseEntity with the CSS file
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline")
                .body(resource);
    }
    @GetMapping(value = "/js/{fileName:.+}", produces = "text/javascript")
    public ResponseEntity<Resource> getJsFile(@PathVariable String fileName) {
        // Construct the path to the CSS file based on the provided file name
        String cssFilePath = "static/js/" + fileName;

        // Load the CSS file as a resource from the classpath
        Resource resource = new ClassPathResource(cssFilePath);

        // Return a ResponseEntity with the CSS file
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline")
                .body(resource);
    }
    @GetMapping(value = "/images/{fileName:.+}")
    public ResponseEntity<Resource> getImageFile(@PathVariable String fileName) {
        // Construct the path to the CSS file based on the provided file name
        String cssFilePath = "static/images/" + fileName;

        // Load the CSS file as a resource from the classpath
        Resource resource = new ClassPathResource(cssFilePath);

        // Return a ResponseEntity with the CSS file
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline")
                .body(resource);
    }
    @GetMapping(value = "/fonts/{fileName:.+}")
    public ResponseEntity<Resource> getFontFile(@PathVariable String fileName) {
        // Construct the path to the CSS file based on the provided file name
        String cssFilePath = "static/fonts/" + fileName;

        // Load the CSS file as a resource from the classpath
        Resource resource = new ClassPathResource(cssFilePath);

        // Return a ResponseEntity with the CSS file
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline")
                .body(resource);
    }
}
