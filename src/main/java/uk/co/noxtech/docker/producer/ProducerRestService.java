package uk.co.noxtech.docker.producer;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ProducerRestService {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping("/")
    public String index() {
        try {
            return phoneService.randomTelephone().getE164Number();
        } catch (NumberParseException e) {
            return e.toString();
        }
    }

}