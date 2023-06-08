package com.example.Spring12.Dao;

import com.example.Spring12.dto.AddClassFromTeacher;
import com.example.Spring12.dto.Responce;
import com.example.Spring12.entity.Classe;

import java.util.List;

public interface IClass {
    public Classe getClassById(int id_class) ;
    public Responce addclassFromStudent(int id_student , String code) ;
    public void addclassFromEnsignement(AddClassFromTeacher addClassFromEnsignement) ;
    public List<Classe> getClassesfroStudent(int id_student) ;
    public List<Classe> getClassesforEnsignemnt(int id_ensignement) ;
    public void removeclassFromEnsignement(int id_class) ;
    public void removeFromStudent(int id_Student ,int id_class) ;
}
