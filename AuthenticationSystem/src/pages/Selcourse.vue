<template>
  <div class="course">
    <div class="search-add" v-if="isCourseSearchBtn">
      <div class="search">
        <input
          type="text"
          placeholder="输入课程名按回车搜索"
          @keyup.enter="searchCourseNames"
          v-model="courseName"
        />
      </div>
    </div>

    <!-- 展示所有的课程名 -->
    <div class="course-list" v-if="isCourseList">
      <table>
        <tr>
          <th>课程名</th>
          <th>版本</th>
        </tr>
        <tr
          v-for="(course, index) in courses"
          :key="index"
          @click="getCourseId(course.id, course.name)"
        >
          <td>{{ course.name }}</td>
          <td>{{ course.version }}</td>
        </tr>
      </table>
    </div>

    <!-- 搜索到的课程名 -->
    <div class="course-list" v-if="isCourseSearch">
      <table>
        <tr>
          <th>课程名</th>
          <th>版本</th>
        </tr>
        <tr
          v-for="(searchCoursesMsg, index) in searchCoursesMsgs"
          :key="index"
          @click="getCourseId(searchCoursesMsg.id, searchCoursesMsg.name)"
        >
          <td>{{ searchCoursesMsg.name }}</td>
        </tr>
      </table>
    </div>

    <!-- 课程里面的详细信息 -->
    <div class="course-detail" v-if="isCourseDetail">
      <div class="detail-left">
        <p>{{ courseName }}</p>

        <div>
          <p>课程负责人</p>
          <input
            type="text"
            v-model="selCourses.leadName"
            style="text-align:center"
          />
        </div>

        <div>
          <p>授课老师</p>
          <select v-model="teacherId">
            <option></option>
            <option
              v-for="(teacherName, index) in teacherNames"
              :key="index"
              :value="teacherName.id"
            >
              {{ teacherName.name }}
            </option>
          </select>
        </div>

        <div>
          <p>学年</p>
          <input type="text" v-model="version" style="text-align:center" />
        </div>

        <div>
          <p>学期</p>
          <select v-model="term">
            <option value="1">1</option>
            <option value="2">2</option>
          </select>
        </div>

        <div @click="getStudentDetail">
          <button
            style="width:50px;height:25px;background:#2795e9;border:none;outline:none;color:white"
          >
            提交
          </button>
        </div>
      </div>

      <div class="detail-right">
        <el-button
          type="primary"
          icon="el-icon-plus"
          class="add-btn"
          @click="getAddStudentDetail"
          >添加</el-button
        >

        <div class="student-list" v-if="isStudentDetail">
          <table>
            <tr>
              <th>学号</th>
              <th>姓名</th>
              <th>专业</th>
              <th>班级</th>
              <th>年级</th>
              <th>操作</th>
            </tr>

            <tr v-for="(selCourse, index) in selCourses.students" :key="index">
              <td>{{ selCourse.studentNum }}</td>
              <td>{{ selCourse.name }}</td>
              <td>{{ selCourse.major }}</td>
              <td>{{ selCourse.className }}</td>
              <td>{{ selCourse.grade }}</td>
              <td>
                <button class="delete" @click="getThisStudentId(selCourse.id)">
                  删除
                </button>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>

    <!-- 添加学生 -->
    <div class="alert-add" v-if="isCourseDetailAdd">
      <div class="add-detail">
        <div class="cancel" @click="isCourseDetailAdd = false">×</div>

        <div class="save">
          <button @click="saveStudent">保存</button>
          <input
            type="text"
            placeholder="输入年级回车查找"
            class="grade-input"
            v-model="grade"
            @keyup.enter="enterStudentDetail"
          />
        </div>

        <div class="student-list">
          <table>
            <tr>
              <th>选择</th>
              <th>学号</th>
              <th>姓名</th>
              <th>专业</th>
              <th>班级</th>
              <th>年级</th>
            </tr>

            <tr v-for="(student, index) in students" :key="index">
              <td>
                <input type="checkbox" @click="getStudentId(student.id)" />
              </td>
              <td>{{ student.studentNum }}</td>
              <td>{{ student.name }}</td>
              <td>{{ student.major }}</td>
              <td>{{ student.className }}</td>
              <td>{{ student.grade }}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <!-- 删除学生 -->
    <div class="alert-delete" v-if="isCourseDetailDelete">
      <div class="delete-detail">
        <div class="cancel" @click="isCourseDetailDelete = false">×</div>

        <div>
          <form @submit.prevent="deleteThisStudent">
            <p>确定要删除这个学生吗？</p>
            <p>
              <button @click="isCourseDetailDelete = false">取消</button>
              <button>确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  reqCourses,
  reqListAllTeacherSelcourse,
  reqClickTeacherSelcourse,
  reqSaveStudent,
  reqDeleteThisStudent
} from "../api";
import { mapState } from "vuex";

