package com.airway.controller;

import com.airway.Service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;
}
