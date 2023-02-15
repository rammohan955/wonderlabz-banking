import axios from 'axios';

const TRANSACTION_API_BASE_URL = 'http://localhost:8080/api/v1';

class TransactionService{

    getAllTransactions() {
        return axios.get(TRANSACTION_API_BASE_URL+'/transactions');
    }

    makeTransfer(data) {
        return http.post(TRANSACTION_API_BASE_URL+'/transfer', data);
    }

    withdraw(data) {
        return http.post(TRANSACTION_API_BASE_URL+'/withdraw', data);
    }

    deposit(data) {
        return http.post(TRANSACTION_API_BASE_URL+'/deposit', data);
    }
}

export default new TransactionService();