package lk.ijse.paymentservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private String id;
    private LocalDate issueDate;
    private TicketStatus status;

    private String userId;
    private String vehicleId;
}
