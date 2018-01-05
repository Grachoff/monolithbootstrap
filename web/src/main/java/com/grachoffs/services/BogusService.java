package com.grachoffs.services;

import basis.IdCodeNameDto;
import org.springframework.stereotype.Service;

@Service
public class BogusService {
    public IdCodeNameDto getBulk() {
        return new IdCodeNameDto();
    }

}
