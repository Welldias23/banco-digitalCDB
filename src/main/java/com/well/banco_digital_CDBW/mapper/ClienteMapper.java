package  com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.ClienteDto;
import com.well.banco_digital_CDBW.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteDto toClienteDto(Cliente clinte);
	Cliente toCliente(ClienteDto clienteDto);
}
