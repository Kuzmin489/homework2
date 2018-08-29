package eu.twino.homework.countryresolver;

import java.util.Optional;

public interface CountryResolver {

    Optional<String> getCountryCode(String ip);
}
