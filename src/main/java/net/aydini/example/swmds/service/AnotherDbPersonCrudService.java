package net.aydini.example.swmds.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aydini.example.swmds.dao.another.AnotherDbPersonDao;
import net.aydini.example.swmds.domain.entity.another.AnotherDbPersonEntity;


/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "secondTransactionManager")
public class AnotherDbPersonCrudService {
	
	
	private final AnotherDbPersonDao anotherDbPersonDao;
	
	public List<AnotherDbPersonEntity> findUnsynced()
	{
		return anotherDbPersonDao.findUnsynced();
	}

	
	public void markSynced(List<AnotherDbPersonEntity>  list)
	{
		if(CollectionUtils.isEmpty(list))
			throw new IllegalArgumentException("list is empty");
		List<Long> ids = list.stream().map(AnotherDbPersonEntity::getId).collect(Collectors.toList());
		anotherDbPersonDao.markSynced(ids);
		log.info("marked {} records: {} ",ids.size(),ids);
	}

}
