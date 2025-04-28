package com.GTS.StudentSystem.Lesson;

import com.GTS.StudentSystem.Notes.NoteEntity;
import jakarta.persistence.*;

import java.util.List;
// jpa entity
@Entity
@Table(name = "lesson") // veritabanında lesson tablosunu kullan
public class LessonEntity {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id sırayla artsın
    private Long id;
    private String lesson_name;

    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL) //LessonEntity ve NoteEntity arasındaki ilişki
    private List<NoteEntity> notes; // notlar

    // hibernate için boş constructor
    public LessonEntity() {
    }

    // dolu constructor, verilerle LessonEntity oluştur
    public LessonEntity(Long id, String lesson_name, List<NoteEntity> notes) {
        this.id = id;
        this.lesson_name = lesson_name;
        this.notes = notes;
    }

    //erişim için gerekli olan getter ve setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }
}