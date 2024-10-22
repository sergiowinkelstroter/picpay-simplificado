package com.sergiowink.picpay.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiowink.picpay.entities.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID>{

}
