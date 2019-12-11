package com.bae.persistence.repo;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.persistence.domain.Items;


@Repository
public interface ItemsRepo extends JpaRepository<Items, Id>{

}
