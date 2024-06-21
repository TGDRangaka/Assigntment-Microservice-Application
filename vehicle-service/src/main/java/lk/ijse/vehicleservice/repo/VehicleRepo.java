package lk.ijse.vehicleservice.repo;

import lk.ijse.vehicleservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepo extends JpaRepository<VehicleEntity, String> {
    Optional<VehicleEntity> findByVehicleNo(String vehicleNo);
}
