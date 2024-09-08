package tn.esprit.projectbackend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.Project;
import tn.esprit.projectbackend.Repository.ProjectRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class ProjectServiceImp implements IProjectService {
    ProjectRepository projectRepository;
    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }
    public Project getProject(Long projectId){
        return projectRepository.findById(projectId).get();
    }
    public  Project addProject(Project b){
        return projectRepository.save(b);
    }
    public void removeProject(Long projectId){
        projectRepository.deleteById(projectId);
    }
    public Project modifyProject(Project project){
        return projectRepository.save(project);
    }
}
