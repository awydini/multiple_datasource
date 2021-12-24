package net.aydini.example.swmds.dao.main;

import org.springframework.data.repository.CrudRepository;

import net.aydini.example.swmds.domain.entity.main.PersonEntity;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */
public interface PersonDao extends CrudRepository<PersonEntity, Long> {
	


}
