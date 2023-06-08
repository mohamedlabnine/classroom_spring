package com.example.Spring12.service;

import com.example.Spring12.Dao.IClass;
import com.example.Spring12.dto.AddClassFromTeacher;
import com.example.Spring12.dto.Responce;
import com.example.Spring12.entity.Classe;
import com.example.Spring12.entity.Teacher;
import com.example.Spring12.entity.Student;
import com.example.Spring12.repesotorie.ClassRepository;
import com.example.Spring12.repesotorie.TeacherRepository;
import com.example.Spring12.repesotorie.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService  implements IClass {

    @Autowired
    private ClassRepository classRepository ;
    @Autowired
    private StudentRepository studentRepository ;
    @Autowired
    private TeacherRepository ensignementRepository ;


    public  Classe getClassById(int id_class) {
        Optional<Classe> classe = classRepository.findById(id_class) ;
        if(!classe.isEmpty()){
            return  classe.get() ;
        }
        return  null ;
    }
    public Responce addclassFromStudent(int id_student , String code){
        List<Classe> listofclass ;
        Optional<Student> student = studentRepository.findById(id_student) ;
        Optional<Classe> classe = classRepository.findByCode(code) ;
        if(!classe.isEmpty() && !student.isEmpty()){
            listofclass = student.get().getClassList();
            if(listofclass.contains(classe.get())){
                return new Responce(500 , "this class has already added");
            }
            student.get().getClassList().add(classe.get());
            studentRepository.save(student.get()) ;
            return new Responce(200 , "operation done");
        }

        return new Responce(404 , "code incorrect");
    }
    public void addclassFromEnsignement(AddClassFromTeacher addClassFromEnsignement){
        Optional<Teacher> ensignement = ensignementRepository.findById(addClassFromEnsignement.getId_Teacher()) ;
        if(!ensignement.isEmpty()){
            Classe classe = new Classe(addClassFromEnsignement.getName() ,
                    addClassFromEnsignement.getImage() ,
                    addClassFromEnsignement.getBranch(),
                    addClassFromEnsignement.getDescription(),
                    ensignement.get()) ;
            classRepository.save(classe) ;

        }
    }
    public List<Classe> getClassesfroStudent(int id_student){
        Optional<Student> student = studentRepository.findById(id_student) ;
        if(!student.isEmpty()) {
            return  student.get().getClassList();
        }
        return null ;
    }
    public List<Classe> getClassesforEnsignemnt(int id_ensignement) {
        Optional<Teacher> ensignement = ensignementRepository.findById(id_ensignement) ;
        if(!ensignement.isEmpty()){
            return  ensignement.get().getClassList() ;
        }
        return null ;
    }
    @Transactional
    public void removeclassFromEnsignement(int id_class){
        Optional<Classe> classe = classRepository.findById(id_class) ;
        if (!classe.isEmpty()) {
            for (Student student : classe.get().getStudent()) {
                student.getClassList().remove(classe.get());
                studentRepository.save(student) ;
            }
            classe.get().getStudent().clear();
            classRepository.delete(classe.get());
        }
    }
    @Transactional
    public void removeFromStudent(int id_Student ,int id_class){
        Optional<Student> student = studentRepository.findById(id_Student) ;
        Optional<Classe> classe = classRepository.findById(id_class) ;
        if (!student.isEmpty() && !classe.isEmpty()){
            student.get().getClassList().remove(classe.get()) ;
            classe.get().getStudent().remove(student.get()) ;
            studentRepository.save(student.get()) ;
            classRepository.save(classe.get()) ;
        }
    }


}
