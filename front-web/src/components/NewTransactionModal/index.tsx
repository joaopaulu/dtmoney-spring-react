import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';
import Modal from 'react-modal';

import incomeImg from 'assets/income.svg';
import outcomeImg from 'assets/outcome.svg';
import closeImg from 'assets/close.svg';

import { Container, TransactionTypeContainer, RadioBox } from './styles';
import { api } from 'services/api';

type FormState = {
  title: string;
  amount: number;
  type: string;
  category: string;
};

interface NewTransactionModalProps {
  isOpen: boolean;
  onRequestClose: () => void;
}

export function NewTransactionModal({
  isOpen,
  onRequestClose,
}: NewTransactionModalProps) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormState>();

  const [type, setType] = useState('deposit');

  const onSubmit = (data: FormState) => {
    api({
      url: '/transactions',
      method: 'POST',
      data,
    })
      .then(() => {
        toast.info('Categoria salva com sucesso!');
        onRequestClose();
      })
      .catch(() => {
        toast.error('Erro ao salvar categoria!');
      });
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      overlayClassName="react-modal-overlay"
      className="react-modal-content"
    >
      <button
        type-="button"
        onClick={onRequestClose}
        className="react-modal-close"
      >
        <img src={closeImg} alt="Fechar Modal" />
      </button>

      <Container onSubmit={handleSubmit(onSubmit)}>
        <h2>Cadastrar Transação</h2>

        <input
          {...register('title')}
          type="text"
          name="title"
          placeholder="Título"
        />
        {errors.title && (
          <div className="invalid-feedback d-block">{errors.title.message}</div>
        )}

        <input {...register('amount')} type="number" placeholder="Valor" />
        {errors.amount && (
          <div className="invalid-feedback d-block">
            {errors.amount.message}
          </div>
        )}

        <TransactionTypeContainer>
          <RadioBox
            {...register('type', { required: true })}
            type="button"
            onClick={() => setType('deposit')}
            value="deposit"
            isActive={type === 'deposit'}
            activeColor="green"
          >
            <img src={incomeImg} alt="Entrada" />
            <span>Entrada</span>
          </RadioBox>

          <RadioBox
            {...register('type', { required: true })}
            type="button"
            onClick={() => setType('withdraw')}
            value="withdraw"
            isActive={type === 'withdraw'}
            activeColor="red"
          >
            <img src={outcomeImg} alt="Saída" />
            <span>Saída</span>
          </RadioBox>
        </TransactionTypeContainer>

        <input {...register('category')} type="text" placeholder="Categoria" />

        <button type="submit">Cadastrar</button>
      </Container>
    </Modal>
  );
}
