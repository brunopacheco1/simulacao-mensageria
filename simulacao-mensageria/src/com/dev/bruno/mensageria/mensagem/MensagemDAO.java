package com.dev.bruno.mensageria.mensagem;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MensagemDAO {

	@PersistenceContext
	protected EntityManager manager;
	
	public Mensagem get(Long id) throws Exception {
		if(!exists(id)) {
			throw new Exception("Mensagem não encontrada");
		}
		
		Mensagem result = manager.find(Mensagem.class, id);
		
		return result;
	}

	public void remove(Mensagem Mensagem) throws Exception {
		if(Mensagem == null) {
			throw new Exception("Mensagem não encontrada");
		}
		
		manager.remove(Mensagem);
	}

	public void add(Mensagem Mensagem) throws Exception {
		if(Mensagem == null) {
			throw new Exception("Mensagem não encontrada");
		}
		
		manager.persist(Mensagem);
	}
	
	public void update(Mensagem Mensagem) throws Exception {
		if(Mensagem == null) {
			throw new Exception("Mensagem não encontrada");
		}
		
		manager.merge(Mensagem);
	}

	public List<Mensagem> list(String queryStr, Integer start, Integer limit) throws Exception {
		if(start == null || limit == null) {
			throw new Exception("start e limit são obrigatórios");
		}
		
		StringBuilder hql = new StringBuilder("select m from Mensagem m where 1=1");
		
		
		if(queryStr != null && !queryStr.isEmpty()) {
			hql.append(" and nome like :query");
		}
		
		TypedQuery<Mensagem> query = manager.createQuery(hql.toString(), Mensagem.class);
		
		if(queryStr != null && !queryStr.isEmpty()) {
			query.setParameter("nome", "%" + queryStr + "%");
		}
		
		return query.setFirstResult(start).setMaxResults(limit).getResultList();
	}
	
	public List<Mensagem> list() {
		return manager.createQuery("select m from Mensagem m order by m.id", Mensagem.class).getResultList();
	}

	public Long count(String queryStr) {
		StringBuilder hql = new StringBuilder("select count(m) from Mensagem m where 1=1");
		
		if(queryStr != null && !queryStr.isEmpty()) {
			hql.append(" and nome like :query");
		}
		
		TypedQuery<Long> query = manager.createQuery(hql.toString(), Long.class);
		
		if(queryStr != null && !queryStr.isEmpty()) {
			query.setParameter("nome", "%" + queryStr + "%");
		}
		
		return query.getSingleResult();
	}
	
	public Boolean exists(Long id) throws Exception {
		if(id == null) {
			throw new Exception("id é obrigatório");
		}
		
		Long result = manager.createQuery("select count(m) from Mensagem m where m.id = :id", Long.class).setParameter("id", id).getSingleResult();
		
		return result > 0;
	}
}