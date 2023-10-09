package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Base64;

@Entity
public class Cours {


    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String courseFile;

    public Cours() {

    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCourseFile(){return courseFile;}
    public void setCourseFile(String courseFile){this.courseFile=courseFile;}

    public Cours(String courseFile,String name) {
        this.courseFile = courseFile;
        this.name=name;
    }
}
