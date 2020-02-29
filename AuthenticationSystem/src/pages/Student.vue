<template>
  <div>
    <!-- 搜索和添加按钮 -->
    <div>
      <el-button
        type="primary"
        icon="el-icon-search"
        class="search-btn"
        @click="isSearchStudent = true"
        >搜索</el-button
      >

      <input
        type="text"
        v-model="grade"
        class="grade"
        placeholder="输入年级"
        @keyup.enter="searchByGrade"
      />

      <el-button
        type="primary"
        icon="el-icon-plus"
        class="add-btn"
        @click="isAddStudent = true"
        >添加</el-button
      >
    </div>

    <!-- 展示所有课程 -->
    <div class="list-student">
      <table>
        <tr>
          <th>学号</th>
          <th>学生姓名</th>
          <th>专业</th>
          <th>班级</th>
          <th>年级</th>
          <th>操作</th>
        </tr>
        <tr v-for="(studentMsg, index) in studentMsgs.list" :key="index">
          <td>{{ studentMsg.studentNum }}</td>
          <td>{{ studentMsg.name }}</td>
          <td>{{ studentMsg.major }}</td>
          <td>{{ studentMsg.className }}</td>
          <td>{{ studentMsg.grade }}</td>
          <td>
            <button
              class="update"
              @click="
                getThisStudentMsg(
                  studentMsg.id,
                  studentMsg.studentNum,
                  studentMsg.name,
                  studentMsg.major,
                  studentMsg.className,
                  studentMsg.grade
                )
              "
            >
              编辑
            </button>
            <button class="delete" @click="getStudentId(studentMsg.id)">
              删除
            </button>
          </td>
        </tr>
      </table>
    </div>

    <!-- 分页 -->
    <div class="pagination">
      <span @click="prePage"> &laquo; </span>
      <span>{{ pageNum }}</span>
      <span @click="nextPage"> &raquo; </span>
    </div>

    <!-- 添加课程框 -->
    <div class="alert-add-student" v-if="isAddStudent">
      <div class="add-student">
        <div class="cancel" @click="isAddStudent = false">×</div>
        <div>
          <form>
            <table>
              <tr>
                <td>学号：</td>
                <td><input type="text" v-model="studentNum" /></td>
              </tr>
              <tr>
                <td>学生姓名：</td>
                <td><input type="text" v-model="name" /></td>
              </tr>
              <tr>
                <td>专业：</td>
                <td><input type="text" v-model="major" /></td>
              </tr>
              <tr>
                <td>班级：</td>
                <td><input type="text" v-model="className" /></td>
              </tr>
              <tr>
                <td>年级：</td>
                <td><input type="text" v-model="grade" /></td>
              </tr>
            </table>

            <p>
              <button @click="isAddStudent = false">取消</button>
              <button @click="addStudent">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 删除学生框 -->
    <div class="alert-delete-student" v-if="isDeleteStudent">
      <div class="delete-student">
        <div class="cancel" @click="isDeleteStudent = false">×</div>
        <div>
          <form>
            <p>确定要删除这个学生吗？</p>
            <p>
              <button @click="isDeleteStudent = false">取消</button>
              <button @click="deleteStudent">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 编辑学生框 -->
    <div class="alert-add-student" v-if="isUpdateStudent">
      <div class="add-student">
        <div class="cancel" @click="isUpdateStudent = false">×</div>
        <div>
          <form @submit.prevent="updateStudent">
            <table>
              <tr>
                <td>学号：</td>
                <td><input type="text" v-model="studentNum" /></td>
              </tr>
              <tr>
                <td>学生姓名：</td>
                <td><input type="text" v-model="name" /></td>
              </tr>
              <tr>
                <td>专业：</td>
                <td><input type="text" v-model="major" /></td>
              </tr>
              <tr>
                <td>班级：</td>
                <td><input type="text" v-model="className" /></td>
              </tr>
              <tr>
                <td>年级：</td>
                <td><input type="text" v-model="grade" /></td>
              </tr>
            </table>

            <p>
              <button @click="isUpdateStudent = false">取消</button>
              <button>确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 搜索学生框 -->
    <div class="alert-search-student" v-if="isSearchStudent">
      <div class="search-list">
        <div class="cancel" @click="isSearchStudent = false">×</div>

        <div class="search-input">
          <input type="text" placeholder="请输入学生姓名" v-model="name" />
          <button type="button" @click="search">搜索</button>
        </div>

        <div v-if="searchStudentsMsgs.length">
          <div class="list-student">
            <table>
              <tr>
                <th>学号</th>
                <th>学生姓名</th>
                <th>专业</th>
                <th>班级</th>
                <th>年级</th>
                <th>操作</th>
              </tr>
              <tr
                v-for="(searchStudentsMsg, index) in searchStudentsMsgs"
                :key="index"
              >
                <td>{{ searchStudentsMsg.studentNum }}</td>
                <td>{{ searchStudentsMsg.name }}</td>
                <td>{{ searchStudentsMsg.major }}</td>
                <td>{{ searchStudentsMsg.className }}</td>
                <td>{{ searchStudentsMsg.grade }}</td>
                <td>
                  <button
                    class="update"
                    @click="
                      getThisStudentMsg(
                        searchStudentsMsg.id,
                        searchStudentsMsg.studentNum,
                        searchStudentsMsg.name,
                        searchStudentsMsg.major,
                        searchStudentsMsg.className,
                        searchStudentsMsg.grade
                      )
                    "
                  >
                    编辑
                  </button>
                  <button class="delete" @click="getStudentId(searchStudentsMsg.id)">
                    删除
                  </button>
                </td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  reqAddStudent,
  reqDeleteStudent,
  reqUpdateStudent,
  reqListStudents
} from "../api";
import { mapState } from "vuex";

