package com.sofka.clientspersonsservice.infrastructure.messaging;

import com.sofka.clientspersonsservice.application.dto.TransactionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @RabbitListener(queues = "transaction.queue")
    public void receiveTransaction(TransactionDTO transactionDTO) {
        System.out.println("Received transaction for client: " + transactionDTO.getClientId());
        // 🔹 Aquí puedes actualizar información del cliente si es necesario
    }
}
