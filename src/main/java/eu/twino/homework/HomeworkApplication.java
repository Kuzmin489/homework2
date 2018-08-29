package eu.twino.homework;

import eu.twino.homework.blacklist.BlackList;
import eu.twino.homework.blacklist.BlackListRepository;
import eu.twino.homework.loan.LoanStatus;
import eu.twino.homework.loan.repository.LoanEntity;
import eu.twino.homework.loan.repository.LoanRepository;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableJpaRepositories
public class HomeworkApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeworkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

    @Bean
    @Profile("DataGenenration")
    public CommandLineRunner run(LoanRepository loanRepository, BlackListRepository blackListRepository) {
        return args -> {
            LOGGER.info("Generating test data");
            loanRepository.saveAll(genTestLoanData());
            blackListRepository.saveAll(genBlackListdata());

        };
    }

    private List<LoanEntity> genTestLoanData() {
        return IntStream.rangeClosed(1, 20)
                .mapToObj(i ->
                        new LoanEntity()
                                .setPersonalId("personID_"+i)
                                .setAmount(BigDecimal.valueOf(RandomUtils.nextLong(1, 1000)))
                                .setApplierName("User" + i)
                                .setApplierSurname("Test" + i)
                                .setApplyDate(LocalDateTime.now().minusDays(i))
                                .setLoanStatus(i % 2 == 0 ? LoanStatus.REJECTED : LoanStatus.APPLIED)
                                .setOriginCountry("LV")
                                .setTerm(24)
                )
                .collect(Collectors.toList());
    }

    private List<BlackList> genBlackListdata() {
        return IntStream.rangeClosed(10, 20)
                .mapToObj(i ->
                        new BlackList("personID_" + i, "Test data")

                )
                .collect(Collectors.toList());
    }


}
