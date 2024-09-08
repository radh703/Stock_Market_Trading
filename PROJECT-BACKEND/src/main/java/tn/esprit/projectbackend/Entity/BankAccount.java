package tn.esprit.projectbackend.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccount {

      @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

      private Long id;
      private String accountNumber;
      private int balance;
      @JsonFormat(pattern ="yyyy-mm-dd")
      private LocalDate createDate;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private LocalDate modifyDate;

    private String rib ;
    private String currency ;

   @Enumerated(EnumType.STRING)
  private AccountType accountType;



    @ManyToOne
    User usersBank;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="bankaccounts")
    private Set<MicroLoan> microLoans;


}
