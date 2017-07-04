package uk.co.noxtech.docker.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.i18n.phonenumbers.NumberParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.noxtech.docker.data.Telephone;

import java.util.Timer;
import java.util.TimerTask;

import static uk.co.noxtech.docker.producer.ProducerApplication.INTERVAL;
import static uk.co.noxtech.docker.producer.ProducerApplication.QUEUE_NAME;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger LOGGER = LogManager.getLogger(ProducerApplication.class);

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static boolean serviceStarted = false;

    @Override
    public void startService() {
        if (!serviceStarted) {
            serviceStarted = true;
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        sendMessage(phoneService.randomTelephone());
                    } catch (NumberParseException e) {
                        LOGGER.error(e);
                    }
                }
            };

            timer.schedule(timerTask, INTERVAL, INTERVAL);
        }
    }

    public void sendMessage(Telephone telephone) {
        Runnable messageTask = () -> {
            try {
                LOGGER.info("Processing " + telephone);
                rabbitTemplate.convertAndSend(QUEUE_NAME, telephone.toJsonString());
            } catch (JsonProcessingException e) {
                LOGGER.error(e);
            }
        };
        new Thread(messageTask).start();
    }

}
