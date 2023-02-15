<template>
<v-container>
    <h1>Transaction Management</h1>
    <v-row>
      <v-col sm="12">
        <v-alert v-if="responseSuccess" dense text type="success">
          Your Transaction have been successfully.
        </v-alert>
      </v-col>
      <v-col sm="6">
        <h3>Transfer Amount</h3>
        <v-text-field
          v-model="transferRequest.sourceAccountNumber"
          label="Source Account Number"
        ></v-text-field>
        <v-text-field
          v-model="transferRequest.targetAccountNumber"
          label="Target Account Number"
        ></v-text-field>
        <v-text-field
          v-model="transferRequest.amount"
          label="Amount"
        ></v-text-field>
        <v-btn color="primary" v-on:click="transferAmount">
          Transfer Amount
        </v-btn>
        <h3>Withdraw Amount</h3>
        <v-text-field
          v-model="withdrawRequest.sourceAccountNumber"
          label="Source Account Number"
        ></v-text-field>
        <v-text-field
          v-model="withdrawRequest.amount"
          label="Amount"
        ></v-text-field>
        <v-btn color="primary" v-on:click="withdrawAmount">
          Withdraw Amount
        </v-btn>
        <h3>Deposit Amount</h3>
        <v-text-field
          v-model="depositRequest.targetAccountNumber"
          label="Target Account Number"
        ></v-text-field>
        <v-text-field
          v-model="depositRequest.amount"
          label="Amount"
        ></v-text-field>
        <v-btn color="primary" v-on:click="depositAmount">
          Deposit Amount
        </v-btn>
      </v-col>
      <v-col sm="6">
        <h3>Transaction History</h3>
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
    withdrawAmount: async function() {
       const requestData = {
         sourceAccountNumber: this.withdrawRequest.sourceAccountNumber,
         amount: this.withdrawRequest.amount
       };
       await TransactionService.withdraw(requestData);
       this.withdrawRequest.sourceAccountNumber = "";
       this.withdrawRequest.amount = "";
       this.getTransactions();
       this.responseSuccess = true;
     },
    depositAmount: async function() {
       const requestData = {
         sourceAccountNumber: this.depositRequest.targetAccountNumber,
         amount: this.depositRequest.amount
       };
       await TransactionService.withdraw(requestData);
       this.depositRequest.targetAccountNumber = "";
       this.depositRequest.amount = "";
       this.getTransactions();
       this.responseSuccess = true;
     },
  },
  mounted() {
    this.getTransactions();
  },
};
</script>