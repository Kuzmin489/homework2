package eu.twino.homework.rest;

import eu.twino.homework.countryresolver.CountryResolver;
import eu.twino.homework.loan.Loan;
import eu.twino.homework.loan.LoanRequest;
import eu.twino.homework.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/loans")
public class LoanController {

    private final LoanService loanService;


    private final CountryResolver countryResolver;

    private final LoanRestMapper loanMapper;

    @Autowired
    public LoanController(LoanService loanService, CountryResolver countryResolver, LoanRestMapper loanMapper) {
        this.loanService = loanService;
        this.countryResolver = countryResolver;
        this.loanMapper = loanMapper;
    }

    @GetMapping
    public List<LoanDto> getAppliedLoans(
                                         @RequestParam(value="page", defaultValue="0") int page,
                                         @RequestParam(value="pageSize", defaultValue="10") int pageSize
    ) {
        List<Loan> loans = loanService.getAppliedLoans(page, pageSize);
        return loanMapper.modelListToDto(loans);
    }

    @PostMapping
    public void applyForLoan(@RequestBody @Valid LoanRequestDto loanRequestDto, HttpServletRequest request) {
        String originCountry = WebUtils.getClientIp(request)
                .flatMap(countryResolver::getCountryCode)
                .orElse("LV");

        LoanRequest loanRequest = loanMapper.dtoToRequest(loanRequestDto, originCountry);
        loanService.applyForLoan(loanRequest);
    }


}