export default {
  data() {
    return {
      isAddStudent: false,
      isDeleteStudent: false,
      isUpdateStudent: false,
      isSearchStudent: false,

      studentNum: "",
      name: "",
      major: "",
      className: "",
      grade: "",
      id: "",

      pageNum: "1",
      studentMsgs: []
    };
  },

  methods: {
    //增加课程
    async addStudent() {
      let result;
      const { studentNum, name, major, className, grade } = this;

      result = await reqAddStudent({
        studentNum,
        name,
        major,
        className,
        grade
      });
      if (result.status === 200) {
        alert("添加成功");
      } else {
        alert(result.msg);
      }
      this.isAddStudent = false;
      this.$router.go(0)
    },

    //删除学生
    async deleteStudent() {
      let result;
      const { id } = this;
      result = await reqDeleteStudent({ id });
      if (result.status === 200) {
        alert("删除成功");
      }
      this.isDeleteStudent = false;
      this.$router.go(0)
    },

    //修改课程
    async updateStudent() {
      let result;
      const { id, studentNum, name, major, className, grade } = this;

      result = await reqUpdateStudent({
        id,
        studentNum,
        name,
        major,
        className,
        grade
      });
      if (result.status === 200) {
        alert("修改成功");
      }
      this.isUpdateStudent = false;
      this.$router.go(0)
    },

    //查询课程
    search() {
      const { name } = this;
      if (name) {
        this.$store.dispatch("searchStudents", name);
      }
    },

    //依靠年级查询学生
    async searchByGrade() {
      let result = await reqListStudents(this.grade, this.studentMsgs.pageNum);
      if(result.status === 200){
        this.studentMsgs = result.data;
      }else{
        this.studentMsgs = ""
      }
      
    },

    //分页
    async prePage() {
      let result = await reqListStudents(
        this.grade,
        this.studentMsgs.pageNum - 1
      );
      this.pageNum = result.data.pageNum;
      this.studentMsgs = [];
      this.studentMsgs = result.data;
    },

    async nextPage() {
      let result = await reqListStudents(
        this.grade,
        this.studentMsgs.pageNum + 1
      );
      this.pageNum = result.data.pageNum;
      this.studentMsgs = [];
      this.studentMsgs = result.data;
    },

    //获取所有的课程信息
    async getStudent() {
      let result;
      result = await await reqListStudents(this.grade, this.pageNum);
      this.studentMsgs = result.data;
    },

    //获取当前课程id
    getStudentId(id) {
      this.id = id;
      this.isDeleteStudent = true;
    },

    //获取当前课程的详细信息
    getThisStudentMsg(id, studentNum, name, major, className, grade) {
      this.id = id;
      this.studentNum = studentNum;
      this.name = name;
      this.major = major;
      this.className = className;
      this.grade = grade;

      this.isUpdateStudent = true;
    }
  },

  mounted() {
    this.getStudent();
  },

  computed: {
    ...mapState(["searchStudentsMsgs"])
  }
};
</script>

