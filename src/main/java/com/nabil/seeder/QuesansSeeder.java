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

import com.nabil.models.Quesans;
import com.nabil.services.QuesansService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Component
public class QuesansSeeder implements CommandLineRunner {
    private static String DATA_URL = "https://raw.githubusercontent.com/faiyaz1469/Files/main/Quesans.csv";

    private final Logger logger = LoggerFactory.getLogger(QuesansSeeder.class);
    
    @Autowired
    private QuesansService quesansService;

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
        
        List<Quesans> quesans = quesansService.getAllQuesans();
        
        if(quesans.size() == 0) {

            for (CSVRecord record : records) {
                String revname = record.get("Revname");
                String quesdescription = record.get("QuesDescription");
                String name = record.get("Name");
                String subject = record.get("Subject");
                String firstName = record.get("FirstName");
                String email = record.get("Email");
                String ansdescription = record.get("AnsDescription");

                Quesans quesanss = new Quesans(revname,quesdescription,name,subject,firstName,email,ansdescription);
                
                quesansService.addQuesans(quesanss);
            }
            
            logger.info("Number of quesans : {}", quesansService.getAllQuesans().size());

        }

    }
}
