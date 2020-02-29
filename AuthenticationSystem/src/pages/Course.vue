<template>
  <div>
    <!-- 搜索和添加按钮 -->
    <div>
      <el-button
        type="primary"
        icon="el-icon-search"
        class="search-btn"
        @click="isSearchCourse = true"
        >搜索</el-button
      >

      <input
        type="text"
        v-model="version"
        class="version"
        placeholder="输入版本号"
        @keyup.enter="searchByVersion"
      />

      <el-button
        type="primary"
        icon="el-icon-plus"
        class="add-btn"
        @click="isAddCourse = true"
        >添加</el-button
      >
    </div>

    <!-- 展示所有课程 -->
    <div class="list-course">
      <table>
        <tr>
          <th>课程号</th>
          <th>课程名</th>
          <th>理论学时</th>
          <th>实验学时</th>
          <th>学分</th>
          <th>课程性质</th>
          <th>课程类别</th>
          <th>大纲编撰者</th>
          <th>版本号</th>
          <th>操作</th>
        </tr>
        <tr v-for="(courseMsg, index) in courseMsgs.list" :key="index">
          <td>{{ courseMsg.courseNum }}</td>
          <td>{{ courseMsg.name }}</td>
          <td>{{ courseMsg.theoryTime }}</td>
          <td>{{ courseMsg.experTime }}</td>
          <td>{{ courseMsg.credit }}</td>
          <td>{{ courseMsg.property }}</td>
          <td>{{ courseMsg.type }}</td>
          <td>{{ courseMsg.teacher.name }}</td>
          <td>{{ courseMsg.version }}</td>
          <td>
            <button
              class="update"
              @click="
                getThisCourseMsg(
                  courseMsg.id,
                  courseMsg.courseNum,
                  courseMsg.name,
                  courseMsg.theoryTime,
                  courseMsg.experTime,
                  courseMsg.credit,
                  courseMsg.property,
                  courseMsg.type,
                  courseMsg.version,
                  courseMsg.teacher.name
                )
              "
            >
              编辑
            </button>
            <button class="delete" @click="getCourseId(courseMsg.id)">
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
    <div class="alert-add-course" v-if="isAddCourse">
      <div class="add-course">
        <div class="cancel" @click="isAddCourse = false">×</div>
        <div>
          <form>
            <table>
              <tr>
                <td>课程号：</td>
                <td><input type="text" v-model="courseNum" /></td>
              </tr>
              <tr>
                <td>课程名：</td>
                <td><input type="text" v-model="name" /></td>
              </tr>
              <tr>
                <td>理论学时：</td>
                <td><input type="text" v-model="theoryTime" /></td>
              </tr>
              <tr>
                <td>实验学时：</td>
                <td><input type="text" v-model="experTime" /></td>
              </tr>
              <tr>
                <td>学分：</td>
                <td><input type="text" v-model="credit" /></td>
              </tr>
              <tr>
                <td>课程性质：</td>
                <td>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
                    type="radio"
                    value="必修"
                    v-model="property"
                  />必修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" v-model="property" value="选修" />选修
                </td>
              </tr>
              <tr>
                <td>课程类别：</td>
                <td><input type="text" v-model="type" /></td>
              </tr>
              <tr>
                <td>大纲编撰者：</td>
                <td>
                  <select v-model="teacherName">
                    <option
                      v-for="(teacherName, index) in teacherNames"
                      :key="index"
                      >{{ teacherName.name }}</option
                    >
                  </select>
                </td>
              </tr>
              <tr>
                <td>版本号：</td>
                <td><input type="text" v-model="version" /></td>
              </tr>
            </table>

            <p>
              <button @click="isAddCourse = false">取消</button>
              <button @click="addCourse">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 删除课程框 -->
    <div class="alert-delete-course" v-if="isDeleteCourse">
      <div class="delete-teacher">
        <div class="cancel" @click="isDeleteCourse = false">×</div>
        <div>
          <form>
            <p>确定要删除这门课程吗？</p>
            <p>
              <button @click="isDeleteCourse = false">取消</button>
              <button @click="deleteCourse">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 编辑课程框 -->
    <div class="alert-add-course" v-if="isUpdateCourse">
      <div class="add-course">
        <div class="cancel" @click="isUpdateCourse = false">×</div>
        <div>
          <form @submit.prevent="updateCourse">
            <table>
              <tr>
                <td>课程号：</td>
                <td><input type="text" v-model="courseNum" /></td>
              </tr>
              <tr>
                <td>课程名：</td>
                <td><input type="text" v-model="name" /></td>
              </tr>
              <tr>
                <td>理论学时：</td>
                <td><input type="text" v-model="theoryTime" /></td>
              </tr>
              <tr>
                <td>实验学时：</td>
                <td><input type="text" v-model="experTime" /></td>
              </tr>
              <tr>
                <td>学分：</td>
                <td><input type="text" v-model="credit" /></td>
              </tr>
              <tr>
                <td>课程性质：</td>
                <td>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
                    type="radio"
                    value="必修"
                    v-model="property"
                  />必修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" v-model="property" value="选修" />选修
                </td>
              </tr>
              <tr>
                <td>课程类别：</td>
                <td><input type="text" v-model="type" /></td>
              </tr>
              <tr>
                <td>大纲编撰者：</td>
                <td>
                  <select v-model="teacherName">
                    <option
                      v-for="(teacherName, index) in teacherNames"
                      :key="index"
                      >{{ teacherName.name }}</option
                    >
                  </select>
                </td>
              </tr>
              <tr>
                <td>版本号：</td>
                <td><input type="text" v-model="version" /></td>
              </tr>
            </table>

            <p>
              <button @click="isUpdateCourse = false">取消</button>
              <button>确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 搜索课程框 -->
    <div class="alert-search-course" v-if="isSearchCourse">
      <div class="search-list">
        <div class="cancel" @click="isSearchCourse = false">×</div>

        <div class="search-input">
          <input type="text" placeholder="请输入课程名" v-model="name" />
          <button type="button" @click="search">搜索</button>
        </div>

        <div v-if="searchCoursesMsgs.length">
          <div class="list-course">
            <table>
              <tr>
                <th>课程号</th>
                <th>课程名</th>
                <th>理论学时</th>
                <th>实验学时</th>
                <th>学分</th>
                <th>课程性质</th>
                <th>课程类别</th>
                <th>大纲编撰者</th>
                <th>版本号</th>
                <th>操作</th>
              </tr>
              <tr
                v-for="(searchCoursesMsg, index) in searchCoursesMsgs"
                :key="index"
              >
                <td>{{ searchCoursesMsg.courseNum }}</td>
                <td>{{ searchCoursesMsg.name }}</td>
                <td>{{ searchCoursesMsg.theoryTime }}</td>
                <td>{{ searchCoursesMsg.experTime }}</td>
                <td>{{ searchCoursesMsg.credit }}</td>
                <td>{{ searchCoursesMsg.property }}</td>
                <td>{{ searchCoursesMsg.type }}</td>
                <td>{{ searchCoursesMsg.teacher.name }}</td>
                <td>{{ searchCoursesMsg.version }}</td>
                <td>
                  <button
                    class="update"
                    @click="
                      getThisCourseMsg(
                        courseMsg.id,
                        courseMsg.courseNum,
                        courseMsg.name,
                        courseMsg.theoryTime,
                        courseMsg.experTime,
                        courseMsg.credit,
                        courseMsg.property,
                        courseMsg.type,
                        courseMsg.version,
                        courseMsg.teacherName
                      )
                    "
                  >
                    编辑
                  </button>
                  <button class="delete" @click="getCourseId(courseMsg.id)">
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
  reqAddCourse,
  reqDeleteCourse,
  reqUpdateCourse,
  reqListCourses
} from "../api";
import { mapState } from "vuex";

