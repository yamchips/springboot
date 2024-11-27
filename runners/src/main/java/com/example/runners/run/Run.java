package com.example.runners.run;

import java.time.LocalDateTime;

public record Run(Integer id, String title, LocalDateTime startedOn, LocalDateTime completedOn, Integer miles,
		Location location) {
	// automatically have getters and constructor, immutable class, cannot change it
}
