package jk.jobsnapper.repository;

import jk.jobsnapper.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    JobOffer findJobOfferByOfferId(Long job_offer_id);
    List<JobOffer> findJobOfferByIdUser(Long idUser);


}
