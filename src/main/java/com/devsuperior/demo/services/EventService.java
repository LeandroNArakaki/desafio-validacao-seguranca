package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;


    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        copyDtoToEntity(dto, event);
        event = repository.save(event);
        return new EventDTO(event);
    }

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable) {
        Page<Event> list = repository.findAll(pageable);
        return list.map(EventDTO::new);
    }

    private void copyDtoToEntity(EventDTO dto, Event event) {
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());
        event.setDate(dto.getDate());
        if (dto.getCityId() != null) {
            City city = cityRepository.getReferenceById(dto.getCityId());
            event.setCity(city);
        }

    }
}
