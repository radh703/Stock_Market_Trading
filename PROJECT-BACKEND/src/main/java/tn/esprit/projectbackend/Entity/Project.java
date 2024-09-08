package tn.esprit.projectbackend.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProject;
    String projectName;
    String descriptin;
    String startDate;
    String endDate;
    String budget;
    String projectManger;
    String projectMembers;


     @OneToOne
     BusinessPlan businessPlan;
//     @ManyToOne
//     User users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projects")
    private Set<ProjectInvestment> projectInvestments;


}
