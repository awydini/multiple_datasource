package net.aydini.example.swmds.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aydini.example.swmds.dao.main.PersonDao;
import net.aydini.example.swmds.domain.entity.main.PersonEntity;


/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "mainTransactionManager")
public class PersonCrudService {
	

	
	private final PersonDao personDao;
	
	
	
	public void save(PersonEntity person)
	{
		if(person == null)
			throw new IllegalArgumentException("person is null");
		personDao.save(person);
		log.info("data saved {}",person.toString());
	}

	
	public void saveAll(List<PersonEntity> persons)
	{
		if(CollectionUtils.isEmpty(persons))
			throw new IllegalArgumentException("persons is null");
		personDao.saveAll(persons);
		log.info(" saved {} persons",persons.size());
	}
}
