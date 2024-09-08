package tn.esprit.projectbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.projectbackend.Entity.Portfolio;

import java.awt.font.NumericShaper;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long UserId;
    String FName;
    String LName;
    Number PhoneNumber;
     String email;
     String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="usersportfolio")
    private Set<PortfolioInvestment> portfolioInvestments;
   @OneToMany(cascade = CascadeType.ALL, mappedBy="usersBank")
   @JsonIgnore
    private Set<BankAccount> bankAccounts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="usersproject")
    @JsonIgnore
    private Set<ProjectInvestment> projectInvestments;

}
