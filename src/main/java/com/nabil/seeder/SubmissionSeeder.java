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

import com.nabil.models.Submission;
import com.nabil.services.SubmissionService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Component
public class SubmissionSeeder implements CommandLineRunner{
    private static String DATA_URL = "https://raw.githubusercontent.com/faiyaz1469/Files/main/Submission4.csv";

    private final Logger logger = LoggerFactory.getLogger(SubmissionSeeder.class);
    
    @Autowired
    private SubmissionService submissionService;

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

        List<Submission> submissions = submissionService.getAllSubmissions();
        
        if(submissions.size() == 0) {
            
            for (CSVRecord record : records) {
                
                String firstName = record.get("FirstName");
                String title = record.get("Title");
                String description = record.get("Description");
                String subject = record.get("Subject");
                String name = record.get("Name");
                String email = record.get("Email");
                String link = record.get("Link");
                String mark = record.get("Mark");


                Submission submission = new Submission(firstName, title, description, subject, name, email, link, mark);
                
                submissionService.addSubmission(submission);
            }
            
            logger.info("Number of submissions : {}", submissionService.getAllSubmissions().size());

        }
	}

}
