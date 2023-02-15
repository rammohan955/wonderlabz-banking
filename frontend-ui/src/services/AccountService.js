import axios from 'axios';

const TRANSACTION_API_BASE_URL = 'http://localhost:8080/accounts';

class AccountService{

    async getAccounts() {
        const response = await axios.get(TRANSACTION_API_BASE_URL+'/view');
        return response.data;
    }

    async createAccount(accountData) {
        const response = await axios.post(TRANSACTION_API_BASE_URL+'/create', accountData);
        return response.data;
    }
}

export default new AccountService();