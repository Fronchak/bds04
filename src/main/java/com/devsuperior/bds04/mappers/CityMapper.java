package com.devsuperior.bds04.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;

@Service
public class CityMapper {

	public CityDTO convertEntityToDTO(City entity) {
		CityDTO dto = new CityDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
	
	public void copyDTOToEntity(CityDTO dto, City entity) {
		entity.setName(dto.getName());
	}
	
	public List<CityDTO> convertEntityListToDTOList(List<City> list) {
		return list.stream()
				.map(entity -> convertEntityToDTO(entity))
				.collect(Collectors.toList());
	}
}
