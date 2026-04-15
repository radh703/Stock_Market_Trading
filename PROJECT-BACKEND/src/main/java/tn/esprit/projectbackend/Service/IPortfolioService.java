package tn.esprit.projectbackend.Service;


import tn.esprit.projectbackend.Entity.Portfolio;

import java.util.List;

public interface IPortfolioService {
    public List<Portfolio> getAllPortfolio();
    public Portfolio getPortfolio(Long portfolioId);
    public Portfolio addPortfolio(Portfolio b);
    public void removePortfolio(Long portfolioId);
    public  Portfolio modifyPortfolio(Portfolio portfolio);
    public List<Portfolio> fetchDataFromApi();
    public List<Portfolio> getPortfolioByCluster();
    public Float predictionForVolume(Long pid);
}
