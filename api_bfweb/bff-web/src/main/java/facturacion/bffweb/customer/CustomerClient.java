package facturacion.bffweb.customer;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "bff.cliente", url = "http://localhost:8000/api/persona")
public interface CustomerClient {

    @GetMapping("/{id}/")
    CustomerDTO findById(@PathVariable("id") Long id);

    @GetMapping("/")
    List<CustomerDTO> findAll();

    @PostMapping("/") 
    CustomerDTO save(CustomerDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    CustomerDTO update(@PathVariable("id")  Long id, CustomerDTO entity);

    @PutMapping("/{id}/completardatos/")
    CustomerDTO completardatos(@PathVariable("id")  Long id);

}
