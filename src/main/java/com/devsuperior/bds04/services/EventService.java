package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.mappers.EventMapper;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private EventMapper mapper;
	
	@Transactional
	public EventDTO save(EventDTO dto) {
		Event entity = new Event();
		copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return mapper.convertEntityToDTO(entity);
	}
	
	private void copyDTOToEntity(EventDTO dto, Event entity) {
		mapper.copyDTOToEntity(dto, entity);
		entity.setCity(cityRepository.getOne(dto.getCityId()));
	}
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged(Pageable pageable) {
		Page<Event> page = repository.findAll(pageable);
		return mapper.convertEntityPageToDTOPage(page);
	}
}
