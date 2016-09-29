package com.dev.bruno.mensageria.mensagem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.inject.Inject;

import org.apache.commons.beanutils.PropertyUtils;

@Singleton
public class MensagemService {

	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Inject
	private MensagemDAO mensagemDAO;
	
	public List<MensagemDTO> list(String queryStr, Integer start, Integer limit) throws Exception {
		if(start == null) {
			start = 0;
		}
		
		if(limit == null) {
			limit = 100;
		}
		
		List<MensagemDTO> dtos = new ArrayList<>();
		
		for(Mensagem entity : mensagemDAO.list(queryStr, start, limit)) {
			dtos.add(entityToMensagemDTO(entity));
		}
		
		return dtos;
	}
	
	public List<MensagemDTO> list() throws Exception {
		List<MensagemDTO> dtos = new ArrayList<>();
		
		for(Mensagem entity : mensagemDAO.list()) {
			dtos.add(entityToMensagemDTO(entity));
		}
		
		List<MensagemDTO> result = new ArrayList<>();
		
		
		return result;
	}
	
	public MensagemDTO get(Long id) throws Exception {
		return entityToMensagemDTO(mensagemDAO.get(id));
	}
	
	public MensagemDTO add(MensagemDTO dto) throws Exception {
		validate(null, dto);
		
		Long timeToSleep = ThreadLocalRandom.current().nextLong(1000, 60000);
		
		System.out.println("Processando " + timeToSleep + " ms");
		
		Thread.sleep(timeToSleep);
		
		Mensagem entity = dtoToEntity(null, null, dto);
		
		mensagemDAO.add(entity);
		
		return entityToMensagemDTO(entity);
	}
	
	public MensagemDTO update(Long id, MensagemDTO dto) throws Exception {
		validate(id, dto);
		
		Long timeToSleep = ThreadLocalRandom.current().nextLong(1000, 60000);
		
		System.out.println("Processando " + timeToSleep + " ms");
		
		Thread.sleep(timeToSleep);
		
		Mensagem entity = mensagemDAO.get(id);
				
		entity = dtoToEntity(id, entity, dto);
		
		mensagemDAO.update(entity);
		
		return entityToMensagemDTO(entity);
	}
	
	public void remove(Long id) throws Exception {
		Mensagem entity = mensagemDAO.get(id);
		
		Long timeToSleep = ThreadLocalRandom.current().nextLong(1000, 60000);
		
		System.out.println("Processando " + timeToSleep + " ms");
		
		Thread.sleep(timeToSleep);
		
		mensagemDAO.remove(entity);
	}
	
	public Long count(String queryStr) {
		return mensagemDAO.count(queryStr);
	}
	
	public void validate(Long id, MensagemDTO dto) throws Exception {
		if(id != null && !mensagemDAO.exists(id)) {
			throw new Exception("Mensagem[" + id + "] não encontrado.");
		}
		
		dto.setId(id);
	}
	
	protected MensagemDTO entityToMensagemDTO(Mensagem entity) throws Exception {
		MensagemDTO dto = MensagemDTO.class.newInstance();
		
		if(entity == null) {
			return dto;
		}
		
		PropertyUtils.copyProperties(dto, entity);
		
		return dto;
	}
	
	protected Mensagem dtoToEntity(Long id, Mensagem entity, MensagemDTO dto) throws Exception {
		if(entity == null) {
			entity = Mensagem.class.newInstance();
		}
		
		if(dto == null) {
			return entity;
		}
		
		PropertyUtils.copyProperties(entity, dto);
		
		entity.setId(id);
		
		return entity;
	}
}