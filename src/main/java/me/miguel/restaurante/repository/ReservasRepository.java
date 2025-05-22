package me.miguel.restaurante.repository;

import me.miguel.restaurante.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, String> {
}
