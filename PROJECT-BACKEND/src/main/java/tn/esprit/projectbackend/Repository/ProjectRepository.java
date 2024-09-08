package tn.esprit.projectbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projectbackend.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
