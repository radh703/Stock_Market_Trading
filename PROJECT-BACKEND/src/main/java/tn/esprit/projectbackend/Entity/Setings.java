package tn.esprit.projectbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Setings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idSetings;
}
