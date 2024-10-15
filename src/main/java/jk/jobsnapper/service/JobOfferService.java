package jk.jobsnapper.service;

import jk.jobsnapper.dto.JobOfferDto;

import java.util.List;

public interface JobOfferService {
    JobOfferDto createJobOffer(JobOfferDto jobOfferDto);

    JobOfferDto getJobOfferById(Long job_offer_id);
    List<JobOfferDto> getJobOfferByUserId(Long idUser);

    List<JobOfferDto> getAllJobOffers();

    JobOfferDto updateJobOffer(Long job_offer_id, JobOfferDto UpdatedJobOffer);

    void deleteJobOffer(Long job_offer_id);
}
