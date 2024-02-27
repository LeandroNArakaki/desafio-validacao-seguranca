package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City city = new City();
        copyDtoToEntity(dto, city);
        city = repository.save(city);
        return new CityDTO(city);
    }

    @Transactional(readOnly = true)
    public List<CityDTO> findAllCities() {
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream().map(CityDTO::new).toList();
    }


    private void copyDtoToEntity(CityDTO dto, City city) {
        city.setName(dto.getName());

        city.getEvents().clear();
        for (EventDTO eventDTO : dto.getEvents()) {
            Event event = eventRepository.getReferenceById(eventDTO.getId());
            city.getEvents().add(event);
        }
    }


}
