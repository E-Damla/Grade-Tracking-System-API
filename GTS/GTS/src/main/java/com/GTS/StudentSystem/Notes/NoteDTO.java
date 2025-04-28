package com.GTS.StudentSystem.Notes;

public class NoteDTO {
    private Long id;
    private Integer note_value;
    private String student_name;
    private String lesson_name;

    public NoteDTO(Long id, Integer note_value, String student_name, String lesson_name) {
        this.id = id;
        this.note_value = note_value;
        this.student_name = student_name;
        this.lesson_name = lesson_name;
    }

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

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }
}