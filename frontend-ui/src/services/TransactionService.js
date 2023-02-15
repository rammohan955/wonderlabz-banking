import axios from 'axios';

const TRANSACTION_API_BASE_URL = 'http://localhost:8080/transaction';

class TransactionService{

    async getAllTransactions() {
        const response = await axios.get(TRANSACTION_API_BASE_URL+'/view');
        return response.data;
    }

    async makeTransfer(data) {
        const response = await axios.post(TRANSACTION_API_BASE_URL+'/transfer', data);
        return response.data;
    }

    async withdraw(data) {
        const response = await axios.post(TRANSACTION_API_BASE_URL+'/withdraw', data);
        return response.data;
    }

    async deposit(data) {
        const response = await axios.post(TRANSACTION_API_BASE_URL+'/deposit', data);
        return response.data;
    }
}

export default new TransactionService();