package com.sergiowink.picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiowink.picpay.entities.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {

}
