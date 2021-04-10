import { TransactionsResponse } from 'components/types/Transaction';
import { useCallback, useEffect, useState } from 'react';
import { api } from 'services/api';
import { Container } from './styles';

export function TransactionTable() {
  const [
    transactionsResponse,
    setTransactionsResponse,
  ] = useState<TransactionsResponse>();

  const [activePage, setActivePage] = useState(0);

  const getTransactions = useCallback(() => {
    const params = {
      page: activePage,
      linesPerPage: 12,
    };

    api({ url: '/transactions', params }).then(response =>
      setTransactionsResponse(response.data),
    );
  }, [activePage]);

  useEffect(() => {
    getTransactions();
  }, [getTransactions]);

  return (
    <Container>
      <table>
        <thead>
          <tr>
            <th>Título</th>
            <th>Valor</th>
            <th>Categoria</th>
            <th>Data</th>
          </tr>
        </thead>

        <tbody>
          {transactionsResponse?.content.map(transactions => (
            <tr key={transactions.id}>
              <td>{transactions.title}</td>
              <td className={transactions.type}>
                {new Intl.NumberFormat('pt-BR', {
                  style: 'currency',
                  currency: 'BRL',
                }).format(transactions.amount)}
              </td>
              <td>{transactions.category}</td>
              <td>
                {new Intl.DateTimeFormat('pt-BR').format(
                  new Date(transactions.createdAt),
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </Container>
  );
}
