package tn.esprit.projectbackend.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import tn.esprit.projectbackend.Entity.Portfolio;
import tn.esprit.projectbackend.Repository.PortfolioRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PortfolioServiceImp implements IPortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Value("${api.url}")
    private String clusteringApiUrl;

    @Value("${prediction.api.url:http://127.0.0.1:8000/Prediction}")
    private String predictionApiUrl;

    private static final Logger logger = LoggerFactory.getLogger(PortfolioServiceImp.class);
    public List<Portfolio> getAllPortfolio(){
        return portfolioRepository.findAll();
    }

    public Portfolio getPortfolio(Long portfolioId){
        return portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found with id " + portfolioId));
    }
    public  Portfolio addPortfolio(Portfolio b){
        return portfolioRepository.save(b);
    }
    public void removePortfolio(Long portfolioId){
        portfolioRepository.deleteById(portfolioId);
    }
    public Portfolio modifyPortfolio(Portfolio portfolio){
        return portfolioRepository.save(portfolio);
    }
    // Consumtion of the Clustring API
    public List<Portfolio> fetchDataFromApi() {
        logger.info("Calling clustering API: {}", clusteringApiUrl);
        try {
            ResponseEntity<String> rawResponseEntity = restTemplate.getForEntity(clusteringApiUrl, String.class);
            String rawResponse = rawResponseEntity.getBody();
            logger.info("Raw API response: {}", rawResponse);
            List<Portfolio> portfolioList = mapper.readValue(rawResponse, new TypeReference<List<Portfolio>>() {});
            logger.info("The list of portfolio: {}", portfolioList);
            return portfolioList;
        } catch (IOException e) {
            logger.error("Error fetching data from API: {}", e.getMessage(), e);
            // Handle the error as needed
            return Collections.emptyList();
        }
    }
    public List<Portfolio>  getPortfolioByCluster(){
        return portfolioRepository.findPortfoliosGroupedByClusterLabel();
    }


    public Float predictionForVolume(Long pid) {
        try {
            Portfolio p = portfolioRepository.findById(pid)
                    .orElseThrow(() -> new IllegalArgumentException("Portfolio not found with id " + pid));
            // Convert Pridect object to JSON string with desired format
            String requestBodyJson = String.format("{\"Open\": %s, \"High\": %s, \"Low\": %s, \"Close\": %s, \"Adj_close\": %s}",
                    p.getOpen(), p.getHigh(), p.getLow(), p.getClose(), p.getAdjClose());
            // Log the JSON request body
            logger.info("Request body: {}", requestBodyJson);
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBodyJson, headers);
            // Make the API call with the JSON request body
            ResponseEntity<String> rawResponseEntity = restTemplate.postForEntity(predictionApiUrl, requestEntity, String.class);
            String rawResponse = rawResponseEntity.getBody();
            // Log the API response
            logger.info("Raw API response: {}", rawResponse);
            return extractPredictionValue(rawResponse);
        } catch (Exception e) {
            logger.error("Error occurred while processing the request: {}", e.getMessage());
            return null;
        }


    }



    private Float extractPredictionValue(String rawResponse) throws IOException {
        if (rawResponse == null || rawResponse.isBlank()) {
            throw new IllegalArgumentException("Prediction API returned empty response");
        }

        JsonNode node = mapper.readTree(rawResponse);
        if (node.isObject() && node.has("prediction")) {
            return (float) node.get("prediction").asDouble();
        }
        if (node.isArray() && !node.isEmpty()) {
            JsonNode first = node.get(0);
            if (first.isNumber()) {
                return (float) first.asDouble();
            }
            String text = first.asText();
            return Float.parseFloat(text);
        }
        if (node.isNumber()) {
            return (float) node.asDouble();
        }
        return Float.parseFloat(node.asText());
    }


}
