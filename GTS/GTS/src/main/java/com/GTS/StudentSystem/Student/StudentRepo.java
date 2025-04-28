package com.GTS.StudentSystem.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Veritabanı işlemlerini yönetmek.
@Repository
public interface StudentRepo extends JpaRepository<StudentEntity,Long> {
    //StudentRepo,JpaRepositoryden miras alır db için gerekli metodlar hazır olması için
    //StudentEntity sınıfını kullanarak öğrenci tablosunu sorgula
}
