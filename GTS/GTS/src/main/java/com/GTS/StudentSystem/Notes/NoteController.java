package com.GTS.StudentSystem.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//web servislerini sağlamak için
@RestController
@RequestMapping("/api/note") // /api/note pathine gelen isteklere yanıt verir
public class NoteController {

    private final NoteService noteService; // NoteEntity sınıfından alınacak bağımlılıklar için

    @Autowired //NoteService classının enjekte eilmesi
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/dto") // Yüm notları getir
    public List<NoteDTO> getAllNoteDTOs() {
        return noteService.getAllNoteDTOs(); //NoteServiceden DTO listesini al
    }

    @GetMapping("/{id}") //idlere göre belirli bir notu döndür
    public NoteDTO getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id); //noteserviceden belirli idye göre notları al
    }

    @PostMapping // yeni öğrnci oluştur
    public NoteDTO addNote(@RequestBody NoteEntity note) {
        try {
            return noteService.addNote(note); // notu ekle ve döndür
        } catch (Exception e) { //hata surumunda fırlat
            e.printStackTrace();
            throw new RuntimeException("Error adding note", e); //hata mesajı
        }
    }

    @PutMapping("/{id}") //idye gmre veri güncelleme işlemi
    public NoteDTO updateNote(@PathVariable Long id, @RequestBody NoteEntity note) {
        return noteService.updateNote(id, note); //idye göre notu güncelle ve NoteEntitydeki notu döndür
    }

    @DeleteMapping("/{id}") //idye göre veriyi sil
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id); //noteService'den verilen idye göre notu sil.
    }
}