package br.com.joaopaulu.dtmoney.mappers;

import br.com.joaopaulu.dtmoney.dto.TransactionDTO;
import br.com.joaopaulu.dtmoney.entities.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    List<TransactionDTO> entityToDtoList(List<Transaction> entities);

    TransactionDTO entityToDto(Transaction entity);

    Transaction dtoToEntityTransaction(TransactionDTO dto);
}
