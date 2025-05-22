package me.miguel.restaurante.controller;

import me.miguel.restaurante.model.Reservas;
import me.miguel.restaurante.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservasController {

    @Autowired
    private ReservasRepository reservasRepository;

    @PostMapping
    public Reservas createReserva(@RequestBody Reservas reserva) {
        // Basic validation could be added here, e.g., check if clienteid and mesaid exist
        return reservasRepository.save(reserva);
    }

    @GetMapping
    public List<Reservas> getAllReservas() {
        return reservasRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservas> getReservaById(@PathVariable String id) {
        Optional<Reservas> reserva = reservasRepository.findById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservas> updateReserva(@PathVariable String id, @RequestBody Reservas reservaDetails) {
        Optional<Reservas> reservaSoptional = reservasRepository.findById(id);
        if (reservaSoptional.isPresent()) {
            Reservas reserva = reservaSoptional.get();
            reserva.setClienteid(reservaDetails.getClienteid());
            reserva.setMesaid(reservaDetails.getMesaid());
            reserva.setCapacidade(reservaDetails.getCapacidade());
            reserva.setData(reservaDetails.getData());
            // Add validation for clienteid and mesaid if necessary
            return ResponseEntity.ok(reservasRepository.save(reserva));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable String id) {
        if (reservasRepository.existsById(id)) {
            reservasRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