export default {
  data() {
    return {
      isAddCourse: false,
      isDeleteCourse: false,
      isUpdateCourse: false,
      isSearchCourse: false,

      courseNum: "",
      name: "",
      theoryTime: "",
      experTime: "",
      credit: "",
      property: "",
      type: "",
      version: "",
      teacherName: "",
      id: "",

      pageNum: "1",
      courseMsgs:[],
    };
  },

  methods: {
    //增加课程
    async addCourse() {
      let result;
      const {
        courseNum,
        name,
        theoryTime,
        experTime,
        credit,
        property,
        type,
        version,
        teacherName
      } = this;

      result = await reqAddCourse({
        courseNum,
        name,
        theoryTime,
        experTime,
        credit,
        property,
        type,
        version,
        teacherName
      });
      if (result.status === 200) {
        alert("添加成功");
      } else {
        alert(result.msg);
      }
      this.isAddCourse = false;
      this.$router.go(0)
    },

    //删除课程
    async deleteCourse() {
      let result;
      const { id } = this;
      result = await reqDeleteCourse({ id });
      if (result.status === 200) {
        alert("删除成功");
      }
      this.isDeleteCourse = false;
      this.$router.go(0)
    },

    //修改课程
    async updateCourse() {
      let result;
      const {
        id,
        courseNum,
        name,
        theoryTime,
        experTime,
        credit,
        property,
        type,
        version,
        teacherName
      } = this;

      result = await reqUpdateCourse({
        id,
        courseNum,
        name,
        theoryTime,
        experTime,
        credit,
        property,
        type,
        version,
        teacherName
      });
      if (result.status === 200) {
        alert("修改成功");
      }
      this.isUpdateCourse = false;
      this.$router.go(0)
    },

    //查询课程
    search() {
      const { name } = this;
      if (name) {
        this.$store.dispatch("searchCourses", name);
      }
    },

    //依靠版本号查询课程
    async searchByVersion() {
      console.log(this.version);
      let result = await reqListCourses(
        this.version,
        this.courseMsgs.pageNum
      );
      if(result.status === 200){
        this.courseMsgs = result.data;
      }else{
        this.courseMsgs = ""
      }
      
    },

    //分页
    async prePage() {
      let result = await reqListCourses(
        this.version,
        this.courseMsgs.pageNum - 1
      );
      this.pageNum = result.data.pageNum;
      this.courseMsgs = []
      this.courseMsgs = result.data;
    },

    async nextPage() {
      let result = await reqListCourses(
        this.version,
        this.courseMsgs.pageNum + 1
      );
      this.pageNum = result.data.pageNum;
      this.courseMsgs = []
      this.courseMsgs = result.data;
    },

    //获取所有的课程信息
    async getCourse(){
      let result
      result = await await reqListCourses(
        this.version,
        this.pageNum
      );
      this.courseMsgs = result.data
    },

    //获取当前课程id
    getCourseId(id) {
      console.log(id);
      this.id = id;
      this.isDeleteCourse = true;
    },

    //获取当前课程的详细信息
    getThisCourseMsg(
      id,
      courseNum,
      name,
      theoryTime,
      experTime,
      credit,
      property,
      type,
      version,
      teacherName
    ) {
      this.id = id;
      this.courseNum = courseNum;
      this.name = name;
      this.theoryTime = theoryTime;
      this.experTime = experTime;
      this.credit = credit;
      this.property = property;
      this.type = type;
      this.version = version;
      this.teacherName = teacherName;

      this.isUpdateCourse = true;
    }
  },

  mounted() {
    this.getCourse()
  },

  created() {
    this.$store.dispatch("getTeacherNames");
  },

  computed: {
    ...mapState(["teacherNames"]),
    ...mapState(["searchCoursesMsgs"])
  }
};
</script>

