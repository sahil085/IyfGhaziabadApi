package com.IyfGZB.services;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.constants.AttendanceConstant;
import com.IyfGZB.constants.ClassLevel;
import com.IyfGZB.domain.*;
import com.IyfGZB.dto.SeminarAttendanceDTO;
import com.IyfGZB.repositories.SeminarAttendanceRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.securityservices.CurrentUser;
import com.IyfGZB.userdto.UserDto;
import com.IyfGZB.util.MasterAttendanceExcelMaker;
import org.joda.time.DateTimeComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sahil on 3/8/18.
 */

@Service
public class SeminarAttendanceOperation {

    public static final Logger logger = LoggerFactory.getLogger(SeminarAttendanceOperation.class);

    @Autowired
    SeminarAttendanceRepo seminarAttendanceRepo;
    @Autowired
    SeminarRepo seminarRepo;
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    MasterAttendanceExcelMaker masterAttendanceExcelMaker;

    public CommonResponseDTO markAttendance(Long seminarId, Long userId, String status) {
        try {
            Seminar seminar = seminarRepo.findSeminarById(seminarId);


            SeminarAttendance attendance = seminarAttendanceRepo.findSeminarAttendanceBySeminarAndUserId(seminar, userId);
            if (attendance != null) {
                UserInfo userInfo = userInfoRepository.findById(userId).get();
                attendance.setSeminar(seminar);
                attendance.setUser(userInfo);
                String userEmail = CurrentUser.getCurrentUser().getEmail();
                attendance.setAttendanceMarkedBy(userEmail);
                attendance.setCreatedDate(new Date());
                if (status.equals(AttendanceConstant.MARK_PRESENT)) {
                    attendance.setAttendanceStatus(AttendanceConstant.MARK_PRESENT);
                    seminarAttendanceRepo.save(attendance);
                    return new CommonResponseDTO("success", "User marked as present");


                } else {
                    attendance.setAttendanceStatus(AttendanceConstant.MARK_ABSENT);
                    seminarAttendanceRepo.save(attendance);
                    return new CommonResponseDTO("success", "User marked as absent");

                }
            } else {
                SeminarAttendance attendance1 = new SeminarAttendance();
                UserInfo userInfo = userInfoRepository.findById(userId).get();

                attendance1.setSeminar(seminar);
                attendance1.setUser(userInfo);
                String userEmail = CurrentUser.getCurrentUser().getEmail();
                attendance1.setAttendanceMarkedBy(userEmail);
                attendance1.setCreatedDate(new Date());
                if (status.equals(AttendanceConstant.MARK_PRESENT)) {
                    attendance1.setAttendanceStatus(AttendanceConstant.MARK_PRESENT);
                    seminarAttendanceRepo.save(attendance1);
                    return new CommonResponseDTO("success", "User marked as present");


                } else {
                    attendance1.setAttendanceStatus(AttendanceConstant.MARK_ABSENT);
                    seminarAttendanceRepo.save(attendance1);
                    return new CommonResponseDTO("success", "User marked as absent");

                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger", "Try again");
        }


    }

    public  Map<String, Object> getAttendeeForSeminar(Long seminarId, Integer itemPerPage, Integer pageNumber){

        Seminar seminar = seminarRepo.findSeminarById(seminarId);
        PageRequest pageRequest=new PageRequest(pageNumber,itemPerPage, Sort.Direction.DESC,"createdDate");
        Map<String, Object> data = new HashMap<>();
        Page<SeminarAttendance> seminarAttendanceList = seminarAttendanceRepo.findAllBySeminar(seminar,pageRequest);
        List<UserDto> userDtoList = new ArrayList<>();
        seminarAttendanceList.getContent().forEach(seminarAttendance -> {
            UserDto userDto = new UserDto();
            userDto.setClassLevel(seminarAttendance.getUser().getClassLevel());
            userDto.setUserName(seminarAttendance.getUser().getUsername());
            userDto.setMobileNumber(seminarAttendance.getUser().getMobileNumber());
            userDto.setAttendanceStatus(seminarAttendance.getAttendanceStatus());
            userDto.setAttendanceMarkedBy(seminarAttendance.getAttendanceMarkedBy());
            userDtoList.add(userDto);
        });
        data.put("attendeeList",userDtoList);
        data.put("totalElements",seminarAttendanceList.getTotalElements());
        return data;
    }

    public String generateMasterSheet(String category){
        try{

            Map<String,List<String>> attendanceData= new HashMap<>();
            List<SeminarAttendanceDTO> seminarAttendanceDTOS = new ArrayList<>();
            // Fetch Seminar of last two month
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/YYYY");
            DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
            LocalDate currentDate = LocalDate.now(); // 2015-11-24
            LocalDate lastTwoMonthDate = currentDate.minusMonths(2);
            Date last2MonthDate = Date.from(lastTwoMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<Seminar> seminarList = seminarRepo.findAllByDateBetweenAndCategory(last2MonthDate,new Date(), category);
            Integer index[]={1};
            String sheetTitle = category +" ( "+simpleDateFormat.format(new Date())+" - "+simpleDateFormat.format(last2MonthDate)+" )";
            Boolean makeSheet[] = {false};
            if(!seminarList.isEmpty()){
                seminarList.forEach(seminar -> {
                    List<SeminarAttendance> seminarAttendanceList = seminarAttendanceRepo.findAllBySeminar(seminar);
                    if(!seminarAttendanceList.isEmpty()){
                        makeSheet[0] = true;
                        String date = simpleDateFormat.format(seminar.getCreatedDate());
                        SeminarAttendanceDTO seminarAttendanceDTO = new SeminarAttendanceDTO();
                        seminarAttendanceDTO.setStartIndex(index[0]++);
                        // this date is original this is taken to set date in string format
                        seminarAttendanceDTO.setDummyDate(date);
                        seminarAttendanceDTO.setSpeakerName(seminar.getSpeakerName());
                        seminarAttendanceDTO.setSeminarTitle(seminar.getTitle());
                        seminarAttendanceDTOS.add(seminarAttendanceDTO);

                        seminarAttendanceList.forEach(seminarRecord -> {
                            String username = seminarRecord.getUser().getUsername();
                            List<String > status = new ArrayList<>();
                            if(!attendanceData.containsKey(username)){
                                status.add(seminarRecord.getAttendanceStatus());
                                attendanceData.put(username, status);
                            }else {
                                List<String> strings =    attendanceData.get(username);
                                strings.add(seminarRecord.getAttendanceStatus());
                                attendanceData.put(username,strings);

                            }
                        });
                    }


                });
                if(makeSheet[0]){
                    masterAttendanceExcelMaker.sendSeminarAttendanceReport(sheetTitle,attendanceData,seminarAttendanceDTOS);
                    return "Master Sheet Generated For Category "+category;
                }else {
                    return "No Record Found To Generate Report";
                }

            }else {
                return "No Seminar Created From Last Two Months";
            }



        }catch (Exception e){
            logger.error(e.getMessage());
            return "Error in generating master sheet";
        }

    }

public String populateSeminarAttendance(){
        try{
            List<String> classLevel = new ArrayList<>();
            classLevel.add(ClassLevel.Discover_Yourself);
            classLevel.add(ClassLevel.One_Time_Program);
            classLevel.add(ClassLevel.Srila_Prabhupada_Sabha);
            classLevel.add(ClassLevel.VEDIC_LEVEL_TWO);
            classLevel.forEach(category -> {

                List<UserInfo> userInfoList = userInfoRepository.findAllByClassLevel(category);
                List<Seminar> seminarList = seminarRepo.findAllByCategory(category);
                seminarList.forEach(seminar -> {
                    List<SeminarAttendance> attendanceList = seminarAttendanceRepo.findAllBySeminar(seminar);
                    if(!attendanceList.isEmpty()){
                        List<UserInfo> userInfos = userInfoList.stream().filter(userInfo -> attendanceList.stream().allMatch(
                                seminarAttendance ->{
                                    return (!seminarAttendance.getUser().getId().equals(userInfo.getId())) && userInfo.getClassLevel().equals(seminar.getCategory());
                                }
                        )).collect(Collectors.toList());
                        List<SeminarAttendance> seminarAttendances = new ArrayList<>();
                        userInfos.forEach(userInfo -> {
                            SeminarAttendance seminarAttendance = new SeminarAttendance();
                            seminarAttendance.setSeminar(seminar);
                            seminarAttendance.setUser(userInfo);
                            seminarAttendance.setCreatedDate(new Date());
                            seminarAttendance.setAttendanceUpdatedBy("System");
                            seminarAttendance.setAttendanceStatus(AttendanceConstant.MARK_ABSENT);
                            seminarAttendances.add(seminarAttendance);
                        });
                        seminarAttendanceRepo.saveAll(seminarAttendances);

                    }else {
                        List<SeminarAttendance> seminarAttendances = new ArrayList<>();
                        List<UserInfo> userInfos = userInfoList.stream().filter(userInfo -> userInfo.getClassLevel().equals(seminar.getCategory())).collect(Collectors.toList());
                        userInfos.forEach(userInfo -> {
                            SeminarAttendance seminarAttendance = new SeminarAttendance();
                            seminarAttendance.setSeminar(seminar);
                            seminarAttendance.setUser(userInfo);
                            seminarAttendance.setCreatedDate(new Date());
                            seminarAttendance.setAttendanceUpdatedBy("System");
                            seminarAttendance.setAttendanceStatus(AttendanceConstant.MARK_ABSENT);
                            seminarAttendances.add(seminarAttendance);
                        });
                        seminarAttendanceRepo.saveAll(seminarAttendances);
                    }

                    System.out.println(" Data Populated For Seminar  "+seminar.getTitle());

                });

            });
            return "Data Populated ....!! hare Krishna";

        }catch (Exception e){
            logger.error(e.getMessage());
            return "Error in populating";
        }
}

}
