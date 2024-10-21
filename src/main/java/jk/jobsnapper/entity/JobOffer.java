package jk.jobsnapper.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "joboffer")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    @Column(name = "idUser", nullable = false)
    private Long idUser;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String location;
    @Column(name = "startDate")
    private String startDate;
    @Column(name = "endDate")
    private String endDate;
    @Column(name = "peopleRequired")
    private int peopleRequired;
    @Column(name = "salary")
    private String salary;
    @Column(name = "phoneNumber")
    private int phoneNumber;
    @Column(name = "category")
    private String category;
    @Column(name = "status")
    private String status;
    @Column(name = "postedBy")
    private String postedBy;
    @Column(name = "postedDate")
    private String postedDate;
    @Column(name = "applicantsNumber")
    private int applicantsNumber;
    @Column(name = "acceptedApplicants")
    private int acceptedApplicants;
}
