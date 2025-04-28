package com.GTS.StudentSystem.Notes;


import com.GTS.StudentSystem.Lesson.LessonEntity;
import com.GTS.StudentSystem.Student.StudentEntity;
import jakarta.persistence.*;

//Jpa Entity
@Entity
@Table(name = "note") //veritabanındaki note tablosuna karşılık gelen tablo
public class NoteEntity {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id otomatik artsın
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST) //NoteEntity ve StudentEntity arasındaki
    private StudentEntity student; //öğrenci bilgileri

    @ManyToOne(cascade = CascadeType.PERSIST) //NoteEntity ve LessonEntity arasındaki ilişki
    private LessonEntity lesson; //ders bilgileri

    private Integer note_value;

    //boş constructor
    public NoteEntity() {
    }
    //dolu constructor. parametreleri kullanarak NoteEntity nesnesi oluşturur
    public NoteEntity(Long id, Integer note_value, StudentEntity student, LessonEntity lesson) {
        this.id = id;
        this.note_value = note_value;
        this.student = student;
        this.lesson = lesson;
    }

    //Erişim sağlanmak istenen alanların getter setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNote_value() {
        return note_value;
    }

    public void setNote_value(Integer note_value) {
        this.note_value = note_value;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }
}