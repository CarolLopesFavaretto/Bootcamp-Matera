package bootcamp.com.br.matera.controller;

import bootcamp.com.br.matera.dto.request.OwnerRequest;
import bootcamp.com.br.matera.dto.response.OwnerResponse;
import bootcamp.com.br.matera.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerRequest request) {
        return service.createOwner(request);
    }
}
