package com.example.SpringWebProgetto.repository;

import com.example.SpringWebProgetto.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio,Long> {
}
