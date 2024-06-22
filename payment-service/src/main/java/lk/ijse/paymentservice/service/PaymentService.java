package lk.ijse.paymentservice.service;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.entity.PaymentEntity;
import lk.ijse.paymentservice.repo.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepo paymentRepo;

    public PaymentDTO savePayment(PaymentDTO paymentDTO){
        paymentDTO.setId(UUID.randomUUID().toString());
        paymentDTO.setPayedDate(LocalDate.now());
        PaymentEntity save = paymentRepo.save(new ModelMapper().map(paymentDTO, PaymentEntity.class));
        return new ModelMapper().map(save, PaymentDTO.class);
    }
}
