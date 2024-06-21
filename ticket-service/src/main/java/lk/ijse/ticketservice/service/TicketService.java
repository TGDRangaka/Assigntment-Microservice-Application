package lk.ijse.ticketservice.service;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.dto.TicketStatus;
import lk.ijse.ticketservice.entity.TicketEntity;
import lk.ijse.ticketservice.repo.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepo ticketRepo;

    public TicketDTO createTicket(String userId, String vehicleId) {
        TicketEntity ticket = new TicketEntity(
                UUID.randomUUID().toString(),
                LocalDate.now(),
                TicketStatus.PENDING,
                userId,
                vehicleId
        );
        TicketEntity save = ticketRepo.save(ticket);
        return new ModelMapper().map(save, TicketDTO.class);
    }

    public String updateStatus(String ticketId){
        Optional<TicketEntity> byId = ticketRepo.findById(ticketId);
        if(byId.isPresent()){
            byId.get().setStatus(TicketStatus.CONFIRMED);
            ticketRepo.save(byId.get());
            return "Ticket status updated successfully";
        }
        throw new RuntimeException("Couldn't find ticket with id " + ticketId);
    }

    public TicketDTO getTicket(String ticketId){
        Optional<TicketEntity> byId = ticketRepo.findById(ticketId);
        if(byId.isPresent()){
            return new ModelMapper().map(byId.get(), TicketDTO.class);
        }
        throw new RuntimeException("Couldn't find ticket with id " + ticketId);
    }
}
