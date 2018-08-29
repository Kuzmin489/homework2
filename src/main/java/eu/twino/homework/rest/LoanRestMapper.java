package eu.twino.homework.rest;

import eu.twino.homework.loan.Loan;
import eu.twino.homework.loan.LoanRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanRestMapper {

    @Mappings({
            @Mapping(source = "loanRequestDto.name", target = "applierName"),
            @Mapping(source = "loanRequestDto.surname", target = "applierSurname")
    })
    LoanRequest dtoToRequest(LoanRequestDto loanRequestDto, String originCountry);

    LoanDto modelToDto(Loan loan);

    List<LoanDto> modelListToDto(List<Loan> loanList);
}
