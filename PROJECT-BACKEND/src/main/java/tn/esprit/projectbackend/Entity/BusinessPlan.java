package tn.esprit.projectbackend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusinessPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idBussinessPlan;
    String industry;
    String businessObjectives;
    String marktingStrategy;
    String operationPlan;
    String riskAnalysis;

     @OneToOne(mappedBy ="businessPlan")
     Project project;

}
