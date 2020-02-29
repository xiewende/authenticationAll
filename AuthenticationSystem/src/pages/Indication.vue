<template>
  <div>
    <!-- 搜索和添加按钮 -->
    <div>
      <span>
        <input
          type="text"
          class="search-btn"
          placeholder="版本号"
          v-model="version"
          @keyup.enter="search"
        />
      </span>

      <el-button
        type="primary"
        icon="el-icon-plus"
        class="add-btn"
        @click="isAddIndication = true"
        >添加</el-button
      >
    </div>

    <!-- 展示所有指标 -->
    <div class="list-indication" v-if="isListIndication">
      <table>
        <tr>
          <th>指标编号</th>
          <th>指标点</th>
          <th>版本</th>
          <th>操作</th>
        </tr>
        <tr v-for="(indicationMsg, index) in indicationMsgs" :key="index">
          <td>{{ indicationMsg.indicationNum }}</td>
          <td>{{ indicationMsg.content }}</td>
          <td>{{ indicationMsg.version }}</td>
          <td>
            <button
              class="update"
              @click="
                getThisIndicationMsg(
                  indicationMsg.id,
                  indicationMsg.indicationNum,
                  indicationMsg.content,
                  indicationMsg.version
                )
              "
            >
              编辑
            </button>
            <button class="delete" @click="getIndicationId(indicationMsg.id)">
              删除
            </button>
          </td>
        </tr>
      </table>
    </div>

    <!-- 搜索出现 -->
    <div class="list-indication" v-if="isSearchIndication">
      <table>
        <tr>
          <th>指标编号</th>
          <th>指标点</th>
          <th>版本</th>
          <th>操作</th>
        </tr>
        <tr
          v-for="(searchIndicationsMsg, index) in searchIndicationsMsgs"
          :key="index"
        >
          <td>{{ searchIndicationsMsg.indicationNum }}</td>
          <td>{{ searchIndicationsMsg.content }}</td>
          <td>{{ searchIndicationsMsg.version }}</td>
          <td>
            <button
              class="update"
              @click="
                getThisIndicationMsg(
                  searchIndicationsMsg.id,
                  searchIndicationsMsg.indicationNum,
                  searchIndicationsMsg.content,
                  searchIndicationsMsg.version
                )
              "
            >
              编辑
            </button>
            <button
              class="delete"
              @click="getIndicationId(searchIndicationsMsg.id)"
            >
              删除
            </button>
          </td>
        </tr>
      </table>
    </div>

    <!-- 添加指标框 -->
    <div class="alert-add-indication" v-if="isAddIndication">
      <div class="add-indication">
        <div class="cancel" @click="isAddIndication = false">×</div>
        <div>
          <form>
            <table>
              <tr>
                <td>指标编号：</td>
                <td><input type="text" v-model="indicationNum" /></td>
              </tr>
              <tr>
                <td class="indication-point">指标点：</td>
                <td>
                  <textarea v-model="content"></textarea>
                </td>
              </tr>
              <tr>
                <td>版本：</td>
                <td><input type="text" v-model="version" /></td>
              </tr>
            </table>

            <p>
              <button @click="isAddIndication = false">取消</button>
              <button @click="addIndication">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 删除指标框 -->
    <div class="alert-delete-indication" v-if="isDeleteIndication">
      <div class="delete-indication">
        <div class="cancel" @click="isDeleteIndication = false">×</div>
        <div>
          <form>
            <p>确定要删除这个指标吗？</p>
            <p>
              <button @click="isDeleteIndication = false">取消</button>
              <button @click="deleteIndication">确认</button>
            </p>
          </form>
        </div>
      </div>
    </div>

    <!-- 编辑指标框 -->
    <div class="alert-add-indication" v-if="isUpdateIndication">
      <div class="add-indication">
        <div class="cancel" @click="isUpdateIndication = false">×</div>
        <div>
          <form @submit.prevent="updateIndication">
            <table>
              <tr>
                <td>指标编号：</td>
                <td><input type="text" v-model="indicationNum" /></td>
              </tr>
              <tr>
                <td>指标点：</td>
                <td><input type="text" v-model="content" /></td>
              </tr>
              <tr>
                <td>版本：</td>
                <td><input type="text" v-model="version" /></td>
              </tr>
            </table>

            <p>
              <button @click="isUpdateIndication = false">取消</button>
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
  reqAddIndication,
  reqDeleteIndication,
  reqUpdateIndication,
  reqListIndications
} from "../api";

