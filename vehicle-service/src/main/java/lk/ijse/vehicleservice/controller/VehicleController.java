package lk.ijse.vehicleservice.controller;

import lk.ijse.vehicleservice.dto.TicketDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    private final RestTemplate restTemplate;

    @GetMapping("/health")
    public String health() {
        return "Vehicle Service is up and running";
    }

//    register vehicle
    @PostMapping
    public ResponseEntity registerVehicle(@RequestBody VehicleDTO dto, @RequestParam String userId) {
        try {
            VehicleDTO register = vehicleService.register(dto);
            // create ticket

            String parameters = "userId=" + userId + "&" + "vehicleId=" + register.getId();
            ResponseEntity<TicketDTO> ticket = restTemplate.getForEntity(
                    "http://ticket-service/api/v1/ticket?" + parameters,
                    TicketDTO.class
            );
            return ResponseEntity.ok(ticket.getBody());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

//    update vehicle
    @PutMapping("/{vehicleId}")
    public ResponseEntity updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable String vehicleId) {
        try {
            String success = vehicleService.updateVehicle(vehicleDTO, vehicleId);
            return ResponseEntity.ok(success);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

//    retrieve vehicle
    @GetMapping("/{vehicleId}")
    public ResponseEntity retrieveVehicle(@PathVariable String vehicleId) {
        try {
            VehicleDTO vehicle = vehicleService.getVehicle(vehicleId);
            return ResponseEntity.ok(vehicle);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
