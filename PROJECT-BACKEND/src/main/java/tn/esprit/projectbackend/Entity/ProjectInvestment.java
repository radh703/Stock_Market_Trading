package tn.esprit.projectbackend.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectInvestment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProjectInvestement;
    Long amount;
    Date dateOfInsecription;

    @ManyToOne
    User usersproject;
    @ManyToOne
    Project projects ;
}
