<template>
  <div>
    <!-- 搜索和添加按钮 -->
    <div>
      <el-button
        type="primary"
        icon="el-icon-search"
        class="search-btn"
        @click="isTeacherSearch = true"
        >搜索</el-button
      >
      <el-button
        type="primary"
        icon="el-icon-plus"
        class="add-btn"
        @click="isAddTeacher = true"
        >添加</el-button
      >
    </div>

    <!-- 展示所有老师 -->
    <div class="list-teacher">
      <table>
        <tr>
          <th>教师编号</th>
          <th>教师姓名</th>
          <th>密码</th>
          <th>角色名</th>
          <th>是否在职</th>
          <th>操作</th>
        </tr>
        <tr v-for="(teacherMsg, index) in teacherMsgs.list" :key="index">
          <td>{{ teacherMsg.teacherNum }}</td>
          <td>{{ teacherMsg.name }}</td>
          <td>{{ teacherMsg.password }}</td>
          <td>{{ teacherMsg.role.name }}</td>
          <td>{{ teacherMsg.status == 1 ? "是" : "否" }}</td>
          <td>
            <button
              class="update"
              @click="
                getThisTeacherMsg(
                  teacherMsg.id,
                  teacherMsg.name,
                  teacherMsg.teacherNum,
                  teacherMsg.role.name
                )
              "
            >
              编辑
            </button>
            <button class="delete" @click="getTeacherId(teacherMsg.id)">
              删除
            </button>
          </td>
        </tr>
      </table>
    </div>

    <!-- 分页 -->
    <div class="pagination">
      <span @click="prePage"> &laquo; </span>
      <span>{{ teacherMsgs.pageNum }}</span>
      <span @click="nextPage"> &raquo; </span>
    </div>

    <!-- 添加教师框 -->
    <div class="alert-add-teacher" v-if="isAddTeacher">
      <div class="add-teacher">
        <div class="cancel" @click="isAddTeacher = false">×</div>
        <div>
          <form @submit.prevent="addTeacher">
            <p>教师编号：<input type="text" v-model="teacherNum" /></p>
            <p>教师姓名：<input type="text" v-model="name" /></p>
            <p>
              角色名：
              <select v-model="rolename">
                <option v-for="(role, index) in roles" :key="index">
                  {{ role.name }}
                </option>
              </select>
            </p>
            <p>
              <button @click="isAddTeacher = false">取消</button>
              <button>确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 删除教师框 -->
    <div class="alert-delete-teacher" v-if="isDeleteTeacher">
      <div class="delete-teacher">
        <div class="cancel" @click="isDeleteTeacher = false">×</div>
        <div>
          <form @submit.prevent="deleteTeacher()">
            <p>确定要删除这个教师吗？</p>
            <p>
              <button @click="isDeleteTeacher = false">取消</button>
              <button>确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 编辑教师框 -->
    <div class="alert-add-teacher" v-if="isUpdateTeacher">
      <div class="add-teacher">
        <div class="cancel" @click="isUpdateTeacher = false">×</div>
        <div>
          <form @submit.prevent="updateTeacher">
            <p>教师编号：<input type="text" v-model="teacherNum" /></p>
            <p>教师姓名：<input type="text" v-model="name" /></p>
            <p>
              角色名：
              <select v-model="rolename">
                <option v-for="(role, index) in roles" :key="index">
                  {{ role.name }}
                </option>
              </select>
            </p>
            <p>
              <button @click="isUpdateTeacher = false">取消</button>
              <button>确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 搜索老师框 -->
    <div class="alert-search-teacher" v-if="isTeacherSearch">
      <div class="search-list">
        <div class="cancel" @click="isTeacherSearch = false">×</div>

        <div class="search-input">
          <input type="text" placeholder="请输入教师名" v-model="keyword" />
          <button @click="search" type="button">搜索</button>
        </div>

        <div v-if="searchTeachersMsgs.length">
          <div class="list-teacher">
            <table>
              <tr>
                <th>教师编号</th>
                <th>教师姓名</th>
                <th>密码</th>
                <th>角色名</th>
                <th>是否在职</th>
                <th>操作</th>
              </tr>
              <tr
                v-for="(searchTeachersMsg, index) in searchTeachersMsgs"
                :key="index"
              >
                <td>{{ searchTeachersMsg.teacherNum }}</td>
                <td>{{ searchTeachersMsg.name }}</td>
                <td>{{ searchTeachersMsg.password }}</td>
                <td>{{ searchTeachersMsg.role.name }}</td>
                <td>{{ searchTeachersMsg.status == 1 ? "是" : "否" }}</td>
                <td>
                  <button
                    class="update"
                    @click="
                      getThisTeacherMsg(
                        searchTeachersMsg.id,
                        searchTeachersMsg.name,
                        searchTeachersMsg.teacherNum,
                        searchTeachersMsg.role.name
                      )
                    "
                  >
                    编辑
                  </button>
                  <button class="delete" @click="getTeacherId(searchTeachersMsg.id)">
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
  reqAddTeacher,
  reqDeleteTeacher,
  reqUpdateTeacher,
  reqListTeachers
} from "../api/index.js";
import { mapState } from "vuex";

