<template>
  <div>
    <div class="top-container">
      <div class="left">
        <span>
          课程名称
          <select v-model="courseName">
            <option
              v-for="(course, index) in courses"
              :key="index"
              :value="course.name"
            >
              {{ course.name }}
            </option>
          </select>
        </span>

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
          <th>目标编号</th>
          <th>实际总分</th>
          <th>目标总分</th>
          <th>达成度</th>
        </tr>

        <tr v-for="(totalAchievement, index) in totalAchievements" :key="index">
          <td>{{totalAchievement.targetNum}}</td>
          <td>{{totalAchievement.actualTotal}}</td>
          <td>{{totalAchievement.desTotal}}</td>
          <td>{{totalAchievement.res}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import { reqCourses, reqTotalAchievement, reqTargets } from "../api";

export default {
  data() {
    return {
      courses: [], //课程信息
      targets: [], //目标编号

      version: "",
      courseName: "",

      totalAchievements: []
    };
  },

  methods: {
    //获取课程信息
    async getCourses() {
      let result = await reqCourses();
      if (result.status === 200) {
        this.courses = result.data;
      }
    },

    //获取目标信息
    async getTargets() {
      let result = await reqTargets();
      if (result.status === 200) {
        this.targets = result.data;
      }
    },

    //查询
    async getTotalAchievements() {
      let result = await reqTotalAchievement(this.courseName, this.version);
      if (result.status === 200) {
        this.totalAchievements = result.data;
      }else{
        this.totalAchievements = '';
      }
    }
  },

  mounted() {
    this.getCourses();
    this.getTargets();
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
