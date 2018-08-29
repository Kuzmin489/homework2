package eu.twino.homework.countryresolver;

import eu.twino.homework.rest.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class IpStackCountryResolver implements CountryResolver {
    private static final String IP_STACK_URL = "http://api.ipstack.com/";

    @Value("ipstack.secret.key")
    private String ipStackSecretKey;

    private RestTemplate restTemplate;

    public IpStackCountryResolver() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Optional<String> getCountryCode(HttpServletRequest request) {

        return WebUtils.getClientIp(request).map(ip -> {
            String finalIp = IP_STACK_URL + ip;
            IpStackResposne ipStackResposne = restTemplate.getForObject(finalIp, IpStackResposne.class, ipStackSecretKey);
            return ipStackResposne.getCountryCode();
        });
    }
}
