package com.GTS.StudentSystem.Notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//veri erişim katmanı
@Repository
public interface NoteRepo extends JpaRepository<NoteEntity,Long> {

    @Query("SELECT new com.GTS.StudentSystem.Notes.NoteDTO(n.id, n.note_value, s.name, l.lesson_name) " + //veritabanındaki özel sorgu
            "FROM NoteEntity n " +// NoteEntity tablosunu sorgula, n burada alias olarak kullan.
            "JOIN n.student s " +// NoteEntity ile ilişkili StudentEntity'yi inner join ile birleştir. n.student, NoteEntity'deki student alanıdır.
            "JOIN n.lesson l")// NoteEntity ile ilişkili LessonEntity'yi inner join ile birleştir. n.lesson, NoteEntity'deki lesson alanıdır.
    List<NoteDTO> findAllWithStudentAndLesson();//özel sorguyu çalıştır ve NoteDTO nesnelerinden oluşan bir liste döndür.
}

