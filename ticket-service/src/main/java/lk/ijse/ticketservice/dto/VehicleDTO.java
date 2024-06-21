package lk.ijse.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private String id;
    private String vehicleNo;
    private String owner;
    private String vehicleModel;
}
