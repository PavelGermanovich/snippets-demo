package com.germanovich.codesnippets.repository;

import com.germanovich.codesnippets.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code, UUID> {
    @Query(value =
            "SELECT * FROM code where VIEWS_RESTRICTION_APPLIED = false AND TIME_RESTRICTION_APPLIED = false order by date desc limit 10",
            nativeQuery = true)
    List<Code> findLatestSnippets();
}
