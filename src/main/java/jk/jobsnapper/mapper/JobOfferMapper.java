package jk.jobsnapper.mapper;

import jk.jobsnapper.dto.JobOfferDto;
import jk.jobsnapper.entity.JobOffer;

public class JobOfferMapper {
    public static JobOfferDto maptoJobOfferDto(JobOffer jobOffer) {
        return new JobOfferDto(
                jobOffer.getOfferId(),
                jobOffer.getIdUser(),
                jobOffer.getTitle(),
                jobOffer.getDescription(),
                jobOffer.getLocation(),
                jobOffer.getStartDate(),
                jobOffer.getEndDate(),
                jobOffer.getPeopleRequired(),
                jobOffer.getSalary(),
                jobOffer.getPhoneNumber(),
                jobOffer.getCategory(),
                jobOffer.getStatus(),
                jobOffer.getPostedBy(),
                jobOffer.getPostedDate(),
                jobOffer.getApplicantsNumber(),
                jobOffer.getAcceptedApplicants()
        );
    }

    public static JobOffer maptoJobOffer(JobOfferDto JobOfferDto) {
        return new JobOffer(
                JobOfferDto.getOfferId(),
                JobOfferDto.getIdUser(),
                JobOfferDto.getTitle(),
                JobOfferDto.getDescription(),
                JobOfferDto.getLocation(),
                JobOfferDto.getStartDate(),
                JobOfferDto.getEndDate(),
                JobOfferDto.getPeopleRequired(),
                JobOfferDto.getSalary(),
                JobOfferDto.getPhoneNumber(),
                JobOfferDto.getCategory(),
                JobOfferDto.getStatus(),
                JobOfferDto.getPostedBy(),
                JobOfferDto.getPostedDate(),
                JobOfferDto.getApplicantsNumber(),
                JobOfferDto.getAcceptedApplicants()
        );
    }
}
