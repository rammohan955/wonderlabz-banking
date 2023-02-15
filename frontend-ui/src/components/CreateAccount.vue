<template>
<v-container>
    <h1>Account Management</h1>
    <p>Account Registration</p>
    <v-row>
      <v-col sm="12">
        <v-alert v-if="responseSuccess" dense text type="success">
          You have successfully created Account.
        </v-alert>
      </v-col>
      <v-col sm="6">
        <h3>Account Registration</h3>
        <v-text-field
          v-model="accountRegistration.accountNumber"
          label="Account Number"
        ></v-text-field>
        <v-text-field
          v-model="accountRegistration.accountType"
          label="Account Type"
        ></v-text-field>
        <v-text-field
          v-model="accountRegistration.userName"
          label="User Name"
        ></v-text-field>
        <v-text-field
          v-model="accountRegistration.openingBalance"
          label="Opening Balance"
        ></v-text-field>
        <v-btn color="primary" v-on:click="createAccount">
          Create Account
        </v-btn>
      </v-col>
      <v-col sm="6">
        <h3>Accounts</h3>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  Account ID
                </th>
                <th class="text-left">
                  Account Number
                </th>
                <th class="text-left">
                  Account Type
                </th>
                <th class="text-left">
                  User Name
                </th>
                <th class="text-left">
                  Current Balance
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="account in accounts" :key="account.id">
                <td>{{ account.id }}</td>
                <td>{{ account.accountNumber }}</td>
                <td>{{ account.accountType }}</td>
                <td>{{ account.userName }}</td>
                <td>{{ account.currentBalance }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import AccountService from '@/services/AccountService';
export default {
  name: "CreateAccount",
  data() {
    return {
      accountRegistration: {
        accountNumber: "",
        accountType: "",
        userName: "",
        openingBalance: ""
      },
      accounts: [],
      responseSuccess: false
    };
  },
  methods: {
    readAccounts: async function() {
      const data = await AccountService.getAccounts();
      this.accounts = data;
    },
    createAccount: async function() {
      const requestData = {
        accountNumber: this.accountRegistration.accountNumber,
        accountType: this.accountRegistration.accountType,
        userName: this.accountRegistration.userName,
        openingBalance: this.accountRegistration.openingBalance
      };
      await AccountService.createAccount(requestData);
      this.accountRegistration.accountNumber = "";
      this.accountRegistration.accountType = "";
      this.accountRegistration.userName = "";
      this.accountRegistration.openingBalance = "";
      this.readAccounts();
      this.responseSuccess = true;
    },
  },
  mounted() {
    this.readAccounts();
  },
};
</script>