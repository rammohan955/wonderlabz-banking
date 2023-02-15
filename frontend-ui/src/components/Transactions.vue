<template>
<v-container>
    <h1>Transaction Management</h1>
    <p>Transaction List</p>

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
  },
  mounted() {
    this.getTransactions();
  },
};
</script>