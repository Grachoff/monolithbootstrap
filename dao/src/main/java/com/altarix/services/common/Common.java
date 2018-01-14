package com.altarix.services.common;

import com.altarix.dtos.security.Heartbeat;
import org.springframework.stereotype.Service;

@Service
public class Common {
    public Heartbeat heartbeat() {return new Heartbeat("OK");}
}
