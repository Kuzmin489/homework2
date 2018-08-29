package eu.twino.homework.countryresolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class IpStackCountryResolver implements CountryResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpStackCountryResolver.class);

    private final String ipStackSecretKey;

    private final String upStackUrl;

    private RestTemplate restTemplate;

    public IpStackCountryResolver(@Value("${ipstack.url}") String upStackUrl,
                                  @Value("${ipstack.secret.key}") String ipStackSecretKey) {
        this.ipStackSecretKey = ipStackSecretKey;
        this.upStackUrl = upStackUrl;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Optional<String> getCountryCode(String ip) {
        String finalUrl = upStackUrl + ip;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromUriString(finalUrl).queryParam("access_key", ipStackSecretKey);
            IpStackResposne ipStackResposne = restTemplate.getForObject(builder.toUriString(), IpStackResposne.class);
            return Optional.ofNullable(ipStackResposne)
                    .map(IpStackResposne::getCountryCode);
        } catch (Exception e) {
            LOGGER.error("Error getting country!", e);
            return Optional.empty();
        }
    }
}
