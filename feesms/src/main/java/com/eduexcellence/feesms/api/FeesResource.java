package com.eduexcellence.feesms.api;

import com.eduexcellence.feesms.model.Fees;
import com.eduexcellence.feesms.repo.FeesRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FeesResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeesResource.class);
    @Autowired
    private FeesRepo feesRepo;

    @GetMapping("/getFee/{id}")
    public ResponseEntity<Fees> getFee(@PathVariable Integer id) {
        LOGGER.info("Get Fee by Student ID");
        Optional<Fees> feeFound =feesRepo.findById(id);
        if (feeFound.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feeFound.get());
    }
}
