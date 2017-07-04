package uk.co.noxtech.docker.producer;

import uk.co.noxtech.docker.data.Telephone;

public interface ProducerService {

    void startService();

    void sendMessage(Telephone telephone);

}
