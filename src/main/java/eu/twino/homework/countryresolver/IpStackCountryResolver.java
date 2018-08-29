package eu.twino.homework.countryresolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            IpStackResposne ipStackResposne = restTemplate.getForObject(finalUrl, IpStackResposne.class, ipStackSecretKey);
            return Optional.ofNullable(ipStackResposne)
                    .map(IpStackResposne::getCountryCode);
        } catch (Exception e) {
            LOGGER.error("Error getting country!", e);
            return Optional.empty();
        }
    }
}
