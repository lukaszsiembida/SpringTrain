package pl.sda.springtrainingjavalub22.external.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.springtrainingjavalub22.domain.car.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCarRepository extends JpaRepository<CarEntity, Long>, CustomDatabaseCarRepository {

    //query method

    // select * from car where vin =?1
    Optional<CarEntity> findByVin(String vin);

    // select * from car where manufacturer = ?1
    List<CarEntity> findByManufacturer(String manufacturer);

    // select * from car where manufacturer = ?1 and model =?2
    List<CarEntity> findByManufacturerAndModel(String manufacturer, String model);

    // select * from car where yearOfProduction > ?1
    List<CarEntity> findByYearOfProductionGreaterThan(int yearOfProduction);

    // delete from cars where manufacturer = ?1
    void deleteByManufacturer(String manufacturer);

    // select count(*) from cars where yearOfProduction =?1
    Long countByYearOfProduction(int yearOfProduction);

    // select c from cars c inner join insurance ins on c.insurance_id = ins.id
    // where ins.insured_from < ?1
    List<CarEntity> findByInsurance_InsuredToBefore(LocalDate lastEndDate);

    // jpql method
    @Query("select car from CarEntity car inner join car.insurance ins " +
            "where ins.insuredFrom < :date and ins.insuredTo > :date")  // jezyk jpql
    List<CarEntity> findInsuredCarsAtDay(@Param("date") LocalDate date);


    //Pobierz samochody starsze niz parametr
    List<CarEntity> findByYearOfProductionLessThan(Integer yearOfProduction);

    // Pobierz samochody danego modelu (model jako parametr)
    @Query("select car from CarEntity car where car.model = :model")
    List<CarEntity> findCarsByModel(@Param("model") String model);

    List<CarEntity> findByModel(String model);

    //Policz auta które nie były ubezpieczone na dany dzień
    @Query("select count(car) from CarEntity car inner join car.insurance ins " +
            "where ins.insuredFrom > :date and ins.insuredTo < :date")
    Long countNoInsuredAtDate(@Param("date") LocalDate date);

    //Usuń auta ktorych vin zaczyna się od podanego jako parametr  String - StartsWith
    void deleteByVinStartingWith(String preVin);

   // Pobierz auta, które zostały wyprodukowane w latach podanych jako parametr (parametry od i do), użyj Between
    List<CarEntity> findByYearOfProductionIsBetween(Integer productionStart, Integer productionEnd);

}
