package lk.ijse.vehicleservice.repo;

import lk.ijse.vehicleservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<VehicleEntity, String> {
}
