package pl.sda.springtrainingjavalub22.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.springtrainingjavalub22.domain.client.Client;
import pl.sda.springtrainingjavalub22.domain.client.ClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientApi {

    private ClientService clientService;

    @GetMapping
    public List<Client> getAll(){return clientService.getAll();}

    @GetMapping("/{driverLicenceNumber}")
    public ResponseEntity<Client> getOne(@PathVariable  String driverLicenceNumber){
        return clientService.getClientByDLNumber(driverLicenceNumber).map(ResponseEntity::ok).
                orElseGet( () -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody Client client){
        clientService.create(client);
    }

    @PutMapping
    public void updateClient(@RequestBody Client client) {clientService.update(client);}

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@RequestParam String driverLicenceNumber) {
        clientService.delete(driverLicenceNumber);
    }

}