<style scoped>
.search-btn {
  border: none;
  float: left;
  outline: none;
}

.version {
  border: 1px solid #2795e9;
  outline: none;
  position: absolute;
  left: 21%;
  width: 60px;
  padding: 5px;
  top: 23%;
  text-align: center;
}

.version::placeholder {
  font-size: 12px;
}

.add-btn {
  float: right;
  border: none;
  outline: none;
}

.list-course {
  width: 980px;
  margin: 20px auto 0px;
}

.list-course table {
  width: 980px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.list-course tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.list-course th {
  font-weight: bolder;
  padding: 10px 15px;
  background-color: #2795e9;
  color: white;
}

.list-course td {
  padding: 8px;
}

.list-course td button {
  outline: none;
  border: none;
  background: rgb(243, 239, 239);
}

.list-course td button.update {
  color: rgb(15, 221, 125);
}

.list-course td button.delete {
  color: red;
}

.list-course td button:hover {
  background: white;
}

.alert-add-course {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-add-course .add-course {
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

.alert-add-course .add-course form {
  width: 308px;
  margin: 5px auto 20px;
}

.alert-add-course .add-course tr {
  padding: 5px;
  margin: 5px;
}

.alert-add-course .add-course tr input[type="text"],
.alert-add-course .add-course tr input[type="password"] {
  border: 1px solid #ccc;
  height: 20px;
  padding: 2px;
  margin: 3px;
  width: 200px;
  outline: none;
}

.alert-add-course .add-course tr select {
  border: 1px solid #ccc;
  width: 200px;
  padding: 2px;
  margin-left: 8px;
  height: 26px;
  outline: none;
  text-align: left;
  line-height: 26px;
}

.alert-add-course .add-course p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
  margin-top: 5px;
}

.alert-add-course .add-course p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-add-course .add-course p button:last-child {
  float: right;
  background: red;
}

.alert-delete-course {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-delete-course .delete-teacher {
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

.alert-delete-course .delete-teacher form {
  margin: 10px auto 20px;
  width: 210px;
}

.alert-delete-course .delete-teacher p {
  padding: 8px;
  text-align: center;
}

.alert-delete-course .delete-teacher p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
}

.alert-delete-course .delete-teacher p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-delete-course .delete-teacher p button:last-child {
  float: right;
  background: red;
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
