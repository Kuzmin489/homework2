package eu.twino.homework.countryresolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface CountryResolver {

    Optional<String> getCountryCode(HttpServletRequest request);
}
