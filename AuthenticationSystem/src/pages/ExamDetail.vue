<template>
  <div>
    <div class="top-container">
      <div class="left">
        <span>
          考核方式
          <select v-model="examtypeId">
            <option
              v-for="(examType, index) in examTypes"
              :key="index"
              :value="examType.id"
            >
              {{ examType.name }}
            </option>
          </select>
        </span>

        <span>
          目标编号
          <select v-model="targetId">
            <option
              v-for="(target, index) in targets"
              :key="index"
              :value="target.id"
            >
              {{ target.targerNum }}
            </option>
          </select>
        </span>

        <span>
          版本
          <input type="text" v-model="version" />
        </span>

        <span>
          <button @click="getExamDetail">查询</button>
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
          <th>考核方式细则</th>
          <th>分值 / 权重</th>
        </tr>

        <tr v-for="(examDetail, index) in examDetails" :key="index">
          <td>
            {{ examDetail.name }}
          </td>
          <td>
            {{ examDetail.score || examDetail.weight }}
          </td>
        </tr>
      </table>
    </div>

    <!-- 添加 -->
    <div class="main" v-if="isAddShow">
      <table>
        <tr>
          <th>考核方式细则</th>
          <th>分值 / 权重</th>
        </tr>

        <tr>
          <td>
            <input type="text" v-model="examDetail1" class="examDetail" />
          </td>
          <td>
            <input type="text" class="weight" v-model="weight1" />
          </td>
        </tr>

        <tr>
          <td>
            <input type="text" v-model="examDetail2" class="examDetail" />
          </td>
          <td>
            <input type="text" class="weight" v-model="weight2" />
          </td>
        </tr>

        <tr>
          <td>
            <input type="text" v-model="examDetail3" class="examDetail" />
          </td>
          <td>
            <input type="text" class="weight" v-model="weight3" />
          </td>
        </tr>

        <tr>
          <td>
            <input type="text" v-model="examDetail4" class="examDetail" />
          </td>
          <td>
            <input type="text" class="weight" v-model="weight4" />
          </td>
        </tr>

        <tr>
          <td>
            <input type="text" v-model="examDetail5" class="examDetail" />
          </td>
          <td>
            <input type="text" class="weight" v-model="weight5" />
          </td>
        </tr>
      </table>

      <div class="save"><button @click="saveAdd">保存</button></div>
    </div>
  </div>
</template>

<script>
import {
  reqExamTypes,
  reqTargets,
  reqListExamDetails,
  reqAddExamDetails
} from "../api";

export default {
  data() {
    return {
      isAllShow: true,
      isTitleShow: true,
      isAddShow: false,

      version: "", //版本
      examtypeId: "",
      targetId: "",
      examDetail: "",

      examTypes: [], //考核方式
      targets: [], //目标信息
      examDetails: [],

      weight1: "",
      weight2: "",
      weight3: "",
      weight4: "",
      weight5: "",

      examDetail1: "",
      examDetail2: "",
      examDetail3: "",
      examDetail4: "",
      examDetail5: ""
    };
  },

  methods: {
    //获取考核方式
    async getExamTypes() {
      let result = await reqExamTypes();
      if (result.status === 200) {
        this.examTypes = result.data;
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
    async getExamDetail() {
      let result = await reqListExamDetails(
        this.targetId,
        this.examtypeId,
        this.version
      );
      if (result.status === 200) {
        this.examDetails = result.data;
      } else {
        this.examDetails = "";
      }

      this.isAllShow = true;
      this.isAddShow = false;
    },

    //保存添加的数据
    async saveAdd() {
      let examDetails = [
        this.examDetail1,
        this.examDetail2,
        this.examDetail3,
        this.examDetail4,
        this.examDetail5
      ];
      let weights = [
        this.weight1,
        this.weight2,
        this.weight3,
        this.weight4,
        this.weight5
      ];

      let nameList = JSON.stringify(examDetails);
      let vualeList = JSON.stringify(weights);

      let result = await reqAddExamDetails(
        this.examtypeId,
        this.targetId,
        this.version,
        nameList,
        vualeList
      );
      if (result.status === 200) {
        alert("添加成功");
        this.examDetail1 = ""
        this.examDetail2 = ""
        this.examDetail3 = ""
        this.examDetail4 = ""
        this.examDetail5 = ""

        this.weight1 = ""
        this.weight2 = ""
        this.weight3 = ""
        this.weight4 = ""
        this.weight5 = ""

      } else {
        alert(result.msg);
      }

      this.isAllShow = true;
      this.isAddShow = false;
    },

    //点击添加按钮
    clickAddBtn() {
      this.isAllShow = false;
      this.isAddShow = true;
    }
  },

  mounted() {
    this.getExamTypes();
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