<style scoped>
.search-btn {
  border: none;
  float: left;
  outline: none;
}

.grade {
  border: 1px solid #2795e9;
  outline: none;
  position: absolute;
  left: 21%;
  width: 60px;
  padding: 5px;
  top: 23%;
  text-align: center;
}

.grade::placeholder {
  font-size: 12px;
}

.add-btn {
  float: right;
  border: none;
  outline: none;
}

.list-student {
  width: 980px;
  margin: 20px auto 0px;
}

.list-student table {
  width: 980px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.list-student tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.list-student th {
  font-weight: bolder;
  padding: 10px 15px;
  background-color: #2795e9;
  color: white;
}

.list-student td {
  padding: 8px;
}

.list-student td button {
  outline: none;
  border: none;
  background: rgb(243, 239, 239);
}

.list-student td button.update {
  color: rgb(15, 221, 125);
}

.list-student td button.delete {
  color: red;
}

.list-student td button:hover {
  background: white;
}

.alert-add-student {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-add-student .add-student {
  position: relative;
  width: 960px;
  padding: 20px;
  top: 15%;
  left: 20%;
  border: 1px solid #ccc;
  border-radius: 15px;
  background: white;
  box-shadow: 2px 2px 30px #909090;
}

.alert-add-student .add-student form {
  width: 308px;
  margin: 5px auto 20px;
}

.alert-add-student .add-student tr {
  padding: 5px;
  margin: 5px;
}

.alert-add-student .add-student tr input[type="text"],
.alert-add-student .add-student tr input[type="password"] {
  border: 1px solid #ccc;
  height: 20px;
  padding: 2px;
  margin: 3px;
  width: 200px;
  outline: none;
}

.alert-add-student .add-student tr select {
  border: 1px solid #ccc;
  width: 200px;
  padding: 2px;
  margin-left: 8px;
  height: 26px;
  outline: none;
  text-align: left;
  line-height: 26px;
}

.alert-add-student .add-student p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
  margin-top: 5px;
}

.alert-add-student .add-student p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-add-student .add-student p button:last-child {
  float: right;
  background: red;
}

.alert-delete-student {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  /* background: rgb(51, 50, 50); */
  z-index: 1200;
}

.alert-delete-student .delete-student {
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

.alert-delete-student .delete-student form {
  margin: 10px auto 20px;
  width: 210px;
}

.alert-delete-student .delete-student p {
  padding: 8px;
  text-align: center;
}

.alert-delete-student .delete-student p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
}

.alert-delete-student .delete-student p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-delete-student .delete-student p button:last-child {
  float: right;
  background: red;
}

.alert-search-student {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1000;
}

.search-list {
  position: relative;
  width: 980px;
  padding: 50px;
  top: 20%;
  left: 15%;
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

/* 分页 */
.pagination {
  margin-top: 20px;
  font-size: 25px;
  float: right;
}

.pagination span {
  display: inline-block;
  width: 30px;
  height: 30px;
  background: rgb(212, 211, 211);
  text-align: center;
  line-height: 30px;
  margin-right: 3px;
  cursor: pointer;
}

.pagination span:nth-child(2) {
  background: none;
  font-size: 20px;
}
</style>
