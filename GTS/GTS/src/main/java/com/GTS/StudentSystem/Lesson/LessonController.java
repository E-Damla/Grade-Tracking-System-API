package com.GTS.StudentSystem.Lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//api isteklerini karşıla
@RestController
@RequestMapping("/api/lesson") // /api/lesson pathine gelen isteklere yanıt verir
public class LessonController {
    //lessonservice classındaki bağımlılıkların kullanımı
    private final LessonService lessonService;

    //LessonService classının onstructor ile enjekte edilmesi
    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    //Tüm dersleri getir
    @GetMapping
    public List<LessonEntity> getAllLessons() {
        return lessonService.getAllLessons();
    }
    //id bilgisi girilen dersleri getir
    @GetMapping("/{id}")
    public LessonEntity getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping //yeni ders ekleme
    public LessonEntity addLesson(@RequestBody LessonEntity lesson) {
        return lessonService.addLesson(lesson); //gelen ders verisini  db kaydet
    }

    @PutMapping("/{id}") //idye göre ders güncelleme
    public LessonEntity updateLesson(@PathVariable Long id, @RequestBody LessonEntity lesson) {
        return lessonService.updateLesson(id, lesson);// güncelle ve kaydet
    }

    @DeleteMapping("/{id}") //id verisiyle DELETE isteği gelince veriyi sil
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);// idye göre veriyi sil
    }
}
