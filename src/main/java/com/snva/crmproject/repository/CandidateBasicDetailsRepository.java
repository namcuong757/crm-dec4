package com.snva.crmproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.snva.crmproject.entity.CandidateBasicDetails;

public interface CandidateBasicDetailsRepository extends JpaRepository<CandidateBasicDetails, String> {
}