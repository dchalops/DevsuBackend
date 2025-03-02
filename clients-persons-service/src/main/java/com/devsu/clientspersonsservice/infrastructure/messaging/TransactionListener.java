package com.devsu.clientspersonsservice.infrastructure.messaging;

import com.devsu.clientspersonsservice.application.dto.TransactionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @RabbitListener(queues = "transaction.queue")
    public void receiveTransaction(TransactionDTO transactionDTO) {
        System.out.println("Received transaction for client: " + transactionDTO.getClientId());
    }
}
