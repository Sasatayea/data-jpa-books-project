package com.global.book.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.global.book.entity.Auther;
import com.global.book.error.RecoredNotFoundExecption;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseService<T extends BaseEntity<ID>, ID extends Number> {

	@Autowired
	BaseRepository<T, ID> baseRepository;

	@Autowired
	private MessageSource messageSource ;
	
	public T findById(ID id) {

		Optional<T> entity = baseRepository.findById(id) ;
		
		if(entity.isPresent()) {
			return entity.get() ;
		}else {
			String [] msgParam = {id.toString()} ;
			String msg =  messageSource.getMessage("validation.recoredNotFound.message", msgParam , LocaleContextHolder.getLocale());
			throw new RecoredNotFoundExecption(msg);
		}
		
	}

	public T getReferenceById(ID id) {

		return baseRepository.getReferenceById(id);
	}

	public List<T> findAll() {

		return baseRepository.findAll();
	}

	public T insert(T entity) {

		if (entity.getId() != null) {
			throw new RuntimeException();
		}
		return baseRepository.save(entity);
	}

	public List<T> insertAll(List<T> entity) {

		return baseRepository.saveAll(entity);
	}

	public T update(T entity) {

		return baseRepository.save(entity);
	}

	public void deleteById(ID id) {
		baseRepository.deleteById(id);
	}

}
