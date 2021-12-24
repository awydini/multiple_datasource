package net.aydini.example.swmds.domain.entity.main;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;



@Data
@Entity
public class PersonEntity  {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String family;
	
	private LocalDate birthDate;

}
