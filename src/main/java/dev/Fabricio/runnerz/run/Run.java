package dev.Fabricio.runnerz.run;

import java.time.LocalDateTime;

public record Run(Integer id, String title, LocalDateTime startedOn, LocalDateTime finishedOn, Integer miles,
		Location location) {
	

}
