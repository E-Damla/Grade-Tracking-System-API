package com.GTS.StudentSystem.Lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//vertabanı işlemlerini yönet
@Repository
public interface LessonRepo extends JpaRepository<LessonEntity,Long> {
    //JpaRepositoryden miras alır db için gerekli metodlar hazır olması için.
    //LessonEntity sınıfını kullanarak lesson tablosunu sorgula
}
