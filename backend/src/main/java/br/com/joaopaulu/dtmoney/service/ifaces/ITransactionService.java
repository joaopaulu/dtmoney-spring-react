package br.com.joaopaulu.dtmoney.service.ifaces;


import br.com.joaopaulu.dtmoney.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ITransactionService {

    TransactionDTO findById(Long id);

    @Transactional(readOnly = true)
    Page<TransactionDTO> findAllPaged(PageRequest pageRequest);

    TransactionDTO insert(TransactionDTO dto);

    TransactionDTO update(Long id, TransactionDTO dto);

    void delete(Long id);

}
