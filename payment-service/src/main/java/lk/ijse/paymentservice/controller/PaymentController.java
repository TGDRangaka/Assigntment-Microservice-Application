package lk.ijse.paymentservice.controller;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.dto.TicketDTO;
import lk.ijse.paymentservice.dto.TicketStatus;
import lk.ijse.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    @GetMapping("/health")
    public String health(){
        return "Payment Service is up and running";
    }

//    create a new payment
//    update ticket status
    @PostMapping
    public ResponseEntity validatePayment(@RequestParam String userId, @RequestBody PaymentDTO paymentDTO){
        try{
//            Retrieve ticket
            ResponseEntity<TicketDTO> ticket = restTemplate.getForEntity("http://ticket-service/api/v1/ticket/" + paymentDTO.getTicketId(), TicketDTO.class);
            if(ticket.getBody().getStatus().equals(TicketStatus.CONFIRMED)){
                return ResponseEntity.internalServerError().body("Ticket is already confirmed and payed");
            }

            paymentDTO.setUserId(userId);
            paymentDTO.setTicketId(ticket.getBody().getId());
            PaymentDTO payment = paymentService.savePayment(paymentDTO);
            if(payment != null){
                ticket.getBody().setStatus(TicketStatus.CONFIRMED);
                restTemplate.put("http://ticket-service/api/v1/ticket/status/" + ticket.getBody().getId(), ticket.getBody());
                return ResponseEntity.status(201).body(payment);
            }

            return ResponseEntity.status(500).body("Something went wrong");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
