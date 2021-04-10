package br.com.joaopaulu.dtmoney.service;

import br.com.joaopaulu.dtmoney.config.Mapper;
import br.com.joaopaulu.dtmoney.dto.TransactionDTO;
import br.com.joaopaulu.dtmoney.entities.Transaction;
import br.com.joaopaulu.dtmoney.mappers.TransactionMapper;
import br.com.joaopaulu.dtmoney.repositories.TransactionRepository;
import br.com.joaopaulu.dtmoney.service.exceptions.DatabaseException;
import br.com.joaopaulu.dtmoney.service.exceptions.ResourceNotFoundException;
import br.com.joaopaulu.dtmoney.service.ifaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository repository;


  /*  @Transactional(readOnly = true)
    public Page<TransactionDTO> findAllPaged(Pageable pageable){
        Page<Transaction> entityPage = repository.findAll(pageable);
        List<TransactionDTO> dtos = Mapper.factory(TransactionMapper.class).entityToDtoList(entityPage.getContent());
        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }*/
    @Override
    @Transactional(readOnly = true)
    public Page<TransactionDTO> findAllPaged(PageRequest pageRequest){
        Page<Transaction> list = repository.findAll(pageRequest);
        return list.map(TransactionDTO::new);
    }

    @Override
    @Transactional
    public TransactionDTO insert(TransactionDTO dto) {
        Transaction entity = new Transaction();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return Mapper.factory(TransactionMapper.class).entityToDto(entity);
    }

    @Override
    @Transactional
    public TransactionDTO update(Long id, TransactionDTO dto) {
        try {
            Transaction entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return Mapper.factory(TransactionMapper.class).entityToDto(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDTO findById(Long id) {
        Optional<Transaction> obj = repository.findById(id);
        Transaction entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidate não encontrada"));
        return Mapper.factory(TransactionMapper.class).entityToDto(entity);
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Intedrity violation");
        }
    }

    private void copyDtoToEntity(TransactionDTO dto, Transaction entity){
        entity.setTitle(dto.getTitle());
        entity.setAmount(dto.getAmount());
        entity.setCategory(dto.getCategory());
        entity.setType(dto.getType());
        entity.setCreatedAt(dto.getCreatedAt());
    }
}
