package tn.esprit.projectbackend.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
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
public class Transaction {
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private Instant Date;
      private int balance;
    private Long amount ;

       @Enumerated(EnumType.STRING)
  private AccountType accountType;

       /* @OneToMany
       Set<jakarta.transaction.Transaction> transactions;*/



}
