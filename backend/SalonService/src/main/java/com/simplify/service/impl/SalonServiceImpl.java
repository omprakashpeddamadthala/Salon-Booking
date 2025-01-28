package com.simplify.service.impl;

import com.simplify.repository.SalonRepository;
import com.simplify.service.SalonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;
}
