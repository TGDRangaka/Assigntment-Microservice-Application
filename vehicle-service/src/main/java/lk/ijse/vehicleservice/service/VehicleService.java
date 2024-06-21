package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.VehicleEntity;
import lk.ijse.vehicleservice.repo.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepo vehicleRepo;

    public VehicleDTO register(VehicleDTO vehicleDTO){
        Optional<VehicleEntity> byVehicleNo = vehicleRepo.findByVehicleNo(vehicleDTO.getVehicleNo());
        if(byVehicleNo.isPresent()){
            return new ModelMapper().map(byVehicleNo, VehicleDTO.class);
        }else{
            vehicleDTO.setId(UUID.randomUUID().toString());
            VehicleEntity save = vehicleRepo.save(new ModelMapper().map(vehicleDTO, VehicleEntity.class));
            return new ModelMapper().map(save, VehicleDTO.class);
        }
    }

    public String updateVehicle(VehicleDTO vehicleDTO,String vehicleId){
        Optional<VehicleEntity> byVehicleId = vehicleRepo.findById(vehicleId);
        if(byVehicleId.isPresent()){
            VehicleEntity vehicleEntity = byVehicleId.get();
            vehicleEntity.setVehicleNo(vehicleDTO.getVehicleNo());
            vehicleEntity.setVehicleModel(vehicleDTO.getVehicleModel());
            vehicleEntity.setOwner(vehicleDTO.getOwner());

            vehicleRepo.save(vehicleEntity);
            return "Vehicle updated successfully";
        }
        throw new RuntimeException("No vehicle found for vehicle id " + vehicleId);
    }

    public VehicleDTO getVehicle(String vehicleId){
        Optional<VehicleEntity> byVehicleId = vehicleRepo.findById(vehicleId);
        if(byVehicleId.isPresent()){
            return new ModelMapper().map(byVehicleId.get(), VehicleDTO.class);
        }
        throw new RuntimeException("No vehicle found for vehicle id " + vehicleId);
    }
}
