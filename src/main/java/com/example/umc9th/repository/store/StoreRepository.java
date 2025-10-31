package com.example.umc9th.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.umc9th.domain.store.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

}