export default {
  data() {
    return {
      isListIndication: true,
      isAddIndication: false,
      isDeleteIndication: false,
      isUpdateIndication: false,
      isSearchIndication: false,

      indicationNum: "",
      content: "",
      version: "",
      id: "",

      pageNum: "1",
      indicationMsgs: [],
      searchIndicationsMsgs: []
    };
  },

  methods: {
    //获取所有的指标信息
    async getIndication() {
      let result;
      const { version } = this;
      result = await reqListIndications({ version });
      if (result.status === 200) {
        this.indicationMsgs = result.data;
      }
    },

    //增加指标
    async addIndication() {
      let result;
      const { indicationNum, content, version } = this;

      result = await reqAddIndication({
        indicationNum,
        content,
        version
      });
      if (result.status === 200) {
        alert("添加成功");
      } else {
        alert(result.msg);
      }
      this.isAddIndication = false;
      this.$router.go(0);
    },

    //删除指标
    async deleteIndication() {
      let result;
      const { id } = this;
      result = await reqDeleteIndication({ id });
      if (result.status === 200) {
        alert("删除成功");
      }
      this.isDeleteIndication = false;
      this.$router.go(0);
    },

    //修改指标
    async updateIndication() {
      let result;
      const { id, indicationNum, content, version } = this;
      result = await reqUpdateIndication({
        id,
        indicationNum,
        content,
        version
      });
      if (result.status === 200) {
        alert("修改成功");
      }
      this.isUpdateIndication = false;
      this.$router.go(0);
    },

    //查询指标
    async search() {
      const { version } = this;
      let result = await reqListIndications({ version });
      if (result.status === 200) {
        this.searchIndicationsMsgs = result.data;
      } else {
        this.searchIndicationsMsgs = "";
      }
      this.isListIndication = false;
      this.isSearchIndication = true;
    },

    //获取当前指标id
    getIndicationId(id) {
      this.id = id;
      this.isDeleteIndication = true;
    },

    //获取当前指标的详细信息
    getThisIndicationMsg(id, indicationNum, content, version) {
      this.id = id;
      this.indicationNum = indicationNum;
      this.content = content;
      this.version = version;

      this.isUpdateIndication = true;
    }
  },

  mounted() {
    this.getIndication();
  }
};
</script>

<style scoped>
.search-btn {
  border: 1px solid #2795e9;
  float: left;
  outline: none;
  width: 80px;
  height: 30px;
  padding: 2px;
  text-align: center;
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

.list-indication {
  width: 980px;
  margin: 20px auto 0px;
}

.list-indication table {
  width: 980px;
  border-bottom: 1px solid rgb(165, 161, 161);
  border-top: 1px solid rgb(165, 161, 161);
  text-align: center;
  margin: 0 auto;
}

.list-indication tr:not(:last-child) {
  border-bottom: 1px dashed rgb(165, 161, 161);
}

.list-indication th {
  font-weight: bolder;
  padding: 10px 15px;
  background-color: #2795e9;
  color: white;
}

.list-indication td {
  padding: 8px;
}

.list-indication td button {
  outline: none;
  border: none;
  background: rgb(243, 239, 239);
}

.list-indication td button.update {
  color: rgb(15, 221, 125);
}

.list-indication td button.delete {
  color: red;
}

.list-indication td button:hover {
  background: white;
}

.alert-add-indication {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1200;
}

.alert-add-indication .add-indication {
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

.alert-add-indication .add-indication form {
  width: 308px;
  margin: 5px auto 20px;
}

.alert-add-indication .add-indication tr {
  padding: 5px;
  margin: 5px;
}

.alert-add-indication .add-indication tr td {
  text-align: center;
}

.alert-add-indication .add-indication tr td.indication-point {
  vertical-align: top;
}

.alert-add-indication .add-indication tr input[type="text"],
.alert-add-indication .add-indication tr input[type="password"] {
  border: 1px solid #ccc;
  height: 20px;
  padding: 2px;
  margin: 3px;
  width: 200px;
  outline: none;
}

.alert-add-indication .add-indication textarea {
  border: 1px solid #ccc;
  width: 200px;
  height: 50px;
  outline: none;
}

.alert-add-indication .add-indication p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
  margin-top: 5px;
}

.alert-add-indication .add-indication p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-add-indication .add-indication p button:last-child {
  float: right;
  background: red;
}

.alert-delete-indication {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  /* background: rgb(51, 50, 50); */
  z-index: 1200;
}

.alert-delete-indication .delete-indication {
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

.alert-delete-indication .delete-indication form {
  margin: 10px auto 20px;
  width: 210px;
}

.alert-delete-indication .delete-indication p {
  padding: 8px;
  text-align: center;
}

.alert-delete-indication .delete-indication p button {
  border: none;
  width: 50px;
  height: 25px;
  color: white;
  outline: none;
}

.alert-delete-indication .delete-indication p button:first-child {
  float: left;
  background: rgb(139, 133, 133);
}

.alert-delete-indication .delete-indication p button:last-child {
  float: right;
  background: red;
}

.alert-search-indication {
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
