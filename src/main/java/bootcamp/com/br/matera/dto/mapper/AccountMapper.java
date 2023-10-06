package bootcamp.com.br.matera.dto.mapper;

import bootcamp.com.br.matera.domain.Account;
import bootcamp.com.br.matera.dto.request.AccountRequest;
import bootcamp.com.br.matera.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountResponse toResponse(Account account);

    Account toModel(AccountRequest request);
}
