package com.GTS.StudentSystem.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// iş kuralları
@Service
public class StudentService {
    //veritabanı işlemlerinin bağımlılığı
    private final StudentRepo studentRepo;
    @Autowired //Constructor ile StudentService enjekte edildi.
    public StudentService(StudentRepo studentRepo)
    {
        this.studentRepo=studentRepo;
    }
    public List<StudentEntity> getAllStudents()// tüm öğrencileri listeler
    {
        return studentRepo.findAll();//student tablosundaki tüm verileri getir.
    }
    public void addStudent(StudentEntity student) //Yeni öğrenci ekleme
    {
        studentRepo.save(student); // parametre olarak gelen öğrenci bilgisini veri tabanına kaydet.
    }
    public void deleteStudent(Long id) // idlerine göre öğrenci silme
    {
        studentRepo.deleteById(id); //verilen idye göre veri tabanından öğrencileri sil
    }
    public void updateStudent(Long id,StudentEntity newStudent) //öğrenci güncelleme
    {
        StudentEntity oldStudent=studentRepo.findById(id).orElseThrow(); // veritabanında öğrenci idsine göre öğrenciyi bul
        //eski öğrenc bilgilerini yeni gelen bilgilerle güncelle
        oldStudent.setName(newStudent.getName());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setBirth_date(newStudent.getBirth_date());
        //güncellenen öğrenci verisini veritabanına kaydet.
        studentRepo.save(oldStudent);
    }
}
