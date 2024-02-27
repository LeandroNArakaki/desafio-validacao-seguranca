package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class CityDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String name;

    @JsonIgnore
    private List<EventDTO> events = new ArrayList<>();

    public CityDTO() {
    }

    public CityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityDTO(City entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventDTO> getEvents() {
        return events;
    }
}
