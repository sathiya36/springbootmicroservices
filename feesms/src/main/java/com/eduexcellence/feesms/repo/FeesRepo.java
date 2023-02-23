package com.eduexcellence.feesms.repo;

import com.eduexcellence.feesms.model.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeesRepo extends JpaRepository<Fees,Integer> {
}
