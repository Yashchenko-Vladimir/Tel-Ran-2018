package telran.cars.jpa.repo;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.cars.jpa.entities.RentRecordJpa;

public interface RentRecordRepository extends JpaRepository<RentRecordJpa, Integer>{

	boolean existsByCarCarNumberAndReturnDateIsNull(String carNumber);

	Set<RentRecordJpa> findByCarCarNumber(String carNumber);

	Set<RentRecordJpa> findByDriverLicenseId(long licenseId);

	Set<RentRecordJpa> findByCarFlRemoved(boolean b);

	Set<RentRecordJpa> findByReturnDateBetween(LocalDate from, LocalDate to);

	Set<RentRecordJpa> findByCarCarNumberAndReturnDateNull(String carNumber);
	
//	@Query(value ="select max(count) form (select count(*) as count from records join cars "
//			+ "on records.car_id=cars.carnumber join models on cars.car_model_name=models.modelname "
//			+ "group by models.modelname)")
//	Long getMaxFromPopuparModelName();
//	
//	
//	@Query(value ="select * from (select count(*) as number, models.modelname from  "
//			+ "records join cars on records.car_id=cars.carnumber join models on "
//			+ "cars.car_model_name=models.modelname group by models.modelname) "
//			+ "having number = :max)")
//	List<String> selectMostpopularModelNames(@Param("max") long maxPopelar);
	
//	@Query(value = "select max(count) from (select count(*) as count from cars join owners "
//			+ "on ownerid=id where year(purchasedate)= :year and year(curdate())-birthyear between "
//			+ ":ageFrom and :ageTo group by modelname)", nativeQuery = true)
//	Long getMax(@Param("year")int year, @Param("ageFrom")int ageFrom, @Param("ageTo")int ageTo);
//	
//	@Query(value ="select modelname from PopularModels where year= :year and age between :ageFrom and :ageTo "
//			+ "group by modelname having count(*)=:max")
//	List<String> getModels(@Param("year")int year, @Param("ageFrom")int ageFrom, 
//			@Param("ageTo")int ageTo, @Param("max") long maxOccurences);
}
