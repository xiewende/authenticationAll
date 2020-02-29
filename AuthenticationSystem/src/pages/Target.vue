<template>
  <div>
    <!-- 搜索和添加按钮 -->
    <div>
      <div class="search-input">
        <span>
          <input
            type="text"
            class="search-version"
            placeholder="版本号"
            v-model="version"
            @keyup.enter="search"
          />
        </span>

        <span>
          <input
            type="text"
            class="search-course"
            placeholder="课程名"
            v-model="courseName"
            @keyup.enter="search"
          />
        </span>
      </div>

      <el-button
        type="primary"
        icon="el-icon-plus"
        class="add-btn"
        @click="isAddTarget = true"
        >添加</el-button
      >
    </div>

    <!-- 展示所有目标 -->
    <div class="list-target" v-if="isListTarget">
      <table>
        <tr>
          <th>目标编号</th>
          <th>目标内容</th>
          <th>课程名</th>
          <th>版本</th>
          <th>操作</th>
        </tr>
        <tr v-for="(targetMsg, index) in targetMsgs" :key="index">
          <td>{{ targetMsg.targerNum }}</td>
          <td>{{ targetMsg.content }}</td>
          <td>{{ targetMsg.course.name }}</td>
          <td>{{ targetMsg.version }}</td>
          <td>
            <button
              class="update"
              @click="
                getThisTargetMsg(
                  targetMsg.id,
                  targetMsg.targerNum,
                  targetMsg.content,
                  targetMsg.course.id,
                  targetMsg.version
                )
              "
            >
              编辑
            </button>
            <button class="delete" @click="getTargetId(targetMsg.id)">
              删除
            </button>
          </td>
        </tr>
      </table>
    </div>

    <!-- 搜索出现 -->
    <div class="list-target" v-if="isSearchTarget">
      <table>
        <tr>
          <th>目标编号</th>
          <th>目标内容</th>
          <th>课程名</th>
          <th>版本</th>
          <th>操作</th>
        </tr>
        <tr v-for="(searchTargetMsg, index) in searchTargetMsgs" :key="index">
          <td>{{ searchTargetMsg.targerNum }}</td>
          <td>{{ searchTargetMsg.content }}</td>
          <td>{{ searchTargetMsg.course.name }}</td>
          <td>{{ searchTargetMsg.version }}</td>
          <td>
            <button
              class="update"
              @click="
                getThisTargetMsg(
                  searchTargetMsg.id,
                  searchTargetMsg.targerNum,
                  searchTargetMsg.content,
                  searchTargetMsg.course.id,
                  searchTargetMsg.version
                )
              "
            >
              编辑
            </button>
            <button class="delete" @click="getTargetId(searchTargetMsg.id)">
              删除
            </button>
          </td>
        </tr>
      </table>
    </div>

    <!-- 添加目标框 -->
    <div class="alert-add-target" v-if="isAddTarget">
      <div class="add-target">
        <div class="cancel" @click="isAddTarget = false">×</div>
        <div>
          <form>
            <table>
              <tr>
                <td>目标编号：</td>
                <td><input type="text" v-model="targetNum" /></td>
              </tr>
              <tr>
                <td class="target-point">目标内容：</td>
                <td>
                  <textarea v-model="content"></textarea>
                </td>
              </tr>
              <tr>
                <td>课程名：</td>
                <td>
                  <select v-model="courseId">
                    <option
                      v-for="(course, index) in courses"
                      :key="index"
                      :value="course.id"
                      >{{ course.name }}</option
                    >
                  </select>
                </td>
              </tr>
              <tr>
                <td>版本：</td>
                <td><input type="text" v-model="version" /></td>
              </tr>
            </table>

            <p>
              <button @click="isAddTarget = false">取消</button>
              <button @click="addTarget">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 删除目标框 -->
    <div class="alert-delete-target" v-if="isDeleteTarget">
      <div class="delete-target">
        <div class="cancel" @click="isDeleteTarget = false">×</div>
        <div>
          <form>
            <p>确定要删除这个目标吗？</p>
            <p>
              <button @click="isDeleteTarget = false">取消</button>
              <button @click="deleteTarget">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 编辑目标框 -->
    <div class="alert-add-target" v-if="isUpdateTarget">
      <div class="add-target">
        <div class="cancel" @click="isUpdateTarget = false">×</div>
        <div>
          <form>
            <table>
              <tr>
                <td>目标编号：</td>
                <td><input type="text" v-model="targetNum" /></td>
              </tr>
              <tr>
                <td class="target-point">目标内容：</td>
                <td>
                  <textarea v-model="content"></textarea>
                </td>
              </tr>
              <tr>
                <td>课程名：</td>
                <td>
                  <select v-model="courseId">
                    <option
                      v-for="(course, index) in courses"
                      :key="index"
                      :value="course.id"
                      >{{ course.name }}</option
                    >
                  </select>
                </td>
              </tr>
              <tr>
                <td>版本：</td>
                <td><input type="text" v-model="version" /></td>
              </tr>
            </table>

            <p>
              <button @click="isUpdateTarget = false">取消</button>
              <button @click="updateTarget">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  reqAddTarget,
  reqDeleteTarget,
  reqUpdateTarget,
  reqListTargets,
  reqCourses
} from "../api";

