<template>
  <div>
    <div class="top-container">
      <div class="left">
        <span>
          版本
          <input type="text" v-model="version" />
        </span>

        <span>
          <button @click="getTotalAchievements">查询</button>
        </span>
      </div>
    </div>

    <!-- 查询，显示课程总体达成度 -->
    <div class="main">
      <table>
        <tr>
          <th>指标编号</th>
          <th>达成度</th>
        </tr>

        <tr v-for="(graduateRequirement, index) in graduateRequirements" :key="index">
          <td>{{graduateRequirement.idcNum}}</td>
          <td>{{graduateRequirement.graduateDachengdu}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import { reqGraduateRequirement } from "../api";

export default {
  data() {
    return {
      version: "",

      graduateRequirements: []
    };
  },

  methods: {
    //查询
    async getTotalAchievements() {
      let result = await reqGraduateRequirement(this.version);
      if (result.status === 200) {
        this.graduateRequirements = result.data;
      }else{
        this.graduateRequirements = '';
      }
    }
  }
};
</script>

<style scope>
.top-container {
  width: 1000px;
  height: 60px;
  margin: 10px auto;
}

.left {
  float: left;
}

.left span {
  margin-right: 20px;
}

.left span select {
  width: 150px;
  border: 1px solid #ccc;
  height: 25px;
  outline: none;
}

.left span input {
  border: 1px solid #ccc;
  height: 20px;
  padding: 3px;
  width: 50px;
  text-align: center;
  outline: none;
}

.left span button {
  border: none;
  color: white;
  background: #2795e9;
  outline: none;
  height: 25px;
  width: 50px;
}

.right {
  float: right;
}

.main {
  width: 580px;
  margin: 0px auto;
}

.main table {
  width: 580px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.main tr {
  text-align: center;
}

.main tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.main th {
  font-weight: bolder;
  padding: 10px 15px;
  background-color: #2795e9;
  color: white;
}

.main td {
  padding: 8px;
  text-align: center;
}

.main td input.weight {
  border: 1px solid #ccc;
  height: 20px;
  padding: 3px;
  width: 50px;
  text-align: center;
  outline: none;
}

.main td input.examDetail {
  width: 150px;
  outline: none;
  border: 1px solid #ccc;
  height: 20px;
  padding: 3px;
}

.main td textarea {
  padding: 5px;
  border: 1px solid #ccc;
  outline: none;
}

.save {
  text-align: center;
  margin-top: 20px;
}

.save button {
  border: none;
  color: white;
  background: #2795e9;
  outline: none;
  height: 25px;
  width: 50px;
}

.save button:hover {
  background: #65b1eb;
}
</style>
