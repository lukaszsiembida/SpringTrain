package pl.sda.springtrainingjavalub22.domain.client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Optional<Client> findOne(String driverLicenceNumber);

    List<Client> findAll();

    void create(Client client);

    void update(Client client);

    void delete(String driverLicenceNumber);

}
