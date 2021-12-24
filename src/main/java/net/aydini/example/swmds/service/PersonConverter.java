package net.aydini.example.swmds.service;

import lombok.extern.slf4j.Slf4j;
import net.aydini.example.swmds.domain.entity.another.AnotherDbPersonEntity;
import net.aydini.example.swmds.domain.entity.main.PersonEntity;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */


@Slf4j
public class PersonConverter {

	
	public static PersonEntity convertAnotherDbPersonEntity(AnotherDbPersonEntity anotherDbPersonEntity)
	{
		if(anotherDbPersonEntity == null)
			return null;
		
		PersonEntity personEntity = new PersonEntity();
		personEntity.setBirthDate(anotherDbPersonEntity.getBirthDate());
		personEntity.setFamily(anotherDbPersonEntity.getFamily());
		personEntity.setName(anotherDbPersonEntity.getName());

		log.info("{} converted to {}",anotherDbPersonEntity.toString(),personEntity.toString());
		return personEntity;
	}
}
