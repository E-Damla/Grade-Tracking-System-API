package com.GTS.StudentSystem.Student;

import com.GTS.StudentSystem.Notes.NoteEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

//Jpa Entity
@Entity
@Table(name = "student")//Veritabanındaki student tablosuna denk gelen class
public class StudentEntity {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id otomatik artsın
    private Long id;

    private String name;
    private String email;
    @Column(name = "birth_date")
    private LocalDate birth_date;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL) //Bir öğrencinin her dersten notu olması gerektiği için
    private List<NoteEntity> notes;

    public StudentEntity() { //Hibernate kullanımı boş constructor
    }
    //Parametreli constructor
    public StudentEntity(Long id, String name, String email, LocalDate birth_date, List<NoteEntity> notes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth_date = birth_date;
        this.notes = notes;
    }
    //Alanlara erişim için getter ve setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }
}
