package facturacion.bffweb.factura;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("api/factura")
@CrossOrigin({"*"})
public class FacturaController {

    @Autowired FacturaClient client;

    @GetMapping("/")
    public List<FacturaDTO> findAll(@RequestHeader("Authorization") String authHeader) {
        return client.findAll(authHeader);
    }
    
    @GetMapping("/{id}/")
    public FacturaDTO findById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        return client.findFacturaById(authHeader, id);
    }

    @GetMapping("/pdf/{id}/")
    public ResponseEntity<byte[]> pdfById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        return client.pdfById(authHeader, id);
    }

    @PostMapping("/")
    public FacturaDTO save(@RequestHeader("Authorization") String authHeader, @RequestBody FacturaDTO entity){
        return client.save(authHeader, entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        client.deleteById(authHeader, id);
    }

    @PutMapping("/{id}/")
    public FacturaDTO update(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody FacturaDTO entity){
        return client.update(authHeader, id, entity);
    }
    
    @PatchMapping("/{id}/")
    public FacturaDTO partialUpdate(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody Map<String, Object> fields){

        FacturaDTO FacturaDTO = client.findFacturaById(authHeader, id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = FacturaDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(FacturaDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepci??n si ocurre alg??n error al acceder al campo
            }
        }
        return client.update(authHeader, id, FacturaDTO);
    }
}
