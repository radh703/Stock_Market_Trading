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
public class LoanTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  idLoanTerm;
    Long loanAmount;
    Date startDate;
    Date endDate;
    String termList;
    @OneToOne(mappedBy ="loanTerms")
    MicroLoan microLoan;
}
