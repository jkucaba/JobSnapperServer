package jk.jobsnapper.repository;

import jk.jobsnapper.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    JobOffer findJobOfferByOfferId(Long job_offer_id);
    JobOffer findJobOfferByIdUser(Long idUser);


}
