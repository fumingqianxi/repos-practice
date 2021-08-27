<template>
  <div id="app">
    <!-- <div id="nav">
      <Menu>
        <MenuItem name="home" to="/home">Home</MenuItem>
        <MenuItem name="about" to="/about">About</MenuItem>
        <MenuItem name="login" to="/login">login</MenuItem>
        <MenuItem name="admin" to="/admin">admin</MenuItem>
      </Menu>
    </div> -->
    <router-view/>
  </div>
</template>

<script>
  export default {
    watch: {
      '$route'() {
        console.log(this.$route);
      }
    },
    mounted() {
      
    },
    methods: {
      // handleSelect(name) {
      //   this.$router.push(name);
      // }
    }
  }
</script>

<style>
/* #app { 
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
} */
  body {
    height: 5000px;
    background: #f8f8f9 !important;
  }
</style>
