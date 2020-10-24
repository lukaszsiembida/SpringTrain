package pl.sda.springtrainingjavalub22.domain.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public Optional<Client> getClientByDLNumber(String driverLicenceNumber) {
        return clientRepository.findOne(driverLicenceNumber);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public void create(Client client) {
      clientRepository.create(client);
    }

    public void update(Client client) {
        clientRepository.update(client);
    }

    public void delete(String driverLicenceNumber) {
        clientRepository.delete(driverLicenceNumber);
    }
}
