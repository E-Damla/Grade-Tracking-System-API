package com.GTS.StudentSystem.Notes;

import com.GTS.StudentSystem.Lesson.LessonRepo;
import com.GTS.StudentSystem.Student.StudentEntity;
import com.GTS.StudentSystem.Student.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.GTS.StudentSystem.Lesson.LessonEntity;

@Service
public class NoteService {
    @Autowired
    private StudentRepo studentRepository; //ÖĞRENCİ VERİTABANI İÇİN ENJEKSİYON

    @Autowired
    private LessonRepo lessonRepository; //DERS DB İÇİN ENJEKSİYON

    private final NoteRepo noteRepo; //NOT DB İÇİN ENJEKSİYON

    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    } //NOTEREPO CONSTRUCTOR ENJEKSİYON

    //YENİ NOT EKLEME İŞLEMİ
    public NoteDTO addNote(NoteEntity note) {
        //IDLERE GÖRE ÖĞRENCİ BUL
        StudentEntity student = studentRepository.findById(note.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Student not found")); //BULAMAZSAN HATA FIRLAT
        //GELEN DERS IDSİNE GÖRE DERS BUL
        LessonEntity lesson = lessonRepository.findById(note.getLesson().getId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        // noteentitye db bulunan doğru öğrenci ve dersi set et
        note.setStudent(student);
        note.setLesson(lesson);

        //notu kaydet
        NoteEntity savedNote = noteRepo.save(note);

        //kaydedilen notu NoteDTO formatında döndür
        return new NoteDTO(
                savedNote.getId(), //Not ıdsi
                savedNote.getNote_value(),
                savedNote.getStudent().getName(), //getStudent bu nota ait student nesnesinin öğrenci adını getir.
                savedNote.getLesson().getLesson_name()
        );
    }

    //tüm notları NoteDTO formatında getir
    public List<NoteDTO> getAllNoteDTOs() {
        return noteRepo.findAll().stream() //db'den tüm notları çek
                .map(note -> new NoteDTO( //her not için dto oluştur
                        note.getId(),
                        note.getNote_value(),
                        // Öğrenci boşsa "Unknown"
                        //bu notun öğrencisi var mı yok mu varsa getStudent.getNAme() yani öğrenci adını getir,
                        //eğer öğrenci bilgisi yoksa "Unkown getir".
                        note.getStudent() != null ? note.getStudent().getName() : "Unknown",
                        // Ders boşsa "Unknown"
                        note.getLesson() != null ? note.getLesson().getLesson_name() : "Unknown"
                ))
                .collect(Collectors.toList());// Hepsini listeye topla
    }
    // ID'ye göre bir notu getirir (DTO olarak)
    public NoteDTO getNoteById(Long id) {
        // ID'yi bulursa DTO'ya çevirir
        return noteRepo.findById(id)
                //NoteEntity nesneleri NoteDTOya çevir,notun idsini value'sini name'i ve lesson_name al
                //bunları yeni bir NoteDTO'ya çevir
                .map(n -> new NoteDTO(n.getId(), n.getNote_value(), n.getStudent().getName(), n.getLesson().getLesson_name()))
                // Bulunamazsan hata fırlat
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
    // ID'ye göre notu günceller
    public NoteDTO updateNote(Long id, NoteEntity note) {
        // İlk önce güncellenecek notu bul, bulamazsan hata fırlat
        NoteEntity existingNote = noteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        // Alanları güncelle
        existingNote.setNote_value(note.getNote_value()); //yeni gelen note içindeki değeri al
        // eskiden kayıtlı olan existingNote içine yaz.
        existingNote.setStudent(note.getStudent());
        existingNote.setLesson(note.getLesson());

        // Güncellenmiş notu kaydet
        NoteEntity updatedNote = noteRepo.save(existingNote);
        // DTO formatında geri döndür.
        return new NoteDTO(updatedNote.getId(), updatedNote.getNote_value(),
                updatedNote.getStudent().getName(), updatedNote.getLesson().getLesson_name());
    }
    // ID'ye göre notu sil
    public void deleteNote(Long id) {
        noteRepo.deleteById(id); // Belirtilen ID'deki notu sil

    }
}