export default {
  data() {
    return {
      isListTarget: true,
      isAddTarget: false,
      isDeleteTarget: false,
      isUpdateTarget: false,
      isSearchTarget: false,

      targetNum: "",
      content: "",
      courseName: "",
      version: "",
      id: "",
      courseId: "",
      targetId: "",

      targetMsgs: [],
      searchTargetMsgs: [],

      courses: [] //课程信息
    };
  },

  methods: {
    //获取所有课程信息
    async getCourses() {
      let result = await reqCourses();
      this.courses = result.data;
    },

    //获取所有的目标信息
    async getTarget() {
      let result;
      const { version, courseName } = this;
      result = await reqListTargets({ version, courseName });
      if (result.status === 200) {
        this.targetMsgs = result.data;
      }
    },

    //增加目标
    async addTarget() {
      let result;
      console.log(this.targetNum);
      result = await reqAddTarget(
        this.targetNum,
        this.content,
        this.courseId,
        this.version
      );
      if (result.status === 200) {
        // alert(this.courseId);
        alert("添加成功");
      } else {
        alert(result.msg);
      }
      this.isAddTarget = false;
      this.$router.go(0);
    },

    //删除目标
    async deleteTarget() {
      let result;
      const { id } = this;
      result = await reqDeleteTarget({ id });
      if (result.status === 200) {
        alert("删除成功");
      }
      this.isDeleteTarget = false;
      this.$router.go(0);
    },

    //修改目标
    async updateTarget() {
      let result;
      result = await reqUpdateTarget(
        this.id,
        this.targetNum,
        this.content,
        this.courseId,
        this.version
      );
      if (result.status === 200) {
        alert("修改成功");
      } else {
        alert("修改失败");
      }
      this.isUpdateTarget = false;
      this.$router.go(0);
    },

    //查询目标
    async search() {
      const { version, courseName } = this;
      let result = await reqListTargets({ version, courseName });
      if (result.status === 200) {
        this.searchTargetMsgs = result.data;
      } else {
        this.searchTargetMsgs = "";
      }
      this.isListTarget = false;
      this.isSearchTarget = true;
    },

    //获取当前目标id
    getTargetId(id) {
      this.id = id;
      this.isDeleteTarget = true;
    },

    //获取当前目标的详细信息
    getThisTargetMsg(id, targetNum, content, courseId, version) {
      this.id = id;
      this.targetNum = targetNum;
      this.content = content;
      this.courseId = courseId;
      this.version = version;

      this.isUpdateTarget = true;
    }
  },

  mounted() {
    this.getTarget();
    this.getCourses();
  }
};
</script>

<style scoped>
.search-input {
  position: relative;
}

.search-input span input {
  border: 1px solid #2795e9;
  height: 23px;
  font-size: 15px;
  display: inline-block;
  position: absolute;
  padding: 5px;
  text-align: center;
}

.search-version {
  width: 50px;
  height: 25px;
}

.search-course {
  margin-top: 50px;
  width: 80px;
}

.search-input span input::placeholder {
  text-align: left;
}

.add-btn {
  float: right;
  border: none;
  outline: none;
}

.list-target {
  width: 980px;
  margin: 20px auto 0px;
}

.list-target table {
  width: 980px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.list-target tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.list-target th {
  font-weight: bolder;
  padding: 10px 15px;
  background-color: #2795e9;
  color: white;
}

.list-target td {
  padding: 8px;
}

.list-target td button {
  outline: none;
  border: none;
  background: rgb(243, 239, 239);
}

.list-target td button.update {
  color: rgb(15, 221, 125);
}

.list-target td button.delete {
  color: red;
}

.list-target td button:hover {
  background: white;
}

.alert-add-target {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-add-target .add-target {
  position: relative;
  width: 520px;
  padding: 20px;
  top: 20%;
  left: 30%;
  border: 1px solid #ccc;
  border-radius: 15px;
  background: white;
  box-shadow: 2px 2px 30px #909090;
}

.alert-add-target .add-target form {
  width: 308px;
  margin: 5px auto 20px;
}

.alert-add-target .add-target tr {
  padding: 5px;
  margin: 5px;
}

.alert-add-target .add-target tr td {
  text-align: center;
}

.alert-add-target .add-target tr td.target-point {
  vertical-align: top;
}

.alert-add-target .add-target tr input[type="text"],
.alert-add-target .add-target tr input[type="password"] {
  border: 1px solid #ccc;
  height: 20px;
  padding: 2px;
  margin: 3px;
  width: 200px;
  outline: none;
}

.alert-add-target .add-target select {
  border: 1px solid #ccc;
  height: 25px;
  padding: 3px;
  margin: 3px;
  width: 205px;
  outline: none;
}

.alert-add-target .add-target textarea {
  border: 1px solid #ccc;
  width: 200px;
  height: 50px;
  outline: none;
}

.alert-add-target .add-target p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
  margin-top: 5px;
}

.alert-add-target .add-target p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-add-target .add-target p button:last-child {
  float: right;
  background: red;
}

.alert-delete-target {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  /* background: rgb(51, 50, 50); */
  z-index: 1200;
}

.alert-delete-target .delete-target {
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

.alert-delete-target .delete-target form {
  margin: 10px auto 20px;
  width: 210px;
}

.alert-delete-target .delete-target p {
  padding: 8px;
  text-align: center;
}

.alert-delete-target .delete-target p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
}

.alert-delete-target .delete-target p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-delete-target .delete-target p button:last-child {
  float: right;
  background: red;
}

.alert-search-target {
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
