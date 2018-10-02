package com.IyfGZB.controller;

import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.domain.CallingSewa;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.dto.CallingSewaDTO;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.services.CallingSewaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

/**
 * Created by sahil on 23/9/18.
 */
@RestController
@RequestMapping("/callingSewa")

public class AdminCallingSewaController {

    @Autowired
    SeminarRepo seminarRepo;

    @Autowired
    private CallingSewaService callingSewaService;

    @GetMapping("/{seminarId}/{itemPerPage}/{pageIndex}")
    public Map<String,Object> getCallingSewa(@PathVariable("seminarId") Long seminarId,
                                                  @PathVariable("itemPerPage") Integer itemPerPage,
                                                  @PathVariable("pageIndex") Integer pageIndex){
        return callingSewaService.getCallingSewa(seminarId, itemPerPage, pageIndex);

    }

    @GetMapping("/allocate")
    @PermitAll
    public void allocateCallingSewa(){
        Seminar seminar = seminarRepo.getOne(30L);
        callingSewaService.allocateCallingSewa(seminar);
    }



}
