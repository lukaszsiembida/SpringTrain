package pl.sda.springtrainingjavalub22.external.client;

import org.springframework.stereotype.Component;
import pl.sda.springtrainingjavalub22.domain.client.Client;
import pl.sda.springtrainingjavalub22.domain.client.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryClientRepository implements ClientRepository {

    private List<Client> clients = new ArrayList<>();
    private Integer number = 0;
    private String driverLicenceNumber = "00001/01/";

    @Override
    public Optional<Client> findOne(String driverLicenceNumber) {
        return clients.stream().filter(client -> client.getDriverLicenceNumber().equals(driverLicenceNumber)).findFirst();
    }

    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public void create(Client client) {
        client.setDriverLicenceNumber(driverLicenceNumber+(number++));
        clients.add(client);
    }

    @Override
    public void update(Client client) {
        delete(client.getDriverLicenceNumber());
        clients.add(client);
    }

    @Override
    public void delete(String driverLicenceNumber) {
    clients.removeIf(existingClient -> existingClient.getDriverLicenceNumber().equals(driverLicenceNumber));
    }
}
