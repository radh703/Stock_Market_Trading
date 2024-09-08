package tn.esprit.projectbackend.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Entity.BusinessPlan;
import tn.esprit.projectbackend.Service.IBusinessPlanService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/businessPlan")
public class BusinessResController {
    IBusinessPlanService businessPlanService;
    @GetMapping("/get-all-businessPlans")
    public List<BusinessPlan> getAllBusinessPlan(){
        List<BusinessPlan> listAllBusinessPlans = businessPlanService.getAllBusinessPlan();
        return listAllBusinessPlans;
    }
    @GetMapping("/get-businessPlans/{idBusinessPlan}")
    public BusinessPlan  getBusinessPlan(@PathVariable("idBusinessPlan") Long blId){
        BusinessPlan businessPlan = businessPlanService.getBusinessPlan(blId);
        return businessPlan;
    }
    @PostMapping("/add-businessPlan")
    public  BusinessPlan addBusinessPlan(@RequestBody BusinessPlan b){
        BusinessPlan businessPlan = businessPlanService.addBusinessPlan(b);
        return  businessPlan;
    }
    @DeleteMapping("/remove-businessPlan/{businessPlan-id}")
    public void  removeBusinessPlan(@PathVariable("businessPlan-id") Long blId){
        businessPlanService.removeBusinessPlan(blId);
    }
    @PutMapping("/modify-businessPlan")
    public BusinessPlan modifyBusinessPlan(@RequestBody BusinessPlan b){
        BusinessPlan businessPlan = businessPlanService.modifyBusinessPlan(b);
        return businessPlan ;
    }
}
