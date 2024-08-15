<template>
  <div class="login">
    <h2>登入</h2>
    <form @submit.prevent="login">
      <div>
        <label for="account">帳號：</label>
        <input v-model="account" id="account" placeholder="帳號" required />
      </div>
      <div>
        <label for="password">密碼：</label>
        <input
          v-model="password"
          type="password"
          id="password"
          placeholder="密碼"
          required
        />
      </div>
      <br />
      <button type="submit" class="login-button">登入</button>
    </form>
    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginPage",
  data() {
    return {
      account: "",
      password: "",
      error: "",
    };
  },
  methods: {
    async login() {
      try {
        const response = await axios.post("http://localhost:8080/api/login", {
          account: this.account,
          password: this.password,
        });
        console.log("後端回應：", response.data); // 打印後端回應

        if (response.data.status === "success") {
          localStorage.setItem("username", response.data.username);
          this.$router.push({
            name: response.data.role === "ADMIN" ? "AdminPage" : "UserPage",
          });
        } else {
          this.error = "帳號或密碼錯誤";
        }
      } catch (err) {
        console.error("登入失敗：", err);
        this.error = "登入失敗，請重試";
      }
    },
  },
};
</script>

<style scoped>
.login {
  max-width: 300px;
  margin: auto;
  padding: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.login-button {
  background-color: #214e34;
  border: none; /* 無邊框 */
  color: white; /* 白色文字 */
  padding: 10px 20px; /* 內間距 */
  text-align: center; /* 文字置中 */
  text-decoration: none; /* 無下劃線 */
  display: inline-block; /* 內聯區塊 */
  font-size: 16px; /* 字體大小 */
  margin-top: 10px; /* 上邊距 */
  cursor: pointer; /* 指針樣式 */
  border-radius: 4px; /* 圓角邊框 */
}

.error {
  color: red;
}
</style>
