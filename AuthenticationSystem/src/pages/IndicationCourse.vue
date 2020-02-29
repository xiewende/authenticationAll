<template>
  <div>
    <!-- 添加按钮 -->
    <div>
      <el-button
        type="primary"
        icon="el-icon-plus"
        class="add-btn"
        v-if="isAddBtn"
        @click="addShow"
        >添加</el-button
      >
    </div>

    <!-- 详细信息 -->
    <div class="main">
      <div class="main-left">
        <table>
          <tr>
            <th>指标点</th>
          </tr>
          <tr v-for="(indication, index) in indications" :key="index">
            <td
              @click="getIndicationId(indication.id)"
              :class="{ active: isCurrentIndicationId(indication.id) }"
            >
              {{ indication.content }}
            </td>
          </tr>
        </table>
      </div>

      <div class="main-right">
        <table>
          <tr>
            <th>课程名</th>
            <th>权重</th>
          </tr>
          <tr
            v-for="(indication,
            index) in indicationCourses.indicationCourseList"
            :key="index"
          >
            <td>{{ indication.course.name }}</td>
            <td>{{ indication.weight }}</td>
          </tr>
        </table>
      </div>
    </div>

    <!-- 添加框 -->
    <div class="alert-add" v-if="isAdd">
      <div class="add-list">
        <div class="cancel" @click="isAdd = false">×</div>

        <div class="add">
          <button @click="AddIndicationCourse">添加</button>
        </div>

        <div class="main-right">
          <table>
            <tr>
              <th>课程名</th>
              <th>权重</th>
            </tr>
            <tr v-for="(course, index) in courses" :key="index">
              <td>{{ course.name }}</td>
              <td>
                <input
                  v-on:blur="getCourseId(course.id)"
                  type="text"
                  v-model="weights[index]"
                />
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  reqListIndications,
  reqCourses,
  reqAddIndicationCourse,
  reqListAllIndicationCourseByIndicationId
} from "../api/index";

export default {
  data() {
    return {
      isAdd: false,
      isAddBtn: false,
      isIndicationCourse: false,

      version: "",
      indicationId: "",
      weight: "",
      courseId: "",

      courseIds: [], //课程id
      weights: [], //权重

      indications: [], //指标信息
      courses: [], //课程信息
      indicationCourses: []
    };
  },

  methods: {
    //列出所有指标信息
    async getIndications() {
      const { version } = this;
      let result = await reqListIndications({ version });
      if (result.status === 200) {
        this.indications = result.data;
      }
    },

    //列出所有的课程信息
    async getCourses() {
      let result = await reqCourses();
      if (result.status === 200) {
        this.courses = result.data;
      }
    },

    //获取课程名和权重
    async indicationCourseByIndicationId(indicationId) {
      let result = await reqListAllIndicationCourseByIndicationId(indicationId);
      if (result.status === 200) {
        console.log(result.data.version);
        this.indicationCourses = result.data;
      }else{
        this.indicationCourses = '';
      }
    },

    //添加
    async AddIndicationCourse() {

      let courseIdList = JSON.stringify(this.courseIds);
      let weightList = JSON.stringify(this.weights);

      console.log(this.weights);
      console.log(this.courseIds);

      let result;
      result = await reqAddIndicationCourse(
        this.indicationId,
        courseIdList,
        weightList
      );
      if (result.status === 200) {
        alert("添加成功");
        this.isAdd = false;
      } else {
        alert(result.msg);
      }
    },

    //获取指标id
    getIndicationId(indicationId) {
      this.indicationId = indicationId;
      this.indicationCourseByIndicationId(this.indicationId);
      this.isAddBtn = true;
    },

    //获取课程id
    getCourseId(courseId) {
      this.courseId = courseId;
      this.courseIds.push(this.courseId);
    },

    isCurrentIndicationId(indicationId) {
      return this.indicationId == indicationId;
    },

    addShow() {
      this.isAdd = true;
      this.getCourses();
    }
  },

  mounted() {
    this.getIndications();
  }
};
</script>

<style scoped>
.add-btn {
  float: left;
}

.main {
  width: 960px;
  background: rgb(241, 233, 233);
  margin: 30px auto 0px;
  text-align: center;
}

.main .main-left {
  float: left;
}

.main .main-left table {
  border-collapse: collapse;
  width: 300px;
}

.main .main-left table td {
  border: 1px solid #ccc;
  padding: 10px 20px;
}

.main .main-left table th {
  background: #2795e9;
  padding: 10px 20px;
  border: 1px solid #ccc;
  color: white;
  font-weight: bolder;
}

.main .main-left table tr:hover {
  background: rgb(238, 238, 56);
  cursor: pointer;
}

.main .main-right {
  float: right;
}

.main-right table {
  width: 400px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.main-right tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.main-right table td {
  padding: 10px 20px;
  cursor: pointer;
}

.main-right th {
  font-weight: bolder;
  padding: 10px 20px;
  background-color: #2795e9;
  color: white;
}

.main-right table td input {
  border: 1px solid #ccc;
  width: 50px;
  height: 18px;
  padding: 2px;
  text-align: center;
  outline: none;
}

.alert-add {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1000;
}

.alert-add .add-list {
  position: relative;
  width: 500px;
  padding: 30px;
  top: 8%;
  left: 40%;
  border: 1px solid #ccc;
  border-radius: 15px;
  background: white;
  box-shadow: 2px 2px 30px #909090;
}

.alert-add .add-list .add button {
  background: yellow;
  border: none;
  border: 1px solid rgb(238, 238, 56);
  outline: none;
  width: 60px;
  height: 26px;
  margin-bottom: 20px;
}

.alert-add .add-list .add button:hover {
  background: rgb(238, 238, 56);
}

.cancel {
  margin: 0;
  padding: 0;
  border: 0;
  width: 30px;
  height: 30px;
  background-color: red;
  border-radius: 50%;
  color: white;
  line-height: 30px;
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
  overflow: hidden;
  font-size: 42px;
  position: absolute;
  top: 0%;
  right: 0%;
  cursor: pointer;
}

.cancel:hover {
  background-color: rgb(204, 44, 44);
}

.active {
  background-color: rgb(238, 238, 56);
}
</style>
