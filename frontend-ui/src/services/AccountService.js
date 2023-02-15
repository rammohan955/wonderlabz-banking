import axios from 'axios';

const TRANSACTION_API_BASE_URL = 'http://localhost:8080/api/v1';

class TransactionService{

    getAccount() {
        return axios.get(TRANSACTION_API_BASE_URL+'/account');
    }

    createAccount(data) {
        return http.post(TRANSACTION_API_BASE_URL+'/account', data);
    }
}

export default new TransactionService();