export default {
  data() {
    return {
      isCourseList: true,
      isCourseSearch: false,
      isCourseSearchBtn: true,

      isCourseDetail: false,
      isCourseDetailAdd: false,
      isCourseDetailDelete: false,
      isStudentDetail: false,

      courses: [], //所有课程名

      courseId: "", //课程名id
      courseName: "", //课程名

      term: "", //学期
      teacherId: "",
      version: "",

      selCourses: [], //展示的学生信息
      students: [], //添加学生时学生信息
      grade: "", //年级
      studentId: "", //当前学生id
      studentIds: [] //选择的学生id

      // selStudentId:''
    };
  },

  methods: {
    //获取所有课程名
    async getCourseNames() {
      let result = await reqCourses();
      this.courses = result.data;
    },

    //获取当前课程id，课程名
    getCourseId(courseId, courseName) {
      this.courseId = courseId;
      this.courseName = courseName;

      this.isCourseList = false;
      this.isCourseSearch = false;
      this.isCourseSearchBtn = false;

      this.isCourseDetail = true;
      this.isStudentDetail = true;
    },

    //搜索课程名
    searchCourseNames() {
      this.isCourseList = false;
      const { courseName } = this;
      if (courseName) {
        this.$store.dispatch("searchCourses", courseName);
        this.isCourseSearch = true;
      }
    },

    //获取学生详细信息
    async getStudentDetail() {
      let result;
      result = await reqListAllTeacherSelcourse(
        this.courseId,
        this.teacherId,
        this.term,
        this.version,
        this.selCourses.leadName
      );
      if (result.status === 200) {
        this.selCourses = result.data;
      } else {
        alert(result.msg);
      }
    },

    //点击添加，获取所有学生信息
    async getAddStudentDetail() {
      let result = await reqClickTeacherSelcourse(this.grade);
      this.students = result.data;
      this.isCourseDetailAdd = true;
    },

    //回车查询所有学生信息
    async enterStudentDetail() {
      console.log(this.grade);
      let result = await reqClickTeacherSelcourse(this.grade);
      if(result.status === 200){
        this.students = result.data;
      }else{
        this.students = ""
      }
    },

    //添加时，点击获取当前学生id
    getStudentId(studentId) {
      this.studentId = studentId;
      console.log(this.studentId);
      this.studentIds.push(this.studentId);
    },

    //保存选中的学生
    async saveStudent() {
      let studentIdList = JSON.stringify(this.studentIds);
      let result = await reqSaveStudent(
        this.courseId,
        this.teacherId,
        this.selCourses.leadId,
        this.term,
        this.version,
        studentIdList,
        this.selCourses.leadName
      );
      if (result.status === 200) {
        alert(result.msg);
      } else {
        alert(result.msg);
      }
    },

    //删除时，获取当前id
    getThisStudentId(studentId) {
      this.studentId = studentId;
      this.isCourseDetailDelete = true;
    },

    //删除当前学生
    async deleteThisStudent() {
      let result = await reqDeleteThisStudent(
        this.studentId,
        this.courseId,
        this.version
      );
      if (result.status === 200) {
        alert(result.msg);
      } else {
        alert("删除失败");
      }
      this.isCourseDetailDelete = false;
    }
  },

  mounted() {
    this.getCourseNames();
  },

  created() {
    this.$store.dispatch("getTeacherNames");
  },

  computed: {
    ...mapState(["searchCoursesMsgs"]),
    ...mapState(["teacherNames"])
  }
};
</script>

<style scoped>
.course {
  width: 1000px;
  margin: 0 auto;
}

.search-add .search {
  float: left;
  margin-bottom: 20px;
}

