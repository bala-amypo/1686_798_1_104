
package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {
    
    @Query("SELECT COUNT(i) FROM IssuedDeviceRecord i WHERE i.employeeId = :employeeId AND i.status = 'ISSUED'")
    Long countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);
    
    @Query("SELECT i FROM IssuedDeviceRecord i WHERE i.employeeId = :employeeId AND i.deviceItemId = :deviceItemId AND i.status = 'ISSUED'")
    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(@Param("employeeId") Long employeeId, @Param("deviceItemId") Long deviceItemId);
}