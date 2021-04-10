package br.com.joaopaulu.dtmoney.dto;

import br.com.joaopaulu.dtmoney.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private Double amount;

    private String type;

    private String category;

    private LocalDateTime createdAt;


    public TransactionDTO(Transaction entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.amount = entity.getAmount();
        this.type = entity.getType();
        this.category = entity.getCategory();
        this.createdAt = entity.getCreatedAt();
    }


}
