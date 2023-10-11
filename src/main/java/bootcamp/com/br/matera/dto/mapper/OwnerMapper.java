package bootcamp.com.br.matera.dto.mapper;

import bootcamp.com.br.matera.domain.Owner;
import bootcamp.com.br.matera.dto.request.OwnerRequest;
import bootcamp.com.br.matera.dto.response.OwnerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    OwnerResponse toResponse(Owner owner);

    Owner toModel(OwnerRequest request);


}
