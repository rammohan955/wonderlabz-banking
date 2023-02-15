import axios from 'axios';

const TRANSACTION_API_BASE_URL = 'http://localhost:8080/api/v1';

class AccountService{

    async getAccounts() {
        const response = await axios.get(TRANSACTION_API_BASE_URL+'/accounts');
        return response.data;
    }

    async createAccount(accountData) {
        const response = await axios.post(TRANSACTION_API_BASE_URL+'/account', accountData);
        return response.data;
    }
}

export default new AccountService();