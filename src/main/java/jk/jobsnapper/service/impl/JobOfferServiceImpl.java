package jk.jobsnapper.service.impl;


import jk.jobsnapper.dto.JobOfferDto;
import jk.jobsnapper.entity.JobOffer;
import jk.jobsnapper.exception.ResourceNotFoundException;
import jk.jobsnapper.mapper.JobOfferMapper;
import jk.jobsnapper.repository.JobOfferRepository;
import jk.jobsnapper.service.JobOfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {
    private JobOfferRepository jobOfferRepository;
    @Override
    public JobOfferDto createJobOffer(JobOfferDto jobOfferDto) {
        JobOffer jobOffer = JobOfferMapper.maptoJobOffer(jobOfferDto);
        JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
        return JobOfferMapper.maptoJobOfferDto(savedJobOffer);
    }

    @Override
    public JobOfferDto getJobOfferById(Long job_offer_id) {
        JobOffer jobOffer = jobOfferRepository.findById(job_offer_id).orElseThrow(
                () -> new ResourceNotFoundException("Job Offer not found for this id :: " + job_offer_id));
        return JobOfferMapper.maptoJobOfferDto(jobOffer);
    }

    @Override
    public List<JobOfferDto> getJobOfferByUserId(Long idUser) {
        try {
            List<JobOffer> jobOffers = jobOfferRepository.findJobOfferByIdUser(idUser);
            return jobOffers.stream().map((jobOffer) -> JobOfferMapper.maptoJobOfferDto(jobOffer))
                    .collect(java.util.stream.Collectors.toList());
        }catch (Exception e) {
            throw new ResourceNotFoundException("Job Offer not found for this id :: " + idUser);
        }

    }

    @Override
    public List<JobOfferDto> getAllJobOffers() {
        List<JobOffer> jobOffers = jobOfferRepository.findAll();
        return jobOffers.stream().map((jobOffer) -> JobOfferMapper.maptoJobOfferDto(jobOffer))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public JobOfferDto updateJobOffer(Long job_offer_id, JobOfferDto UpdatedJobOffer) {
        JobOffer jobOffer = jobOfferRepository.findById(job_offer_id).orElseThrow(
                () -> new ResourceNotFoundException("Job Offer not found for this id :: " + job_offer_id));

        jobOffer.setTitle(UpdatedJobOffer.getTitle());
        jobOffer.setDescription(UpdatedJobOffer.getDescription());
        jobOffer.setLocation(UpdatedJobOffer.getLocation());
        jobOffer.setStartDate(UpdatedJobOffer.getStartDate());
        jobOffer.setEndDate(UpdatedJobOffer.getEndDate());
        jobOffer.setPeopleRequired(UpdatedJobOffer.getPeopleRequired());
        jobOffer.setSalary(UpdatedJobOffer.getSalary());
        jobOffer.setPhoneNumber(UpdatedJobOffer.getPhoneNumber());
        jobOffer.setCategory(UpdatedJobOffer.getCategory());
        jobOffer.setStatus(UpdatedJobOffer.getStatus());
        jobOffer.setPostedBy(UpdatedJobOffer.getPostedBy());
        jobOffer.setPostedDate(UpdatedJobOffer.getPostedDate());
        jobOffer.setApplicantsNumber(UpdatedJobOffer.getApplicantsNumber());
        jobOffer.setAcceptedApplicants(UpdatedJobOffer.getAcceptedApplicants());

        JobOffer updatedJobOfferObj = jobOfferRepository.save(jobOffer);
        return JobOfferMapper.maptoJobOfferDto(updatedJobOfferObj);
    }

    @Override
    public void deleteJobOffer(Long job_offer_id) {
        jobOfferRepository.deleteById(job_offer_id);
    }
}
