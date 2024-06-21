package lk.ijse.userservice.dto;

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
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String userId;
    private String vehicleId;
}
