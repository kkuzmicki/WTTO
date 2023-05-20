package com.example.libraryapplication.controller.api;

import com.example.libraryapplication.api.model.LibraryDTO;
import com.example.libraryapplication.api.model.ReaderDTO;
import com.example.libraryapplication.services.LibraryService;
import com.example.libraryapplication.services.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reader/")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDTO getReaderById(@PathVariable Long id){
        ReaderDTO readerDTO = readerService.getReaderById(id);
        return readerDTO;
    }

    @GetMapping("findByLastName")
    @ResponseStatus(HttpStatus.OK)
    public List<ReaderDTO> getReadersByLastName(@RequestParam String lastName){
        return readerService.getReadersByLastName(lastName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDTO createNewReader(@RequestBody ReaderDTO readerDTO) {
        return readerService.createNewReader(readerDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDTO updateReader(@PathVariable Long id, @RequestBody ReaderDTO readerDTO){
        return readerService.updateReader(id, readerDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteReader(@PathVariable Long id){
        readerService.deleteReaderById(id);
        return "reader delete called";
    }
}
