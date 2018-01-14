package com.altarix.controllers.common;

import com.altarix.ConstantsHolder;
import com.altarix.dtos.security.Heartbeat;
import com.altarix.services.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utility")
public class UtilityController extends AbstractWebController{
    @Autowired
    private Common common;

    @RequestMapping(value = "/constants", method = RequestMethod.GET)
    public ConstantsHolder getConstants(){
        return ConstantsHolder.getConstatsHolder();
    }

    @RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
    public Heartbeat test(){
        return common.heartbeat();
    }
}
