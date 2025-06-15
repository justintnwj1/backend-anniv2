package com.example.demo.controller;

import com.example.demo.model.Word;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.FlagfotoResponse;
import com.example.demo.dto.FlagfotoRequest;
import com.example.demo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/post")
public class WordController {

    private final WordRepository wordRepository;

    @Autowired
    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    // Endpoint untuk POST data
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createWord(@RequestBody Word word) {
        Optional<Word> existingWord = wordRepository.findById(word.getId());
        if (existingWord.isPresent()) {
            return new ApiResponse("Word with this ID already exists", word.getId());
        }

        wordRepository.save(word);
        return new ApiResponse("Word created successfully", word.getId());
    }

    // Endpoint untuk GET data berdasarkan ID
    @GetMapping("/{id}")
    public Word getWordById(@PathVariable Long id) {
        Optional<Word> word = wordRepository.findById(id);
        if (word.isPresent()) {
            return word.get();  // Mengembalikan objek Word lengkap (termasuk gambar yang null)
        } else {
            throw new RuntimeException("Word not found with id " + id);
        }
    }

    @PostMapping("/fotoflag")
    public FlagfotoResponse setFlagFotoById(@RequestBody FlagfotoRequest request) {
        Optional<Word> optionalWord = wordRepository.findById(request.getId());

        if (optionalWord.isPresent()) {
            Word word = optionalWord.get();
            word.setFotoflag(request.getFotoflag());
            wordRepository.save(word);
            return new FlagfotoResponse("Flagfoto updated successfully", word.getId(), word.getFotoflag());
        } else {
            return new FlagfotoResponse("Word not found", request.getId(), "");
        }
    }

    @GetMapping("/get-info/fotoflag/{id}")
    public FlagfotoResponse getFlagfotoById(@PathVariable Long id) {
        Optional<Word> optionalWord = wordRepository.findById(id);

        if (optionalWord.isPresent()) {
            Word word = optionalWord.get();
            return new FlagfotoResponse("Flagfoto retrieved successfully", word.getId(), word.getFotoflag());
        } else {
            return new FlagfotoResponse("Word not found", id, null);
        }
    }
}