export default {
  data() {
    return {
      // value: false,

      teacherNum: "",
      name: "",
      rolename: "",
      id: "",

      isAddTeacher: false,
      isUpdateTeacher: false,
      isDeleteTeacher: false,
      isTeacherSearch: false,

      keyword: ""
    };
  },

  methods: {
    //分页
    prePage() {
      this.$store.dispatch("getTeacher", this.teacherMsgs.pageNum - 1);
    },

    nextPage() {
      this.$store.dispatch("getTeacher", this.teacherMsgs.pageNum + 1);
    },

    //添加老师信息
    async addTeacher() {
      let result;
      const { name, teacherNum, rolename } = this;
      /* if (!/^\d{6}$/.test(teacherNum)) {
        alert("教师编号必须填写是六位数字");
        return;
      } else if (!this.name) {
        alert("教师姓名必须填写");
        return;
      } else if (!this.rolename) {
        alert("教师角色名必须填写");
        return;
      } */

      result = await reqAddTeacher({ name, teacherNum, rolename });
      if (result.status === 200) {
        alert("添加成功");
      } else {
        alert(result.msg);
      }
      this.isAddTeacher = false;
      this.$router.go(0)
    },

    //删除老师信息
    async deleteTeacher() {
      console.log(this.id);
      let result;
      const { id } = this;
      result = await reqDeleteTeacher({ id });
      if (result.status === 200) {
        alert("删除成功");
      }
      this.isDeleteTeacher = false;
      this.$router.go(0)
    },

    //编辑老师信息
    async updateTeacher() {
      let result;
      const { id, name, teacherNum, rolename } = this; 

      result = await reqUpdateTeacher({ id, name, teacherNum, rolename });
      if (result.status === 200) {
        alert("修改成功");
      }
      this.isUpdateTeacher = false;
      this.$router.go(0)
    },

    //搜索老师信息
    search() {
      const { keyword } = this;
      console.log(keyword);
      if (keyword) {
        this.$store.dispatch("searchTeachers", { keyword });
      }
    },
    //得到当前老师的id
    getTeacherId(id) {
      console.log(id);
      this.id = id;
      this.isDeleteTeacher = true;
    },

    //获取当前老师id、编号、老师名、角色名
    getThisTeacherMsg(id, name, teacherNum, rolename) {
      this.id = id;
      this.name = name;
      this.teacherNum = teacherNum;
      this.rolename = rolename;
      this.isUpdateTeacher = true;
    }
  },

  created() {
    this.$store.dispatch("getTeacher");
    this.$store.dispatch("getRoles");
  },

  computed: {
    ...mapState(["teacherMsgs"]),
    ...mapState(["roles"]),
    ...mapState(["searchTeachersMsgs"])
  }
};
</script>

<style scoped>
.search-btn {
  border: none;
  float: left;
  outline: none;
}

.add-btn {
  float: right;
  border: none;
  outline: none;
}

.list-teacher {
  width: 960px;
  margin: 20px auto 0px;
}

.list-teacher table {
  width: 960px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.list-teacher tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.list-teacher th {
  font-weight: bolder;
  padding: 10px 20px;
  background-color: #2795e9;
  color: white;
}

.list-teacher td {
  padding: 10px 20px;
}

.list-teacher td button {
  outline: none;
  border: none;
  background: rgb(243, 239, 239);
}

.list-teacher td button.update {
  color: rgb(15, 221, 125);
}

.list-teacher td button.delete {
  color: red;
}

.list-teacher td button:hover {
  background: white;
}

.alert-add-teacher {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-add-teacher .add-teacher {
  position: relative;
  width: 960px;
  padding: 20px;
  top: 30%;
  left: 15%;
  border: 1px solid #ccc;
  border-radius: 15px;
  background: white;
  box-shadow: 2px 2px 30px #909090;
}

.alert-add-teacher .add-teacher form {
  width: 351px;
  margin: 10px auto 20px;
}

.alert-add-teacher .add-teacher p {
  padding: 5px;
}

.alert-add-teacher .add-teacher p input[type="text"],
.alert-add-teacher .add-teacher p input[type="password"] {
  border: 1px solid #ccc;
  height: 20px;
  padding: 2px;
  width: 250px;
  outline: none;
}

.alert-add-teacher .add-teacher p select {
  border: 1px solid #ccc;
  width: 256px;
  padding: 2px;
  margin-left: 8px;
  height: 26px;
  outline: none;
  text-align: left;
  line-height: 26px;
}

.alert-add-teacher .add-teacher p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
}

.alert-add-teacher .add-teacher p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-add-teacher .add-teacher p button:last-child {
  float: right;
  background: red;
}

.alert-delete-teacher {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-delete-teacher .delete-teacher {
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

.alert-delete-teacher .delete-teacher form {
  margin: 10px auto 20px;
  width: 210px;
}

.alert-delete-teacher .delete-teacher p {
  padding: 8px;
  text-align: center;
}

.alert-delete-teacher .delete-teacher p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
}

.alert-delete-teacher .delete-teacher p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-delete-teacher .delete-teacher p button:last-child {
  float: right;
  background: red;
}

.alert-search-teacher {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1000;
}

.search-list {
  position: relative;
  width: 960px;
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
