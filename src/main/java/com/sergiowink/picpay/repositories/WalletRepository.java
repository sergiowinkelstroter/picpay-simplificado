package com.sergiowink.picpay.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiowink.picpay.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
