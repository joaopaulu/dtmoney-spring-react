package br.com.joaopaulu.dtmoney.repositories;

import br.com.joaopaulu.dtmoney.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
