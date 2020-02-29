<template>
  <el-aside class="side-nav">
    <el-menu class="el-menu-vertical-demo">
      <el-menu-item
        :class="{ active: isCurrent('/teacher') }"
        index="1"
        v-if="localData == '系统管理员'"
      >
        <div @click="goto('/teacher')">
          <span>教师管理</span>
        </div>
      </el-menu-item>

      <el-submenu
        index="2"
        v-if="
          localData == '大纲编撰者' ||
            localData == '系统管理员'
        "
      >
        <template slot="title">
          <span>课程管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="2-1" :class="{ active: isCurrent('/course') }">
            <div @click="goto('/course')">
              <span>课程设置</span>
            </div>
          </el-menu-item>
          <el-menu-item
            index="2-2"
            :class="{ active: isCurrent('/indication') }"
          >
            <div @click="goto('/indication')">
              <span>指标点设置</span>
            </div>
          </el-menu-item>
          <el-menu-item index="2-3" :class="{ active: isCurrent('/target') }">
            <div @click="goto('/target')">
              <span>课程目标设置</span>
            </div>
          </el-menu-item>
          <el-menu-item
            index="2-4"
            :class="{ active: isCurrent('/indicationcourse') }"
          >
            <div @click="goto('/indicationcourse')">
              <span>课程-指标点支撑度矩阵设置</span>
            </div>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-menu-item
        :class="{ active: isCurrent('/student') }"
        index="3"
        v-if="localData == '系统管理员'"
      >
        <div @click="goto('/student')">
          <span>学生管理</span>
        </div>
      </el-menu-item>

      <el-menu-item
        :class="{ active: isCurrent('/selcourse') }"
        index="4"
        v-if="localData == '系统管理员'"
      >
        <div @click="goto('/selcourse')">
          <span>课表管理</span>
        </div>
      </el-menu-item>

      <el-menu-item
        :class="{ active: isCurrent('/score') }"
        index="5"
        v-if="localData == '系统管理员'"
      >
        <div @click="goto('/score')">
          <span>成绩管理</span>
        </div>
      </el-menu-item>

      <el-submenu
        index="6"
        v-if="
          localData == '课程负责人' ||
            localData == '系统管理员'
        "
      >
        <template slot="title">
          <span>考核管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="6-1" :class="{ active: isCurrent('/exam') }">
            <div @click="goto('/exam')">
              <span>课程考核设置</span>
            </div>
          </el-menu-item>
          <el-menu-item
            index="6-2"
            :class="{ active: isCurrent('/examdetail') }"
          >
            <div @click="goto('/examdetail')">
              <span>考核细则比例设置</span>
            </div>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-submenu
        index="7"
        v-if="localData == '系统管理员'"
      >
        <template slot="title">
          <span>达成度查询</span>
        </template>
        <el-menu-item-group>
          <el-menu-item
            index="7-1"
            :class="{ active: isCurrent('/courseAchievement') }"
          >
            <div @click="goto('/courseAchievement')">
              <span>课程总体达成度查询</span>
            </div>
          </el-menu-item>

          <el-menu-item
            index="7-2"
            :class="{ active: isCurrent('/graduateIndividual') }"
          >
            <div @click="goto('/graduateIndividual')">
              <span>毕业个体达成度查询</span>
            </div>
          </el-menu-item>

          <el-menu-item
            index="7-3"
            :class="{ active: isCurrent('/graduateRequirement') }"
          >
            <div @click="goto('/graduateRequirement')">
              <span>毕业要求达成度查询</span>
            </div>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      localData1:'',
    }
  },

  methods: {
    goto(path) {
      this.$router.replace(path);
    },

    isCurrent(path) {
      return this.$route.path == path;
    }
  },

  computed: {
    localData(){
      this.localData1 = window.localStorage.getItem('name')
      return this.localData1
    }
    // ...mapState(["userInfo"])
  }
};
</script>

<style scoped>
.active {
  background: #2795e9;
  color: white;
}
</style>
