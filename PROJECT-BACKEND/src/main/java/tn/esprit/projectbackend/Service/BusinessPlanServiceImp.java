package tn.esprit.projectbackend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.BusinessPlan;
import tn.esprit.projectbackend.Repository.BusinessPlanRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class BusinessPlanServiceImp implements IBusinessPlanService {
    BusinessPlanRepository businessPlanRepository;
    public List<BusinessPlan> getAllBusinessPlan(){
        return businessPlanRepository.findAll();
    }
    public BusinessPlan getBusinessPlan(Long businessPlanId){
        return businessPlanRepository.findById(businessPlanId).get();
    }
    public  BusinessPlan addBusinessPlan(BusinessPlan b){
        return businessPlanRepository.save(b);
    }
    public void removeBusinessPlan(Long businessPlanId){
        businessPlanRepository.deleteById(businessPlanId);
    }
    public BusinessPlan modifyBusinessPlan(BusinessPlan businessPlan){
        return businessPlanRepository.save(businessPlan);
    }
}
