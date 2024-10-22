package com.sergiowink.picpay.services;

import org.springframework.stereotype.Service;

import com.sergiowink.picpay.controllers.dto.CreateWalletDTO;
import com.sergiowink.picpay.entities.Wallet;
import com.sergiowink.picpay.exceptions.WalletDataAlreadyExistsException;
import com.sergiowink.picpay.repositories.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }    

    public Wallet createWallet(CreateWalletDTO dto) {
        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

        if(walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
