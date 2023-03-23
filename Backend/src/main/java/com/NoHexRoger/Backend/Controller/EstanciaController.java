package com.NoHexRoger.Backend.Controller;

import com.NoHexRoger.Backend.Dto.EstanciaRequest;
import com.NoHexRoger.Backend.Dto.EstanciaResponse;
import com.NoHexRoger.Backend.Entity.Estancia;

import com.NoHexRoger.Backend.Entity.Vehiculo;
import com.NoHexRoger.Backend.Service.EstanciaService;
import com.NoHexRoger.Backend.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estancias")
public class EstanciaController {
    @Autowired
    private EstanciaService estanciaService;

    @Autowired
    private VehiculoService vehiculoService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Estancia> getAll(){
        return estanciaService.getEstancias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estancia> getEstanciaById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(estanciaService.getEstanciaById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Estancia> createEstancia(@RequestBody EstanciaRequest estanciaRequest){
        Vehiculo vehiculo = vehiculoService.getVehiculoById(estanciaRequest.getVehiculoId());

        Estancia newEstancia = Estancia.builder()
                .vehiculo(vehiculo)
                .build();

        estanciaService.createEstancia(newEstancia);

        EstanciaResponse estanciaResponse = EstanciaResponse.builder()
                .id(newEstancia.getId())
                .vehiculoId(newEstancia.getVehiculo().getPlaca())
                .fechaEntrada(newEstancia.getFechaEntrada())
                .fechaSalida(newEstancia.getFechaSalida())
                .build();

        return new ResponseEntity(estanciaResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estancia> updateEstancia(@PathVariable("id") Integer id, @RequestBody Estancia  estanciaRequest){
        return estanciaService.updateEstancia(id, estanciaRequest);
    }
}
