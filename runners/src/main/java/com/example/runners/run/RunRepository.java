package com.example.runners.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

//@Repository
public class RunRepository {
	private List<Run> runs = new ArrayList<>();
	
	List<Run> findAll() {
		return runs;
	}
	
	@PostConstruct
	private void init() {
		runs.add(new Run(1,
				"Monday Morning Run",
				LocalDateTime.now(),
				LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
				3,
				Location.INDOOR));
		runs.add(new Run(2,
				"Wednesday Evening Run",
				LocalDateTime.now(),
				LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
				6,
				Location.OUTDOOR));
	}
}
