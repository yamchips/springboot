package com.example.runners.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {
	
	
	
	@GetMapping("/hello")
	String home() {
		return "Hello, Runners!";
	}

}
