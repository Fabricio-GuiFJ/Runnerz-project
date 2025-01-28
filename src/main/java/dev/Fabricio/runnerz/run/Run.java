package dev.Fabricio.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Run(
	@Id
	Integer id,
	
	@NotEmpty
	String title,
	
	
	LocalDateTime startedOn, 
	LocalDateTime completedOn, 

	@Positive
	Integer miles,

	@Version
	Integer version,

	Location location) {}
