<template>
  <div>
    <h2>新增投票項目</h2>
    <form @submit.prevent="createVoteItem">
      <input v-model="newVoteItem.name" placeholder="輸入投票項目名稱" />
      <button type="submit">新增</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      newVoteItem: {
        name: "",
      },
    };
  },
  methods: {
    createVoteItem() {
      axios
        .post("http://localhost:8080/api/vote-items", this.newVoteItem)
        .then((response) => {
          this.$emit("vote-item-created", response.data);
          this.newVoteItem.name = "";
        })
        .catch((error) => {
          console.error("Error creating vote item:", error);
        });
    },
  },
};
</script>
