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

import com.nabil.models.Traveller;
import com.nabil.services.TravellerService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Component
public class TravellerSeeder implements CommandLineRunner {
    private static String DATA_URL = "https://raw.githubusercontent.com/faiyaz1469/Files/main/travellers6.csv";

    private final Logger logger = LoggerFactory.getLogger(TravellerSeeder.class);
    
    @Autowired
    private TravellerService travellerService;

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
        
        List<Traveller> travellers = travellerService.getAllTravellers();
        
        if(travellers.size() == 0) {

            for (CSVRecord record : records) {
                
                String firstName = record.get("FirstName");
                String email = record.get("Email");
                String name = record.get("Name");
                String subject = record.get("Subject");
                Double price = Double.parseDouble(record.get("Price"));
                String method = record.get("Method");

                Traveller traveller = new Traveller(firstName,email,name,subject,price,method);
                
                travellerService.addTraveller(traveller);
            }
            
            logger.info("Number of travellers : {}", travellerService.getAllTravellers().size());

        }

    }
}
