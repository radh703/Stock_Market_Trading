package tn.esprit.projectbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projectbackend.Entity.BusinessPlan;

public interface BusinessPlanRepository extends JpaRepository<BusinessPlan,Long> {
}
