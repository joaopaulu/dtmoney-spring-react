export type TransactionsResponse = {
  content: Transaction[];
  totalPages: number;
};

export type Transaction = {
  id: number;
  title: string;
  amount: number;
  type: string;
  category: string;
  createdAt: string;
};
