package tn.esprit.projectbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.projectbackend.Entity.Portfolio;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

    @Query(value = "SELECT * FROM portfolio ORDER BY cluster_labels", nativeQuery = true)
    List<Portfolio> findPortfoliosGroupedByClusterLabel();


}
