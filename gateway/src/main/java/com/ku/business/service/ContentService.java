package com.ku.business.service;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    public Optional<ContentDto> findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String fooUrl = "http://localhost:8080/contents/";
        ContentDto dns =  restTemplate.getForObject(fooUrl, ContentDto.class, id);
        return Optional.of(dns);
    }

    public List<ContentListDto> findAll() {
       // return ContentDtoMapper.toListDto(contentDao.findAll());
        return null;
    }

    public void save(ContentSaveDto content) {
       // contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void update(ContentSaveDto content) {
     //   contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void delete(Long id) {
     //   contentDao.deleteById(id);
    }
}
