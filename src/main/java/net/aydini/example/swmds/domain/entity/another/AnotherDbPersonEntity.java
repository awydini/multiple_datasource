package net.aydini.example.swmds.domain.entity.another;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class AnotherDbPersonEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String family;
	
	private LocalDate birthDate;
	
	private Boolean synced;

}
