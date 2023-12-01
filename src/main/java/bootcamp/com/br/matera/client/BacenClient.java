package bootcamp.com.br.matera.client;

import bootcamp.com.br.matera.dto.request.AccountClientRequest;
import bootcamp.com.br.matera.dto.request.ActiveRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bacenClient", url = "${feign.client.url}")
public interface BacenClient {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createAccount(@RequestBody AccountClientRequest account);

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void updateActive(@PathVariable Long id, @RequestBody ActiveRequest account);

}
