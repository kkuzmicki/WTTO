package com.example.libraryapplication.services;

import com.example.libraryapplication.api.mapper.LibraryMapper;
import com.example.libraryapplication.api.mapper.ReaderMapper;
import com.example.libraryapplication.api.model.ReaderDTO;
import com.example.libraryapplication.domain.Library;
import com.example.libraryapplication.domain.Reader;
import com.example.libraryapplication.repositories.LibraryRepository;
import com.example.libraryapplication.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ReaderServiceImpl implements ReaderService {
    public ReaderServiceImpl(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    ReaderRepository readerRepository;
    ReaderMapper readerMapper;
    @Override
    public List<ReaderDTO> getAllReaders() {
        return readerRepository.findAll().stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    @Override
    public ReaderDTO getReaderById(Long id) {
        return readerMapper.readerToReaderDTO(readerRepository.findById(id).get());
    }

    @Override
    public List<ReaderDTO> getReadersByLastName(String lastName) {
        return readerRepository.getByLastName(lastName).stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    @Override
    public ReaderDTO createNewReader(ReaderDTO readerDTO) {
        Reader reader = readerMapper.readerDTOToReader(readerDTO);
        Reader savedreader = readerRepository.save(reader);

        return readerMapper.readerToReaderDTO(savedreader);
    }

    @Override
    public ReaderDTO updateReader(Long id, ReaderDTO readerDTO) {
        Reader reader = readerMapper.readerDTOToReader(readerDTO);
        reader.setId(id);

        Reader savereader = readerRepository.save(reader);

        return readerMapper.readerToReaderDTO(savereader);
    }

    @Override
    public void deleteReaderById(Long id) {
        readerRepository.deleteById(id);
    }
}
