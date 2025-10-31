package com.naho.umc9th.domain.store.repository;

import com.naho.umc9th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
