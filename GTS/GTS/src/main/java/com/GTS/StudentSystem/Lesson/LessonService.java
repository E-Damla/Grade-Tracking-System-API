package com.GTS.StudentSystem.Lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//iş katmanı
@Service
public class LessonService {
    // veritabanı işlemleri bağımlılığı
    private final LessonRepo lessonRepo;

    // LessonRepo classının enjekte edilmesi
    @Autowired
    public LessonService(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    //tüm dersleri getir
    public List<LessonEntity> getAllLessons() {
        return lessonRepo.findAll();
    } //repodaki tüm dersleri listele

    public LessonEntity getLessonById(Long id) {
        return lessonRepo.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    //yeni ders ekleme
    public LessonEntity addLesson(LessonEntity lesson) {
        return lessonRepo.save(lesson);
    } //repoya(db) yeni dersi kaydet

    // ders güncelleme
    public LessonEntity updateLesson(Long id, LessonEntity lesson) {
        // girilen id bilgisini veritabanında ara,ders bulunmazsa Ders bulunamadı hatası fırlat
        LessonEntity existingLesson = lessonRepo.findById(id).orElseThrow(()
                -> new RuntimeException("Lesson not found"));
        //bulunursa dersin ismini güncelle
        existingLesson.setLesson_name(lesson.getLesson_name());
        //güncellenmiş dersi db kaydet ve kaydedilen nesneyi döndür
        return lessonRepo.save(existingLesson);
    }
    // girilen idye ait olan dersi sil
    public void deleteLesson(Long id) {
        lessonRepo.deleteById(id);
    } //db sil
}
