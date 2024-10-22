package com.sergiowink.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sergiowink.picpay.entities.Transfer;

@FeignClient(
    name = "NotificationClient",
    url = "${client.notification-service.url}"
)
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void>  sendNotification(@RequestBody Transfer transfer);
}
