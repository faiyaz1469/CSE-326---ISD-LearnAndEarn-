package com.nabil.seeder;

import java.io.StringReader;
import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nabil.models.Blog;
import com.nabil.services.BlogService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Component
public class BlogSeeder implements CommandLineRunner {
    private static String DATA_URL = "https://raw.githubusercontent.com/faiyaz1469/Files/main/blogs3.csv";

    private final Logger logger = LoggerFactory.getLogger(BlogSeeder.class);
    
    @Autowired
    private BlogService blogService;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    // loading seeded data from csv file hosted in github
    public void loadSeedData() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        List<Blog> blogs = blogService.getAllBlogs();
        
        if(blogs.size() == 0) {

            for (CSVRecord record : records) {
                String revname = record.get("Revname");
                String title = record.get("Title");
                String description = record.get("Description");
                String name = record.get("Name");
                String subject = record.get("Subject");

                Blog blog = new Blog(revname,title,description,name,subject);
                
                blogService.addBlog(blog);
            }
            
            logger.info("Number of blogs : {}", blogService.getAllBlogs().size());

        }

    }
}
