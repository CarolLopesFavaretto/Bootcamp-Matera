package bootcamp.com.br.matera.dto.mapper;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.dto.response.ContaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ContaMapper {

    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    ContaResponse toResponse(Conta conta);

    Conta toModel(ContaRequest request);
}
