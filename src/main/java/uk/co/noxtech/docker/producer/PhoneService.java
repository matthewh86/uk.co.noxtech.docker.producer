package uk.co.noxtech.docker.producer;

import com.google.i18n.phonenumbers.NumberParseException;
import uk.co.noxtech.docker.data.Telephone;

public interface PhoneService {
    String[] REGION_CODES = {"GB", "US", "CN", "JP", "RU", "KR", "UA", "PL", "TH", "GR", "IT", "FR", "DE"};

    Telephone randomTelephone() throws NumberParseException;

    Telephone randomTelephone(String regionCode) throws NumberParseException;
}
