package lk.ijse.ticketservice.controller;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.dto.TicketStatus;
import lk.ijse.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/health")
    public String health() {
        return "Ticket Good!";
    }

//    create ticket
    @GetMapping
    public ResponseEntity createTicket(@RequestParam String userId, @RequestParam String vehicleId){
        try{
            TicketDTO ticket = ticketService.createTicket(userId, vehicleId);
            return ResponseEntity.status(201).body(ticket);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong creating ticket");
        }
    }

//    update status
    @PutMapping("/status/{ticketId}")
    public ResponseEntity updateStatus(@PathVariable String ticketId, @RequestBody TicketDTO ticketDTO){
        try {
            String success = ticketService.updateStatus(ticketId, ticketDTO);
            System.out.println(ticketDTO);
            System.out.println(success);
            return ResponseEntity.status(200).body(success);
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

//    retrieve ticket
    @GetMapping("/{ticketId}")
    public ResponseEntity retrieveTicket(@PathVariable String ticketId){
        try{
            TicketDTO ticket = ticketService.getTicket(ticketId);
            return ResponseEntity.status(200).body(ticket);
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
