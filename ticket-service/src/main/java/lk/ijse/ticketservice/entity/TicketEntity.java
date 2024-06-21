package lk.ijse.ticketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.ticketservice.dto.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TicketEntity {
    @Id
    private String id;
    private LocalDate issueDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String userId;
    private String vehicleId;
}
