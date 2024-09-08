package tn.esprit.projectbackend.Service;

import tn.esprit.projectbackend.Entity.Project;

import java.util.List;

public interface IProjectService {
    public List<Project> getAllProject();
    public Project getProject(Long projectId);
    public Project addProject(Project b);
    public void removeProject(Long projectId);
    public  Project modifyProject(Project project);
}
