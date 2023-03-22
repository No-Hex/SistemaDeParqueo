package com.NoHexRoger.Backend.Service;

import com.NoHexRoger.Backend.Dto.VehiculoRequest;
import com.NoHexRoger.Backend.Entity.TipoVehiculo;
import com.NoHexRoger.Backend.Entity.Vehiculo;
import com.NoHexRoger.Backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    public Vehiculo getVehiculoById(String vehiculoId) {
        return vehiculoRepository.findById(vehiculoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo create(VehiculoRequest vehiculoRequest) {
        TipoVehiculo tipoVehiculo = tipoVehiculoService.findById(vehiculoRequest.getTipoVehiculoId());

        Vehiculo newVehiculo = Vehiculo.builder()
                .placa(vehiculoRequest.getPlaca())
                .tipoVehiculo(tipoVehiculo)
                .build();

        return vehiculoRepository.save(newVehiculo);
    }
}
