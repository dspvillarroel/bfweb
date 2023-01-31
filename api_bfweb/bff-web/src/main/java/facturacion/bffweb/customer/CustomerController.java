package facturacion.bffweb.customer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/person")
@CrossOrigin({"*"})
public class CustomerController {

    @Autowired CustomerClient client;

    @GetMapping("/")
    public List<CustomerDTO> findAll() {
        return client.findAll();
    }

    @GetMapping("/{id}/")
    public CustomerDTO findById(@PathVariable Long id){
        return client.findById(id);
    }

    @PostMapping("/")
    public CustomerDTO save(@RequestBody CustomerDTO entity){
        return client.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        client.deleteById(id);
    }

    @PutMapping("/{id}/")
    public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO entity){
        return client.update(id, entity);
    }
    @PatchMapping("/{id}/")
    public CustomerDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        CustomerDTO ClienteDTO = client.findById(id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = CustomerDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(ClienteDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return client.update(id, ClienteDTO);
    }
}
