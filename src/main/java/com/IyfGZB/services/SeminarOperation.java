package com.IyfGZB.services;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.NotificationServices.SeminarNotificationService;
import com.IyfGZB.constants.SeminarConstant;
import com.IyfGZB.controller.AccountController;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.dto.SeminarDto;
import com.IyfGZB.dto.SeminarRecordDTO;
import com.IyfGZB.repositories.SeminarRecordRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.securityservices.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Service
public class SeminarOperation {

    @Autowired
    SeminarNotificationService notificationService;

    @Autowired
    SeminarRepo seminarRepo;

    @Autowired
    SeminarRecordRepo seminarRecordRepo;

    public static final Logger logger = LoggerFactory.getLogger(SeminarOperation.class);

    public CommonResponseDTO createSeminar(Seminar seminar){
        try{
            UserInfo userInfo=CurrentUser.getCurrentUser();

            seminar.setModifiedDate(new Date());
            seminar.setModifiedBy(userInfo.getEmail());
            seminar.setCreatedBy(userInfo.getEmail());
            seminar.setCreatedDate(new Date());
            seminarRepo.save(seminar);

            notificationService.sendEmail(seminar);

            return new CommonResponseDTO("success","Seminar Created Successfully");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","OOPS..! Seminar Could Not Be Created");


        }
    }
    public HashMap<String,Object> getUpcomingSeminars(Integer vedicLevel, Integer pageNumber, Integer itemPerPage){
      try{
          PageRequest pageRequest=new PageRequest(pageNumber,itemPerPage,Sort.Direction.DESC,"date");
              List<Seminar> seminars=seminarRepo.findAllByDateAfter(new Date(),pageRequest);
          Integer totalPage=seminarRepo.findAllByDateAfter(new Date()).size();
          UserInfo userInfo=CurrentUser.getCurrentUser();
          List<SeminarDto> seminarDtoList=new ArrayList<>();
          seminars.forEach(seminar -> {
              SeminarDto seminarDto=new SeminarDto();
//              seminarDto.setBookingStatus(false);
              seminarDto.setId(seminar.getId());
//                            seminarDto.setCategory(seminar.getCategory());
//                            seminarDto.setTitle(seminar.getTitle());
//                            seminarDto.setSpeakerName(seminar.getSpeakerName());
//                            seminarDto.setThumbNailUrl(seminar.getThumbNailUrl());
//                            seminarDtoList.add(seminarDto);
              seminarDto.setCategory(seminar.getCategory());
              seminarDto.setTitle(seminar.getTitle());
              seminarDto.setSpeakerName(seminar.getSpeakerName());
              seminarDto.setThumbNailUrl(seminar.getThumbNailUrl());

              if(!seminar.getCategory().equals(SeminarConstant.OTP)){
                if(Integer.parseInt(seminar.getCategory()) == (userInfo.getVedicLevel())){
                    SeminarRecord seminarRecord =  seminarRecordRepo.findSeminarRecordBySeminarAndUser(seminar,userInfo);
                    if(seminarRecord !=null){
                        if(seminarRecord.getStatus().equals(SeminarConstant.STATUS_BOOKED))
                        {
                            seminarDto.setSeminarRecordId(seminarRecord.getId());
                            seminarDto.setBookingStatus(true);

                        }
                        else {
                            seminarDto.setSeminarRecordId(seminarRecord.getId());
                            seminarDto.setBookingStatus(false);
                        }
                    }
                    seminarDtoList.add(seminarDto);
                }
              }else{
                  SeminarRecord seminarRecord =  seminarRecordRepo.findSeminarRecordBySeminarAndUser(seminar,userInfo);
                  if(seminarRecord !=null){
                      if(seminarRecord.getStatus().equals(SeminarConstant.STATUS_BOOKED))
                      {
                          seminarDto.setSeminarRecordId(seminarRecord.getId());
                          seminarDto.setBookingStatus(true);
                      }
                      else {
                          seminarDto.setSeminarRecordId(seminarRecord.getId());
                          seminarDto.setBookingStatus(false);
                      }
                  }
                  seminarDtoList.add(seminarDto);
              }

          });
//          seminars.forEach(seminar -> {
//              String seminarStartTime=seminar.getStartTime();
//              if(seminarStartTime != null ){
//                  LocalTime now = LocalTime.now();
//                  LocalTime startTime = LocalTime.parse( seminarStartTime );
//                  if(!startTime.isAfter( now )){
//                      seminars.remove(seminar);
//                  }
//              }
//          });

//          seminars.forEach();

          HashMap<String,Object> hashMap=new HashMap<>();
          hashMap.put("upcomingSeminar",seminarDtoList);
          hashMap.put("totalPages",(itemPerPage + totalPage -1)/itemPerPage);

          return hashMap;
      }catch (Exception e){
         logger.error(e.getMessage());
         return null;
      }


    }

    public CommonResponseDTO updateSeminar(Seminar seminar){

        try{
            Seminar seminar1 = seminarRepo.findSeminarById(seminar.getId());

            seminar1.setCategory(seminar.getCategory());
            seminar1.setDate(seminar.getDate());
            seminar1.setEndTime(seminar.getEndTime());
            seminar1.setStartTime(seminar.getStartTime());
            seminar1.setModifiedBy(CurrentUser.getCurrentUser().getUsername());
            seminar1.setSeminarDescription(seminar.getSeminarDescription());
            seminar1.setTitle(seminar.getTitle());
            seminar1.setThumbNailUrl(seminar.getThumbNailUrl());
            seminar1.setSpeakerName(seminar.getSpeakerName());
            seminar1.setVenue(seminar.getVenue());
            seminar1.setTotalNumberOfSeats(seminar.getTotalNumberOfSeats());
            notificationService.sendUpdationEmail(seminar);

            return new CommonResponseDTO("success","Seminar updated successfully");

        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","Some error occured while updating seminar");
        }

    }


    public Page<Seminar> getAllSeminars(Integer itemPerPage, Integer pageNumber){

        try{
            PageRequest pageRequest=new PageRequest(pageNumber,itemPerPage,Sort.Direction.DESC,"date");

            return seminarRepo.findAll(pageRequest);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public SeminarRecordDTO getSeminar(Long seminarId){
        try{
          Seminar seminar =  seminarRepo.findSeminarById(seminarId);
          List<SeminarRecord> seminarRecord = seminarRecordRepo.findAllBySeminar(seminar);
          List<SeminarRecord> seminarRecordList = new ArrayList<>();
          Long count = 0l;
          Long totalSeats=seminar.getTotalNumberOfSeats();
          Iterator<SeminarRecord> seminarRecords = seminarRecord.iterator();
          while (seminarRecords.hasNext()){
              SeminarRecord seminarRecord1 = seminarRecords.next();
              if(seminarRecord1.getStatus().equals(SeminarConstant.STATUS_BOOKED)){
                  count++;
              }
              seminarRecordList.add(seminarRecord1);
          }
            SeminarRecordDTO seminarRecordDTO = new SeminarRecordDTO();
            seminar.setTotalNumberOfAvailableSeats(totalSeats - count);
            seminarRecordDTO.setSeminar(seminar);
            seminarRecordDTO.setSeminarRecord(seminarRecordList);

            return seminarRecordDTO;


        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

//    public CommonResponseDTO deleteSeminar(Long seminarId){
//
//
//
//    }

//    public Integer getTotalNumberOfPages(Integer itemPerPage){
//        try{
//           SeminarOperation seminarOperation=new SeminarOperation();
//           seminarOperation.getUpcomingSeminars()
//
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            return 0;
//        }
//    }



}
