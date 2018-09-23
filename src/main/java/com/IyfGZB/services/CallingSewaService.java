package com.IyfGZB.services;

import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.domain.CallingSewa;
import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.dto.CallingSewaDTO;
import com.IyfGZB.repositories.CallingSewaRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.securityservices.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by sahil on 23/9/18.
 */
@Service
public class CallingSewaService {

    @Autowired
    private CallingSewaRepo callingSewaRepo;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private SeminarRepo seminarRepo;

    public static final Logger logger = LoggerFactory.getLogger(SeminarOperation.class);


    public Map<String, Object> getCallingSewa(Long seminarId, Integer itemPerPage, Integer pageNumber) {
        Map<String, Object> map = new HashMap<>();
        Seminar seminar = seminarRepo.findSeminarById(seminarId);
        List<CallingSewaDTO> callingSewaDTOS = new ArrayList<>();
        if (seminar == null) {
            throw new RuntimeException("Seminar Or User Not Found ");
        } else {
            UserInfo userInfo = CurrentUser.getCurrentUser();
            PageRequest pageRequest = new PageRequest(pageNumber, itemPerPage);
            Page<CallingSewa> callingSewas = callingSewaRepo.findAllByVolunteerAndSeminar(userInfo, seminar, pageRequest);
            callingSewas.getContent().forEach(callingSewa -> {
                CallingSewaDTO callingSewaDTO = new CallingSewaDTO();
                callingSewaDTO.setClasslevel(callingSewa.getUser().getClassLevel());
                callingSewaDTO.setCurrentStatus(callingSewa.getResponseStatus());
                callingSewaDTO.setEmailId(callingSewa.getUser().getEmail());
                callingSewaDTO.setMobileNumber(callingSewa.getUser().getMobileNumber());
                callingSewaDTO.setUserName(callingSewa.getUser().getUsername());

                callingSewaDTOS.add(callingSewaDTO);
            });
            map.put("callingSewa", callingSewaDTOS);
            map.put("totalPage", callingSewas.getTotalElements());
        }

        return map;
    }

    @Async
    public void allocateCallingSewa(Seminar seminar) {

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        Role role = new Role();
        role.setRole(RoleConstant.ROLE_IYF_VOLUNTEER);
        Predicate<UserInfo> predicate = userInfo -> userInfo.getRoles().contains(role);
        List<UserInfo> iyfVolunteers = userInfoList.stream().filter(predicate).collect(Collectors.toList());
        role.setRole(RoleConstant.ROLE_USER);
        predicate = userInfo -> userInfo.getRoles().contains(role);
        List<UserInfo> usersToAllocate = userInfoList.stream().filter(predicate).collect(Collectors.toList());

        Integer usersPerVolunteer = (usersToAllocate.size() / iyfVolunteers.size());

        IntStream.range(0, iyfVolunteers.size()).parallel().forEach(index -> {
            List<CallingSewa> callingSewas = new ArrayList<>();
            UserInfo volunteer = iyfVolunteers.get(index);

            List<UserInfo> userInfos = usersToAllocate.subList(index * usersPerVolunteer, (index * usersPerVolunteer) + 20);
            userInfos.forEach(userInfo -> {
                        CallingSewa callingSewa = new CallingSewa();
                        callingSewa.setCalled(false);
                        callingSewa.setResponseStatus("Not Called Yet");
                        callingSewa.setSeminar(seminar);
                        callingSewa.setSewaAllocationDate(new Date());
                        callingSewa.setUser(userInfo);
                        callingSewa.setVolunteer(volunteer);
                        callingSewas.add(callingSewa);
                    }
            );
            callingSewaRepo.saveAll(callingSewas);
            callingSewaRepo.flush();
            logger.info(" Calling Sewa Allocation Done For  -- "+volunteer.getUsername());
        });


    }
}
