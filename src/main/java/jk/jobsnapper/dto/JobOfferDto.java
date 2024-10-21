package jk.jobsnapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDto {
    private Long offerId;
    private Long idUser;
    private String title;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private int peopleRequired;
    private String salary;
    private int phoneNumber;
    private String category;
    private String status;
    private String postedBy;
    private String postedDate;
    private int applicantsNumber;
    private int acceptedApplicants;
}