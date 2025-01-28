package com.simplify.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping
    public String homeControllerHandler(){
        log.info( "User service is up and running ... " );
        return "User service is up and running ...."  ;
    }

}
