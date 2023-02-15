import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/App",
      name: "app",
      component: () => import("./App")
    },
     {
       path: "/account",
       name: "account",
       component: () => import("./components/CreateAccount")
     },
    {
      path: "/transactions",
      name: "transactions",
      component: () => import("./components/Transactions")
    }
  ]
});