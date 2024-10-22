package com.sergiowink.picpay.services;

import org.springframework.stereotype.Service;

import com.sergiowink.picpay.client.AuthorizationClient;
import com.sergiowink.picpay.controllers.dto.TransferDTO;
import com.sergiowink.picpay.exceptions.PicPayException;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer) {
        var resp = authorizationClient.isAuthorized();

        if(resp.getStatusCode().isError()){
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }

}
