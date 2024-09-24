package com.workbeattalent.sport_club.cycling;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/v1/cycling"})
public record CyclingController() {
}
