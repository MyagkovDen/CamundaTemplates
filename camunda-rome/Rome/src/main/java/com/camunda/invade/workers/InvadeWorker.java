package com.camunda.invade.workers;

import java.util.logging.Logger;
import java.awt.Desktop;
import java.net.URI;

import org.camunda.bpm.client.ExternalTaskClient;


public class InvadeWorker {
    private final static Logger LOGGER = Logger.getLogger(InvadeWorker.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe("InvadeGaul")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    LOGGER.info("Invade Gaul!!!");

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();

        client.subscribe("InvadePersia")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    LOGGER.info("Invade Persia!!!");

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
