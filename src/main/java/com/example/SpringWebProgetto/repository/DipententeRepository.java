package com.example.SpringWebProgetto.repository;

import com.example.SpringWebProgetto.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipententeRepository extends JpaRepository<Dipendente,Long> {

}
