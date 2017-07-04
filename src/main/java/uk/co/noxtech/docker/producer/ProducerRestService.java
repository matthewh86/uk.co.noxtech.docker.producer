package uk.co.noxtech.docker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.i18n.phonenumbers.NumberParseException;

import uk.co.noxtech.docker.data.Telephone;

@RestController
public class ProducerRestService {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/")
    public String index() {
        try {
            Telephone telephone = phoneService.randomTelephone();
            producerService.sendMessage(telephone);
            return telephone.getE164Number();
        } catch (NumberParseException e) {
            return e.toString();
        }
    }

}