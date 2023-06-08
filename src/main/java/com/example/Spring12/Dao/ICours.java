package com.example.Spring12.Dao;

import com.example.Spring12.dto.AddCoursFromTeacher;
import com.example.Spring12.dto.EditCours;
import com.example.Spring12.dto.Responce;
import com.example.Spring12.entity.Cours;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICours {
    public void addcoursfromensignement(AddCoursFromTeacher addCoursFromEnsignement) ;
    public List<Cours> getCourses(int id_class) ;
    public void removeCours(int id_cours) ;
    public void EditCours(EditCours editCours) ;
    public Responce upload(MultipartFile file) ;
    public ResponseEntity<Resource> download(String name) ;
}
