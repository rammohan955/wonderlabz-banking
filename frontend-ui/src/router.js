import Vue from 'vue'
import VueRouter from 'vue-router'
import CreateAccount from '@/components/CreateAccount.vue'
import Transactions from '@/components/Transactions.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'App',
    component: CreateAccount
  },
  {
    path: '/account',
    name: 'CreateAccount',
    component: CreateAccount
  },
  {
    path: '/transactions',
    name: 'Transactions',
    component: Transactions
  }
]

const router = new VueRouter({
  routes
})

export default router