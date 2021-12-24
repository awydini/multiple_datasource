package net.aydini.example.swmds.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aydini.example.swmds.domain.entity.another.AnotherDbPersonEntity;
import net.aydini.example.swmds.domain.entity.main.PersonEntity;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */


@Slf4j
@Service
@RequiredArgsConstructor
public class PersonSyncScheduler {
	
	
	private final PersonCrudService personCrudService;
	
	
	private final AnotherDbPersonCrudService anotherDbPersonCrudService;
	
	
	
	@Async
	@Scheduled(fixedDelay = 10000)
	@Transactional(rollbackFor = RuntimeException.class)
	public void sync()
	{
		List<AnotherDbPersonEntity> unsyncedPersonsFromAnotherDb = anotherDbPersonCrudService.findUnsynced();
		
		if(CollectionUtils.isEmpty(unsyncedPersonsFromAnotherDb))
		{
			log.info("nothing to sync");
			return;
		}
		List<PersonEntity> persons = unsyncedPersonsFromAnotherDb.stream().map(PersonConverter::convertAnotherDbPersonEntity).collect(Collectors.toList());
		personCrudService.saveAll(persons);
		anotherDbPersonCrudService.markSynced(unsyncedPersonsFromAnotherDb);
	}

}
