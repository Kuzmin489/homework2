package eu.twino.homework.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.twino.homework.blacklist.BlackListRepository;
import eu.twino.homework.blacklist.BlackListService;
import eu.twino.homework.loan.LoanStatus;
import eu.twino.homework.loan.repository.LoanEntity;
import eu.twino.homework.loan.repository.LoanRepository;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanAppIntegrationTest {



    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        loanRepository.deleteAll();
    }

    @Test
    public void shouldShowAppliedLoans() throws Exception {
        //Arrange
        List<LoanEntity> generatedLoans = genTestLoanData(10, true);
        List<LoanEntity> generatedRejectedLoans = genTestLoanData(10, false);
        loanRepository.saveAll(generatedLoans);
        loanRepository.saveAll(generatedRejectedLoans);
        //Act
        mockMvc.perform(get("/rest/v1/loans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void shouldShowMoreLoansIfPageSizeParameterSet() throws Exception {
        //Arrange
        List<LoanEntity> generatedLoans = genTestLoanData(20, true);
        loanRepository.saveAll(generatedLoans);
        //Act
        mockMvc.perform(get("/rest/v1/loans").param("pageSize", "20"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(20)));
    }

    @Test
    public void shouldShowSecondPage() throws Exception {
        //Arrange
        List<LoanEntity> generatedLoans = genTestLoanData(15, true);
        loanRepository.saveAll(generatedLoans);
        //Act
        mockMvc.perform(get("/rest/v1/loans").param("page", "1"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void shouldApplyLoan() throws Exception {
        //Arrange
        LoanRequestDto loanRequestDto = genLoanRequestDto("testPersCode");
        //Act
        mockMvc.perform(post("/rest/v1/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loanRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanStatus", is("APPLIED")));
    }

    @Test
    public void shouldRejectLoanIfPersonIdInBlackList() throws Exception {
        //Arrange
        String blacklistedPersonCode = "11111-22222";
        blackListRepository.deleteAll();
        blackListService.addPersonIdToBlackList(blacklistedPersonCode,"Blocked for test");
        LoanRequestDto loanRequestDto = genLoanRequestDto(blacklistedPersonCode);

        //Act
        mockMvc.perform(post("/rest/v1/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loanRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanStatus", is("REJECTED")));
    }

    @Test
    public void shouldShowErrorIfApplyLoanTwiceWithSamePersonId() throws Exception {
        //Arrange
        LoanRequestDto loanRequestDto = genLoanRequestDto("testPersCode");
        //Act
        mockMvc.perform(post("/rest/v1/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loanRequestDto)))
                .andExpect(status().isOk());


        mockMvc.perform(post("/rest/v1/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loanRequestDto)))
                .andExpect(status().is(400));
    }

    @Test
    public void shouldShowErrorIfSomeOfParameterIsMissing() throws Exception {
        //Arrange
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        //Act
        mockMvc.perform(post("/rest/v1/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loanRequestDto)))
                .andExpect(status().is(400)) ;
    }

    private List<LoanEntity> genTestLoanData(int size, boolean applied) {
        return IntStream.rangeClosed(1, size)
                .mapToObj(i ->
                        new LoanEntity()
                                .setPersonalId("personID_" + RandomUtils.nextInt())
                                .setAmount(BigDecimal.valueOf(RandomUtils.nextLong(1, 1000)))
                                .setApplierName("User" + i)
                                .setApplierSurname("Test" + i)
                                .setApplyDate(LocalDateTime.now().minusDays(i))
                                .setLoanStatus(applied ? LoanStatus.APPLIED : LoanStatus.REJECTED)
                                .setOriginCountry("LV")
                                .setTerm(24)
                )
                .collect(Collectors.toList());
    }

    private LoanRequestDto genLoanRequestDto(String personCode) {
        return new LoanRequestDto()
                .setPersonalId(personCode)
                .setAmount(BigDecimal.TEN)
                .setName("Test name")
                .setSurname("Test surname")
                .setTerm(24);
    }


}