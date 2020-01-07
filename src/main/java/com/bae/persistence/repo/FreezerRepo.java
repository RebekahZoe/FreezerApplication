
package com.bae.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.persistence.domain.Freezers;

@Repository
public interface FreezerRepo extends JpaRepository<Freezers, Long> {

	Freezers findByFreezerName(String name);
}

