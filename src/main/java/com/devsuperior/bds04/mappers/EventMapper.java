package com.devsuperior.bds04.mappers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;

@Service
public class EventMapper {

	public EventDTO convertEntityToDTO(Event entity) {
		EventDTO dto = new EventDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDate(entity.getDate());
		dto.setUrl(entity.getUrl());
		dto.setCityId(entity.getCity().getId());
		return dto;
	}
	
	public void copyDTOToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
	}
	
	public Page<EventDTO> convertEntityPageToDTOPage(Page<Event> page) {
		return page.map(entity -> convertEntityToDTO(entity));
	}
}
