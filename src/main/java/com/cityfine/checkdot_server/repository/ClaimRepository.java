package com.cityfine.checkdot_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfine.checkdot_server.entity.Claim;

import java.util.List;


@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findAllByOrderByRatingDesc();

    List<Claim> findAllByUserUserId(Long userId);
}