.search-add .search input {
  border: 1px solid #ccc;
  padding: 5px;
  outline: none;
}

.search-add .search input::placeholder {
  font-size: 14px;
}

.course-list {
  width: 900px;
  margin: 20px auto 0px;
}

.course-list table {
  width: 900px;
  border: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.course-list tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.course-list tr:hover {
  background: #3f99df;
  cursor: pointer;
}

.course-list th {
  font-weight: bolder;
  padding: 10px 15px;
  background-color: #2795e9;
  color: white;
}

.course-list td {
  padding: 8px;
}

.course-list td button {
  outline: none;
  border: none;
  background: rgb(243, 239, 239);
}

.course-list td button.edit {
  color: rgb(15, 221, 125);
}

.alert-search-course {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1000;
}

.search-list {
  position: relative;
  width: 900px;
  padding: 50px;
  top: 15%;
  left: 20%;
  border: 1px solid #ccc;
  border-radius: 15px;
  background: white;
  box-shadow: 2px 2px 30px #909090;
}

.search-list .search-input,
.search-list .search-none {
  text-align: center;
}

.search-list input {
  outline: none;
  border: 1px solid rgb(204, 201, 201);
  border-radius: 3px;
  height: 20px;
  padding: 5px;
  width: 300px;
}

.search-list input::placeholder {
  font-size: 15px;
}

.search-list button {
  outline: none;
  border: none;
  height: 30px;
  background: yellow;
  width: 50px;
  color: red;
  font-size: 16px;
  border-radius: 3px;
}

.search-list button:hover {
  color: rgb(209, 43, 43);
}

.course-detail {
  background: rgb(230, 224, 224);
  width: 1000px;
  margin: 20px auto 0;
  font-size: 16px;
}

.detail-left {
  width: 80px;
  text-align: center;
  float: left;
}

.detail-left > div {
  margin-top: 35px;
}

.detail-left > div input {
  width: 70px;
  margin-top: 6px;
  border: 1px solid #ccc;
  outline: none;
  padding: 3px;
}

.detail-left > div select {
  width: 80px;
  margin-top: 6px;
  border: 1px solid #ccc;
  outline: none;
  padding: 3px;
}

.detail-right {
  float: right;
}

.add-btn {
  float: right;
  margin-bottom: 20px;
}

.student-list {
  width: 850px;
  margin-top: 20px;
}

.student-list table {
  width: 850px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.student-list tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.student-list th {
  font-weight: bolder;
  padding: 10px 20px;
  background-color: #2795e9;
  color: white;
}

.student-list td {
  padding: 10px 15px;
}

.student-list td button {
  outline: none;
  border: none;
  background: rgb(243, 239, 239);
}

.student-list td button.edit {
  color: rgb(15, 221, 125);
}

.student-list td button.delete {
  color: red;
}

.student-list td button:hover {
  background: white;
}

.alert-add {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1000;
}

.alert-add .add-detail {
  position: relative;
  width: 850px;
  padding: 50px;
  top: 8%;
  left: 20%;
  border: 1px solid #ccc;
  border-radius: 15px;
  background: white;
  box-shadow: 2px 2px 30px #909090;
}

.alert-add .add-detail .save button {
  background: yellow;
  border: none;
  border: 1px solid rgb(238, 238, 56);
  outline: none;
  width: 60px;
  height: 26px;
}

.alert-add .add-detail .save button:hover {
  background: rgb(238, 238, 56);
}

.grade-input {
  margin-left: 20px;
  border: 1px solid #ccc;
  height: 20px;
  padding: 2px;
  width: 150px;
  text-align: center;
}

.grade-input::placeholder {
  font-size: 14px;
}

.alert-delete {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-delete .delete-detail {
  position: relative;
  width: 500px;
  padding: 20px;
  top: 30%;
  left: 33%;
  border: 1px solid #ccc;
  border-radius: 15px;
  background: white;
  box-shadow: 2px 2px 30px #909090;
}

.alert-delete .delete-detail form {
  margin: 10px auto 20px;
  width: 210px;
}

.alert-delete .delete-detail p {
  padding: 8px;
  text-align: center;
}

.alert-delete .delete-detail p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
}

.alert-delete .delete-detail p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-delete .delete-detail p button:last-child {
  float: right;
  background: red;
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
</style>
