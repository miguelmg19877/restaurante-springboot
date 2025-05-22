package me.miguel.restaurante.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservas {

    @Id
    private String id;
    private String clienteid;
    private String mesaid;
    private Integer capacidade;
    private String data;
}
