package uk.co.noxtech.docker.producer;

import org.junit.Test;
import uk.co.noxtech.docker.data.Telephone;

import static org.junit.Assert.assertTrue;

public class PhoneServiceImplTest {

    private PhoneService testSubject = new PhoneServiceImpl();

    @Test
    public void shouldReturnARandomPhoneNumberFromGB() throws Exception {
        Telephone result = testSubject.randomTelephone("GB");

        assertTrue(result.getE164Number().startsWith("+44"));
    }

    @Test
    public void shouldReturnARandomPhoneNumber() throws Exception {
        Telephone result = testSubject.randomTelephone();

        System.out.println(result);
    }
}
