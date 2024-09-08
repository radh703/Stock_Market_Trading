package tn.esprit.projectbackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MicroLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  idLoan;
    Long loanAmount;
    Long interestRate;
    Long maxAmount;
    Long minAmount;
    Date creationDate;
    String repaymentPeriod;
    @OneToOne
    LoanTerm loanTerms;

    @ManyToOne
    BankAccount bankaccounts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="microLoans")
    private Set<Insurance> insurances;
}
