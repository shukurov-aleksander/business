package com.ku.business.service;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.dtomapper.DetailDtoMapper;
import com.ku.business.repository.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    private final DetailRepository repository;

    public DetailService(DetailRepository repository) {
        this.repository = repository;
    }

    public Optional<DetailDto> findById(Long id) {
        return Optional.of(DetailDtoMapper.toDto(repository.findById(id).get()));
    }

    public List<DetailListDto> findAll() {
        return DetailDtoMapper.toListDto(repository.findAll());
    }

    public void save(DetailSaveDto detail) {
        repository.save(DetailDtoMapper.fromSaveDto(detail));
    }

    public void update(DetailSaveDto detail) {
        repository.save(DetailDtoMapper.fromSaveDto(detail));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}