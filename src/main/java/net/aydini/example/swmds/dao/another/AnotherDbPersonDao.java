package net.aydini.example.swmds.dao.another;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.aydini.example.swmds.domain.entity.another.AnotherDbPersonEntity;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */
public interface AnotherDbPersonDao extends CrudRepository<AnotherDbPersonEntity, Long> {

	@Query("select adpe from AnotherDbPersonEntity adpe where synced is null or synced = false")
	public List<AnotherDbPersonEntity> findUnsynced();

	@Modifying
	@Query("update AnotherDbPersonEntity set synced = true where id in :ids ")
	public void markSynced(@Param("ids") List<Long> ids);

}
