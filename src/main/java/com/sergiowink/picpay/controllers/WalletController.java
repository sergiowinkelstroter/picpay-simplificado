package com.sergiowink.picpay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.sergiowink.picpay.controllers.dto.CreateWalletDTO;
import com.sergiowink.picpay.entities.Wallet;
import com.sergiowink.picpay.services.WalletService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class WalletController {

    private final WalletService walletService;;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDTO dto)  {
        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }
    

}
