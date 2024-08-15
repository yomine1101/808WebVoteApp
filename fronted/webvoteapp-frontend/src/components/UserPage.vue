<template>
  <div>
    <h2 class="title">使用者投票頁面</h2>

    <form @submit.prevent="submitVotes">
      <table class="vote-table">
        <thead>
          <tr>
            <th>選擇</th>
            <th>項目名稱</th>
            <th>當前得票數</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in voteItems" :key="item.id">
            <td>
              <input
                type="checkbox"
                :value="item.id"
                v-model="selectedVoteItems"
              />
            </td>
            <td>{{ item.name }}</td>
            <td>{{ item.voteCount }}</td>
          </tr>
        </tbody>
      </table>
      <div>
        <button type="submit" class="submit-button">提交投票</button
        ><br /><br />
        <button @click="goToLogin" class="back-button">返回登入頁面</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserPage",
  data() {
    return {
      voteItems: [],
      selectedVoteItems: [],
      username: null,
    };
  },
  created() {
    this.fetchVoteItems();
    this.getUsername();
  },
  methods: {
    async fetchVoteItems() {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/vote-items/vote-counts"
        );
        this.voteItems = response.data;
      } catch (error) {
        console.error("投票失敗：", error);
      }
    },
    getUsername() {
      this.username = localStorage.getItem("username");
      if (!this.username) {
        // 如果沒有找到用戶名，可能需要重定向到登錄頁面
        this.$router.push({ name: "LoginPage" });
      }
    },
    async submitVotes() {
      console.log("提交的數據：", {
        username: this.username,
        selectedVoteItems: this.selectedVoteItems,
      });
      console.log("當前使用者：", this.username);
      if (!this.username) {
        alert("帳號未設置，請重新登入");
        return;
      }
      if (this.selectedVoteItems.length === 0) {
        alert("請至少選擇一個項目");
        return;
      }

      try {
        for (const itemId of this.selectedVoteItems) {
          const response = await axios.post("http://localhost:8080/api/votes", {
            username: this.username,
            voteItem: { id: itemId },
          });
          console.log("單次投票回應：", response.data);
        }

        alert("投票成功");
        this.selectedVoteItems = [];
        this.fetchVoteItems();
      } catch (error) {
        console.error("提交投票失敗：", error.response?.data || error.message);
        alert("提交投票失敗，請重試");
      }
    },
    goToLogin() {
      this.$router.push({ name: "LoginPage" });
    },
  },
};
</script>

<style scoped>
h2 {
  margin-bottom: 20px;
}
.title {
  border-bottom: 2px solid #ccc;
}

.vote-table {
  width: 70%;
  margin: 0 auto;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.vote-table th,
.vote-table td {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: center;
}

.vote-table th {
  background-color: #f2f2f2;
}

.submit-button {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #214e34;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.back-button {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #cdcdcd;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #448660;
}

.back-button:hover {
  background-color: #9c9c9c;
}
</style>
