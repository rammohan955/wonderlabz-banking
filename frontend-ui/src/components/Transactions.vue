<template>
<v-container>
    <h1>Transaction Management</h1>
    <p>Transaction List</p>
    <v-row>
      <v-col sm="12">
        <v-alert v-if="responseSuccess" dense text type="success">
          Your Transaction have been successfully.
        </v-alert>
      </v-col>
      <v-col sm="6">
        <h3>Transaction</h3>
        <v-text-field
          v-model="transferRequest.sourceAccountNumber"
          label="Account Number"
        ></v-text-field>
        <v-text-field
          v-model="transferRequest.targetAccountNumber"
          label="Account Type"
        ></v-text-field>
        <v-text-field
          v-model="transferRequest.amount"
          label="User Name"
        ></v-text-field>
        <v-btn color="primary" v-on:click="transferAmount">
          Transfer Amount
        </v-btn>
      </v-col>
      <v-col sm="6">
        <h3>Accounts</h3>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  Transaction ID
                </th>
                <th class="text-left">
                  Transaction Type
                </th>
                <th class="text-left">
                  Source Account Id
                </th>
                <th class="text-left">
                  Target Account Id
                </th>
                <th class="text-left">
                  Transaction Amount
                </th>
                <th class="text-left">
                  Transaction Date
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="transaction in transactions" :key="transaction.transactionId">
                <td>{{ transaction.transactionId }}</td>
                <td>{{ transaction.transactionType }}</td>
                <td>{{ transaction.sourceAccountId }}</td>
                <td>{{ transaction.targetAccountId }}</td>
                <td>{{ transaction.transactionAmount }}</td>
                <td>{{ transaction.transactionDateTime }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
 </v-container>
</template>
<script>
import TransactionService from "@/services/TransactionService";
export default {
  name: "TransactionDetails",
  data() {
    return {
      transferRequest: {
        sourceAccountNumber: "",
        targetAccountNumber: "",
        amount: ""
      },
      withdrawRequest: {
        sourceAccountNumber: "",
        amount: ""
      },
      depositRequest: {
        targetAccountNumber: "",
        amount: ""
      },
      transactions: [],
      responseSuccess: false
    };
  },
  methods: {
    getTransactions: async function() {
      const data = await TransactionService.getAllTransactions();
      this.transactions = data;
    },
    transferAmount: async function() {
       const requestData = {
         sourceAccountNumber: this.transferRequest.sourceAccountNumber,
         targetAccountNumber: this.transferRequest.targetAccountNumber,
         amount: this.transferRequest.amount
       };
       await TransactionService.makeTransfer(requestData);
       this.transferRequest.sourceAccountNumber = "";
       this.transferRequest.targetAccountNumber = "";
       this.transferRequest.amount = "";
       this.getTransactions();
       this.responseSuccess = true;
     },
  },
  mounted() {
    this.getTransactions();
  },
};
</script>