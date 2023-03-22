package com.NoHexRoger.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "vehiculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehiculo {
    @Id
    private String placa;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id")
    private TipoVehiculo tipoVehiculo;
}
