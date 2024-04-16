package net.javaguides.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import net.javaguides.springboot.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    @Query(value = "SELECT * FROM vehicles s WHERE s.model LIKE %:keyword% OR s.brand LIKE %:keyword%", nativeQuery = true)
    Page<Vehicle> findByKeywordWithPageable(@Param("pageable") Pageable pageable, @Param("keyword") String keyword);
}
