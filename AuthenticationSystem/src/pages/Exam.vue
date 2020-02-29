<template>
  <div>
    <div class="top-container">
      <div class="left">
        <span>
          课程名称
          <select v-model="courseId">
            <option
              v-for="(course, index) in courses"
              :key="index"
              :value="course.id"
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
          <button @click="getExam">查询</button>
        </span>
      </div>

      <div class="right">
        <el-button
          type="primary"
          icon="el-icon-plus"
          class="add-btn"
          @click="clickAddBtn"
          >添加</el-button
        >
      </div>
    </div>

    <!-- 查询，显示详细考核信息 -->
    <div class="main" v-if="isAllShow">
      <table>
        <tr v-if="isTitleShow">
          <th>考核方式</th>
          <th>权重</th>
        </tr>

        <tr v-for="(exam, index) in exams.listExam" :key="index">
          <td>{{ exam.examtype.name }}</td>
          <td>{{ exam.ratio }}</td>
        </tr>
      </table>
    </div>

    <!-- 添加 -->
    <div class="main" v-if="isAddShow">
      <table>
        <tr>
          <th>考核方式</th>
          <th>权重</th>
        </tr>

        <tr v-for="(examType, index) in examTypes" :key="index">
          <td>{{ examType.name }}</td>
          <td>
            <input
              type="text"
              v-model="radios[index]"
            />
          </td>
        </tr>
      </table>

      <div class="save"><button @click="saveAdd">保存</button></div>
    </div>
  </div>
</template>

<script>
import { reqCourses, reqListExams, reqExamTypes, reqAddExams } from "../api";

export default {
  data() {
    return {
      isAllShow: true,
      isTitleShow: true,
      isAddShow: false,

      courseId: "",
      version: "",
      examTypeId: "",

      courses: [], //获取课程信息
      exams: [], //考核信息
      examTypes: [],
      radios: [],
      examTypeIds: [1,2,3,4]
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

    //查询，列出所有考核方式和权重
    async getExam() {
      let result = await reqListExams(this.courseId, this.version);
      if (result.status === 200) {
        this.exams = result.data;
      } else {
        // alert(result.msg);
        this.exams = "";
      }

      this.isAllShow = true;
      this.isAddShow = false;
    },

    //获取考核方式
    async getExamTypes() {
      let result = await reqExamTypes();
      if (result.status === 200) {
        this.examTypes = result.data;
      }
    },

    //点击添加按钮
    clickAddBtn() {
      this.isAllShow = false;
      this.isAddShow = true;

      this.getExamTypes();
    },

    //获取考核方式id
    /* getExamTypeId(examTypeId) {
      this.examTypeId = examTypeId;
      this.examTypeIds.push(this.examTypeId);
      //如果当前input值为"",除去当前的examTypeId
    //   for (let i = 0; i < this.radios.length; i++) {
    //     if (this.radios[i] == "" || this.radios[i] == null) {
    //       this.examTypeIds.splice(i, 1);
    //     }
    //   }
    }, */

    //保存添加的数据
    async saveAdd() {
      let examTypeIdList = JSON.stringify(this.examTypeIds);
      let ratioList = JSON.stringify(this.radios);

      let result = await reqAddExams(
        this.courseId,
        this.version,
        examTypeIdList,
        ratioList
      );
      if (result.status === 200) {
        alert("添加成功");
      } else {
        alert(result.msg);
      }
    }
  },

  mounted() {
    this.getCourses();
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
}

.main td input {
  border: 1px solid #ccc;
  height: 20px;
  padding: 3px;
  width: 50px;
  text-align: center;
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
