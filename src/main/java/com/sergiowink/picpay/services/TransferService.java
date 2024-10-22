package com.sergiowink.picpay.services;


import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.sergiowink.picpay.controllers.dto.TransferDTO;
import com.sergiowink.picpay.entities.Transfer;
import com.sergiowink.picpay.entities.Wallet;
import com.sergiowink.picpay.exceptions.InsufficientBalanceException;
import com.sergiowink.picpay.exceptions.TransferNotAllowedForWalletTypeException;
import com.sergiowink.picpay.exceptions.TransferNotAuthorizedException;
import com.sergiowink.picpay.exceptions.WalletNotFoundException;
import com.sergiowink.picpay.repositories.TransferRepository;
import com.sergiowink.picpay.repositories.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(AuthorizationService authorizationService, NotificationService notificationService, TransferRepository transferRepository, WalletRepository walletRepository) {
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) { 
        var sender = walletRepository.findById(transferDTO.payer()).orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

        var receiver = walletRepository.findById(transferDTO.payee()).orElseThrow(() -> new WalletNotFoundException(transferDTO.payee()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());

        receiver.credit(transferDTO.value());


        var transfer = new Transfer(sender, receiver, transferDTO.value());


        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));
        

        return transferResult;
     }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {
        if(!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!sender.isBalancerEqualOrGreatherThan(transferDTO.value())){
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDTO)){
            throw new TransferNotAuthorizedException();
        }
    }
}
