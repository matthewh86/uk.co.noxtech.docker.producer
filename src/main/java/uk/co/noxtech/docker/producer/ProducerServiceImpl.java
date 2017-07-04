package uk.co.noxtech.docker.producer;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.NumberParseException;

import uk.co.noxtech.docker.data.Telephone;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private PhoneService phoneService;

    private static boolean serviceStarted = false;

    private static int INTERVAL = 2000;

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
                        e.printStackTrace();
                    }
                }
            };

            timer.schedule(timerTask, INTERVAL, INTERVAL);
        }
    }

    public void sendMessage(Telephone telephone) {
        System.out.println(telephone);
    }

}
