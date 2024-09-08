package tn.esprit.projectbackend.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Entity.Project;
import tn.esprit.projectbackend.Service.IProjectService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectResController {
    IProjectService projectService;
    @GetMapping("/get-all-projects")
    public List<Project> getAllProject(){
        List<Project> listAllProjects = projectService.getAllProject();
        return listAllProjects;
    }
    @GetMapping("/get-projects/{idProject}")
    public Project  getProject(@PathVariable("idProject") Long blId){
        Project project = projectService.getProject(blId);
        return project;
    }
    @PostMapping("/add-project")
    public  Project addProject(@RequestBody Project b){
        Project project = projectService.addProject(b);
        return  project;
    }
    @DeleteMapping("/remove-project/{project-id}")
    public void  removeProject(@PathVariable("project-id") Long blId){
        projectService.removeProject(blId);
    }
    @PutMapping("/modify-project")
    public Project modifyProject(@RequestBody Project b){
        Project project = projectService.modifyProject(b);
        return project ;
    }
}
