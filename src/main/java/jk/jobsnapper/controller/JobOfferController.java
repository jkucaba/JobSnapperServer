package jk.jobsnapper.controller;

import jk.jobsnapper.dto.JobOfferDto;
import jk.jobsnapper.service.JobOfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/employer/joboffers")
public class JobOfferController {
    private JobOfferService jobOfferService;

    //Build Add JobOffer REST API
    @PostMapping
    public ResponseEntity<JobOfferDto> createJobOffer(@RequestBody JobOfferDto jobOfferDto){
        JobOfferDto savedJobOffer = jobOfferService.createJobOffer(jobOfferDto);
        return new ResponseEntity<>(savedJobOffer, HttpStatus.CREATED);
    }

    //Build Get JobOffer by id REST API
    @GetMapping("{id}")
    public ResponseEntity<JobOfferDto> getJobOfferById(@PathVariable("id") Long jobOfferId){
        JobOfferDto jobOfferDto = jobOfferService.getJobOfferById(jobOfferId);
        return new ResponseEntity<>(jobOfferDto, HttpStatus.OK);
    }

    //Build Get all JobOffers REST API
    @GetMapping
    public ResponseEntity<List<JobOfferDto>> getAllJobOffers(){
        List<JobOfferDto> jobOffers = jobOfferService.getAllJobOffers();
        return ResponseEntity.ok(jobOffers);
    }

    //Build Update JobOffer REST API
    @PutMapping("{id}")
    public ResponseEntity<JobOfferDto> updateJobOffers(@PathVariable("id") Long job_offer_id, @RequestBody JobOfferDto updatedJobOffer){
        JobOfferDto jobOfferDto = jobOfferService.updateJobOffer(job_offer_id, updatedJobOffer);
        return new ResponseEntity<>(jobOfferDto, HttpStatus.OK);
    }

    //Build Delete JobOffer REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobOffer(@PathVariable("id") Long jobOfferId){
        jobOfferService.deleteJobOffer(jobOfferId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
