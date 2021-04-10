import { useState } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import Modal from 'react-modal';
import { Dashboard } from 'components/Dashboard';
import { Header } from './components/Header';

import { GlobalStyle } from './styles/global';
import { NewTransactionModal } from 'components/NewTransactionModal';

Modal.setAppElement('#root');

export function App() {
  const [isNewTransationModalOpen, setIsNewTransactionModalOpen] = useState(
    false,
  );

  function handleOpenNewTransactionModal() {
    setIsNewTransactionModalOpen(true);
  }

  function handleCloseNewTransactionModal() {
    setIsNewTransactionModalOpen(false);
  }

  return (
    <>
      <Header onOpenNewTransactionModal={handleOpenNewTransactionModal} />
      <Dashboard />
      <NewTransactionModal
        isOpen={isNewTransationModalOpen}
        onRequestClose={handleCloseNewTransactionModal}
      />

      <GlobalStyle />
      <ToastContainer />
    </>
  );
}
