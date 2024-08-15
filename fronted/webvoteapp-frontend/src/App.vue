<template>
  <div id="app">
    <header>
      <h1>線上投票系統</h1>
    </header>

    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
#app {
  font-family: Arial, sans-serif;
  text-align: center;
  margin: 20px;
}

header {
  background-color: #011638;
  padding: 20px;
  margin-bottom: 20px;
  color: white;
}

main {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
</style>
