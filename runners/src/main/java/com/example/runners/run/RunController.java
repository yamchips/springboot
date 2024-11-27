package com.example.runners.run;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {

//	private final RunRepository runRepo;
//	
//	public RunController(RunRepository runRepo) {
//		this.runRepo = runRepo;
//	}
//	
//	@GetMapping("/api/runs")
//	public List<Run> findAll() {
//		return runRepo.findAll();
//	}
	
	@GetMapping("/hello")
	public String home() {
		return "Hello, Runners!";
	}

}
