package eu.twino.homework.countryresolver;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IpStackCountryResolverTest {

    IpStackCountryResolver ipStackCountryResolver =
            new IpStackCountryResolver("http://api.ipstack.com/", "bdc29dce3b65b8e3fa96130c52529269");

    @Test
    @Ignore("For Local testing only")
    public void shouldGetIp() {
        //Arrange
        String ruIp = "195.208.131.1";

        //Act
        Optional<String> countryCode = ipStackCountryResolver.getCountryCode(ruIp);
        //Assert
        assertTrue(countryCode.isPresent());
        assertThat(countryCode.get(), is("RU"));
    }

}