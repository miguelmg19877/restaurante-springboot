package me.miguel.restaurante.controller;

import me.miguel.restaurante.model.Mesa;
import me.miguel.restaurante.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @PostMapping
    public Mesa createMesa(@RequestBody Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @GetMapping
    public List<Mesa> getAllMesas() {
        return mesaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable String id) {
        Optional<Mesa> mesa = mesaRepository.findById(id);
        return mesa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> updateMesa(@PathVariable String id, @RequestBody Mesa mesaDetails) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);
        if (mesaOptional.isPresent()) {
            Mesa mesa = mesaOptional.get();
            mesa.setCapacidade(mesaDetails.getCapacidade());
            return ResponseEntity.ok(mesaRepository.save(mesa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable String id) {
        if (mesaRepository.existsById(id)) {
            mesaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
