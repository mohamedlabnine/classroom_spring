package com.example.Spring12.service;

import com.example.Spring12.Dao.ICours;
import com.example.Spring12.dto.AddCoursFromTeacher;
import com.example.Spring12.dto.EditCours;
import com.example.Spring12.dto.Responce;
import com.example.Spring12.entity.Classe;
import com.example.Spring12.entity.Cours;
import com.example.Spring12.repesotorie.ClassRepository;
import com.example.Spring12.repesotorie.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService implements ICours {
    @Autowired
    private CoursRepository coursRepository ;
    @Autowired
    private ClassRepository classRepository ;

    public void addcoursfromensignement(AddCoursFromTeacher addCoursFromEnsignement){
        Optional<Classe> classe = classRepository.findById(addCoursFromEnsignement.getId_Class()) ;
        if(!classe.isEmpty()){
            Cours cours = new Cours(addCoursFromEnsignement.getName(),
                    addCoursFromEnsignement.getPdf_name() ,
                    addCoursFromEnsignement.getDescription(),
                    classe.get()) ;
            coursRepository.save(cours) ;
        }
    }

    public List<Cours> getCourses(int id_class){
        Optional<Classe> classe = classRepository.findById(id_class) ;
        if(!classe.isEmpty()){
            return classe.get().getCoursList() ;
        }
        return  null ;
    }

    public void removeCours(int id_cours){
        Optional<Cours> cours = coursRepository.findById(id_cours) ;
        if(!cours.isEmpty()){
            coursRepository.delete(cours.get());
        }
    }

    public void EditCours(EditCours editCours){
        Optional<Cours> cours =coursRepository.findById(editCours.getId_cours()) ;
        if(!cours.isEmpty()){
            cours.get().setName(editCours.getName());
            cours.get().setDescription(editCours.getDescription());
            cours.get().setPdf_name(editCours.getPdf_name());
            coursRepository.save(cours.get()) ;
        }
    }

    public Responce upload(MultipartFile file){
        String path = "./src/main/resources/static/"+file.getOriginalFilename() ;
        try {
            Files.copy(file.getInputStream() , Paths.get(path)) ;
        } catch (IOException e) {
            return new Responce(403 , "You file has not uploaded") ;
        }
        return new Responce(200 , "You file has uploaded") ;
    }

    public ResponseEntity<Resource> download(String name) {
        String filesPath = "./src/main/resources/static/" ;
        Path path = Paths.get(filesPath).resolve(name) ;
        try {
            Resource file = new UrlResource(path.toUri()) ;
            Path path1 = file.getFile().toPath();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path1))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

