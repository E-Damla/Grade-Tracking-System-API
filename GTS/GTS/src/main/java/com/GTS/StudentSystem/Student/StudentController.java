package com.GTS.StudentSystem.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//API isteklerini karşılar
@RestController
@RequestMapping("/api/student") //api/student pathine gelen URL yanıtı vermesi için
public class StudentController {
    //nesne tanımlamaları
    private final StudentService studentService;
    private final StudentRepo studentRepo;

    // nesnelerin enjeksiyonu
    @Autowired
    public StudentController(StudentService studentService,StudentRepo studentRepo){
        this.studentService=studentService;
        this.studentRepo=studentRepo;
    }
    //tüm öğrencileri listele
    @GetMapping
    public List<StudentEntity> getAllStudent(){
        return studentService.getAllStudents();
    }
    //yeni öğrenci oluştur
    @PostMapping
    public void addStudent(@RequestBody StudentEntity student){
        studentService.addStudent(student);
    }
    //öğrencileri idlerine göre sil
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    // id verisiyle öğrencileri güncelle, student nesnesiyle öğrenci bilgilerini güncelle
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentEntity student) {
        studentService.updateStudent(id, student);
    }
}

