package tn.esprit.projectbackend.Service;

import tn.esprit.projectbackend.Entity.BusinessPlan;

import java.util.List;

public interface IBusinessPlanService {
    public List<BusinessPlan> getAllBusinessPlan();
    public BusinessPlan getBusinessPlan(Long businessPlanId);
    public BusinessPlan addBusinessPlan(BusinessPlan b);
    public void removeBusinessPlan(Long businessPlanId);
    public  BusinessPlan modifyBusinessPlan(BusinessPlan businessPlan);
}
