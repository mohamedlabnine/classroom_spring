package com.example.Spring12.controller;

import com.example.Spring12.annotation.LogAnnotation;
import com.example.Spring12.dto.AddClassForStudent;
import com.example.Spring12.dto.AddClassFromTeacher;
import com.example.Spring12.dto.RemoveClassfromStudent;
import com.example.Spring12.dto.Responce;
import com.example.Spring12.entity.Classe;
import com.example.Spring12.repesotorie.ClassRepository;
import com.example.Spring12.service.ClassService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "/api/class")
@ResponseBody
@AllArgsConstructor
public class ClassController {
    private ClassService classService;

    @Autowired
    private ClassRepository classRepository ;


    //All the Route for Student

    @GetMapping("/test")
    public String test(){
        return "test" ;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/getClassesStudent/{id_student}")
    public List<Classe> getClassesforStudent(@PathVariable int id_student){
        return classService.getClassesfroStudent(id_student) ;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/addclassfromStudent")
    public Responce addClassFromStudent(@RequestBody AddClassForStudent addClassForStudent){
        return  classService.addclassFromStudent(addClassForStudent.getId_Student() , addClassForStudent.getCode());
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/getClassById/{id_class}")
    public Classe getClassById(@PathVariable int id_class){
        return classService.getClassById(id_class) ;
    }


    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/removeClassfromStudent")
    public void removebyStudent(@RequestBody RemoveClassfromStudent removeClassfromStudent){
        classService.removeFromStudent(removeClassfromStudent.getId_Student() , removeClassfromStudent.getId_classe());
    }

    //All the Route for teacher

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/getClassesForTeacher/{id_Teacher}")
    public List<Classe> getClassesforEnsignemnt(@PathVariable int id_Teacher){
        return classService.getClassesforEnsignemnt(id_Teacher) ;
    }


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/addclassfromTeacher")
    public void addClassFromTeacher(@RequestBody AddClassFromTeacher addClassFromTeacher){
        classService.addclassFromEnsignement(addClassFromTeacher);
    }


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @DeleteMapping("/removeClassbyTeacher/{id_class}")
    public void removebyTeacher(@PathVariable int id_class){
         classService.removeclassFromEnsignement(id_class);
    }


    @GetMapping("/class/{id}")
    @LogAnnotation
    public Classe getClass(@PathVariable int id){
        return classRepository.findById(id).orElseThrow() ;
    }



}
