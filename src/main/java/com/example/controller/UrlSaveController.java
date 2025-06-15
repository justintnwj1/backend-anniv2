package com.example.demo.controller;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.UrlUpdateRequest;
import com.example.demo.model.Word;
import com.example.demo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlSaveController {

    private final WordRepository wordRepository;

    @Autowired
    public UrlSaveController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    // ✅ Endpoint untuk menyimpan URL ke kolom dinamis (gambar1 - gambar17)
    @PostMapping("/update-url")
    public ResponseEntity<?> updateImageUrl(@RequestBody UrlUpdateRequest request) {
        Optional<Word> wordOpt = wordRepository.findById(request.getId());

        if (wordOpt.isEmpty()) {
            return ResponseEntity
            .badRequest()
            .body(new ApiResponse("Word with this ID not found", request.getId()));
        }

        Word word = wordOpt.get();
        String fieldName = request.getTable();

        try {
            Field field = Word.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            if (!field.getType().equals(String.class)) {
               return ResponseEntity.badRequest().body(new ApiResponse("Invalid field type", request.getId()));
            }

            field.set(word, request.getUrl());
            wordRepository.save(word);

            return ResponseEntity.ok(new  ApiResponse("URL saved to " + fieldName + " successfully", word.getId()));
        } catch (NoSuchFieldException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Field '" + fieldName + "' not found", request.getId()));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to access field: " + e.getMessage(), request.getId()));
        }
    }
}
