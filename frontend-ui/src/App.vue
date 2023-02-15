<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Wonderlabz Banking App"/>

  <div id="app">
      <nav class="navbar navbar-expand navbar-dark bg-dark">
        <router-link to="/" class="navbar-brand">bezKoder</router-link>
        <div class="navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/account" class="nav-link">Create Account</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/transactions" class="nav-link">Transactions</router-link>
          </li>
        </div>
      </nav>

      <div class="container mt-3">
        <router-view />
      </div>
    </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
