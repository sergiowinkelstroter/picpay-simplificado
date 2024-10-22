package com.sergiowink.picpay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sergiowink.picpay.controllers.dto.TransferDTO;
import com.sergiowink.picpay.entities.Transfer;
import com.sergiowink.picpay.services.TransferService;

import jakarta.validation.Valid;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO dto) {
       var resp = transferService.transfer(dto);

       return ResponseEntity.ok(resp);
    }

}
