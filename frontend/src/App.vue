<template>
  <nav class="navbar navbar-expand-lg" style="background-color: #0D6EFD;" data-bs-theme="dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Estacionamento</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link active" to="/">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link active" to="/about">About</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link active" to="/marca/lista">Marca</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  
  <router-view/>
</template>

<style lang="scss">

/** Style do Bootstrap */
$primary: #900;
$enable-shadows: true;
$prefix: "mo-";

@import "~/node_modules/bootstrap/scss/bootstrap";

/** Fim das configurações do Style do Bootstrap */

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

th {
  font-weight: normal !important;
}

hr {
  margin-top: -5px;
  margin-bottom: 0px;
}

.row{
  margin-top: 10px;
}
.container{
  margin-top: 10px;
  background: #ECECEC;
  padding: 20px;
}
</style>
