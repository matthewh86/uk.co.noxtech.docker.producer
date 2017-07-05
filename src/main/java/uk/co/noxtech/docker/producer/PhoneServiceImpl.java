package uk.co.noxtech.docker.producer;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.stereotype.Service;
import uk.co.noxtech.docker.data.Telephone;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PhoneServiceImpl implements PhoneService {

    private static final String DEFAULT_REGION = "GB";

    @Override
    public Telephone randomTelephone() throws NumberParseException {
        int randomRegion = ThreadLocalRandom.current().nextInt(0, REGION_CODES.length);

        return new Telephone(Telephone.createPhoneNumber(randomPhoneNumber(), DEFAULT_REGION, REGION_CODES[randomRegion]));
    }

    @Override
    public Telephone randomTelephone(String regionCode) throws NumberParseException {
        return new Telephone(Telephone.createPhoneNumber(randomPhoneNumber(), DEFAULT_REGION, regionCode));
    }

    private String randomPhoneNumber() {
        int randomDigitCount = ThreadLocalRandom.current().nextInt(5, 16);

        StringBuilder sb = new StringBuilder(randomDigitCount);
        for (int i = 0; i < randomDigitCount; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        return sb.toString();
    }

}
