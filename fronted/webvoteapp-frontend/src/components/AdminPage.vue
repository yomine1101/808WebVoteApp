<template>
  <div>
    <h2 class="title">管理人員編輯投票項目頁面</h2>

    <div>
      <h3>新增投票項目</h3>
      <input v-model="newVoteItemName" placeholder="投票項目名稱" />
      <button @click="createVoteItem" class="create-button">新增</button>
    </div>

    <br />
    <h3>以下為目前所有投票項目</h3>
    <table>
      <thead>
        <tr>
          <th class="name-column">項目名稱</th>
          <th class="action-column">編輯</th>
          <th class="action-column">刪除</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in voteItems" :key="item.id">
          <td>
            <span v-if="editingItem && editingItem.id === item.id">
              <input v-model="editingItem.name" />
              <button @click="updateVoteItem" class="update-button">
                保存
              </button>
              <button @click="cancelEdit" class="cancel-button">取消</button>
            </span>
            <span v-else>
              {{ item.name }}
            </span>
          </td>
          <td>
            <button @click="editVoteItem(item)" class="edit-button">
              編輯
            </button>
          </td>
          <td>
            <button @click="deleteVoteItem(item.id)" class="delete-button">
              刪除
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <br />
    <button @click="goToLogin" class="back-button">返回登入頁面</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AdminPage",
  data() {
    return {
      voteItems: [],
      newVoteItemName: "",
      editingItem: null,
    };
  },
  created() {
    this.fetchVoteItems();
  },
  methods: {
    async fetchVoteItems() {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/vote-items"
        );
        this.voteItems = response.data;
        console.log(this.voteItems);
      } catch (error) {
        console.error("查詢所有投票項目失敗：", error);
      }
    },
    async createVoteItem() {
      if (this.newVoteItemName.trim() === "") {
        alert("投票項目名稱不得為空");
        return;
      }
      try {
        await axios.post("http://localhost:8080/api/vote-items", {
          name: this.newVoteItemName,
        });
        this.newVoteItemName = "";
        this.fetchVoteItems();
      } catch (error) {
        console.error("新增投票項目失敗：", error);
      }
    },
    editVoteItem(item) {
      this.editingItem = { ...item };
    },
    async updateVoteItem() {
      try {
        await axios.put(
          `http://localhost:8080/api/vote-items/${this.editingItem.id}`,
          this.editingItem
        );
        this.editingItem = null;
        this.fetchVoteItems();
      } catch (error) {
        console.error("更新投票項目失敗：", error);
      }
    },
    cancelEdit() {
      this.editingItem = null;
    },
    async deleteVoteItem(id) {
      try {
        await axios.delete(`http://localhost:8080/api/vote-items/${id}`);
        this.fetchVoteItems();
      } catch (error) {
        console.error("刪除投票項目失敗：", error);
      }
    },
    goToLogin() {
      this.$router.push({ name: "LoginPage" });
    },
  },
};
</script>

<style scoped>
h2,
h3 {
  margin-bottom: 10px;
}

.title {
  border-bottom: 2px solid #ccc;
}

input {
  margin-right: 5px;
}

table {
  width: 70%;
  border-collapse: collapse;
  margin: 0 auto;
}

th,
td {
  padding: 10px;
  border: 1px solid #ccc;
}

th {
  background-color: #f2f2f2;
}
.name-column {
  width: 70%;
}

.action-column {
  width: 15%;
  text-align: center;
}
.create-button {
  padding: 5px 20px;
  background-color: #4e8a6b;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.create-button:hover {
  background-color: #448660;
}

.edit-button {
  padding: 5px 20px;
  background-color: #214e34;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.edit-button:hover {
  background-color: #448660;
}

.delete-button {
  padding: 5px 20px;
  background-color: #f03a47;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.delete-button:hover {
  background-color: #fd8d95;
}

.update-button {
  padding: 5px 20px;
  background-color: #3a624a;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-right: 15px;
}

.cancel-button {
  padding: 5px 20px;
  background-color: #f45560;
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

.back-button:hover {
  background-color: #9c9c9c;
}
</style>
