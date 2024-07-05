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

import com.nabil.models.Lecture;
import com.nabil.services.LectureService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Component
public class LectureSeeder implements CommandLineRunner {
    private static String DATA_URL = "https://raw.githubusercontent.com/faiyaz1469/Files/main/lectures11.csv";

    private final Logger logger = LoggerFactory.getLogger(LectureSeeder.class);
    
    @Autowired
    private LectureService lectureService;

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
        
        List<Lecture> lectures = lectureService.getAllLectures();
        
        if(lectures.size() == 0) {

            for (CSVRecord record : records) {
                
                String title = record.get("Title");
                String description = record.get("Description");
                String subject = record.get("Subject");
                String name = record.get("Name");
                String link = record.get("Link");

                Lecture lecture = new Lecture(title, description, subject, name, link);
                
                lectureService.addLecture(lecture);
            }
            
            logger.info("Number of lectures : {}", lectureService.getAllLectures().size());

        }

    }
}
