package telran.cars.jpa.repo;


import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.jpa.entities.RentRecordJpa;

public interface RentRecordRepository extends JpaRepository<RentRecordJpa, Integer>{

	boolean existsByCarCarNumberAndReturnDateIsNull(String carNumber);

	Set<RentRecordJpa> findByCarCarNumber(String carNumber);

	Set<RentRecordJpa> findByDriverLicenseId(long licenseId);

	Set<RentRecordJpa> findByCarFlRemoved(boolean b);

	Set<RentRecordJpa> findByReturnDateBetween(LocalDate from, LocalDate to);

	Set<RentRecordJpa> findByCarCarNumberAndReturnDateNull(String carNumber);

}
