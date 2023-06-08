package com.example.Spring12.controller;

import com.example.Spring12.dto.AddCoursFromTeacher;
import com.example.Spring12.dto.EditCours;
import com.example.Spring12.dto.Responce;
import com.example.Spring12.entity.Cours;
import com.example.Spring12.service.CoursService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("api/cours")
@AllArgsConstructor
public class CoursController {
    private CoursService coursService;

    // this rout for All role

    @GetMapping("/getCourses/{id_class}")
    public List<Cours> getCoursesfromClass(@PathVariable int id_class){
        return  coursService.getCourses(id_class);
    }

    @GetMapping("/DownloadPDfcours/{name}")
    public ResponseEntity<Resource> download_cours(@PathVariable String name) throws IOException {
        return  this.coursService.download(name) ;
    }


    // this rout is work just for Role Teacher

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/addcours")
    public void addcours(@RequestBody AddCoursFromTeacher addCoursFromEnsignement){
        coursService.addcoursfromensignement(addCoursFromEnsignement);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @DeleteMapping("removeCours/{id_cours}")
    public void removeCours(@PathVariable  int id_cours){
        coursService.removeCours(id_cours);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("EditCours")
    public void EditCours(@RequestBody EditCours editCours){
        coursService.EditCours(editCours);
    }


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/uploadPdfcours")
    public Responce upload_cours(@RequestBody MultipartFile file)  {
        return this.coursService.upload(file) ;
    }


}

