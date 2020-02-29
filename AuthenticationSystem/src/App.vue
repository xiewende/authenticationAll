<template>
  <div>
    <div v-if="$route.meta.showGuide">
      <el-container>
        <SideGuide />

        <el-container>
          <el-header class="header">
            <p class="logo"><img src="./common/images/logo.png" alt="" /></p>
            <h1 class="title">
              GDOU-Engineering Education Authentication System
            </h1>
            <div class="loginout" @click="loginOut">退出登录</div>
          </el-header>

          <el-main>
            <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>
    </div>

    <div v-else>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import SideGuide from "./components/SideGuide";
import { reqLoginOut } from "./api";
import {mapState} from 'vuex'
export default {
  components: {
    SideGuide
  },

  methods: {
    async loginOut(){
      let result = await reqLoginOut()
      if(result.status === 200){
        alert('退出成功');
        // this.userInfo = null;
        window.localStorage.removeItem("name")
        this.$router.replace("/login");
      }
    }
  },

  computed: {
    ...mapState(['userInfo'])
  },
};
</script>

<style scoped>
.header {
  background: #2795e9;
}

.logo {
  margin-top: 5px;
  width: 25%;
  position: absolute;
  display: inline-block;
}

.logo img {
  height: 50px;
}

.title {
  position: absolute;
  display: inline-block;
  height: 60px;
  text-align: center;
  line-height: 60px;
  font-size: 26px;
  color: #ffffff;
  width: 75%;
  /* margin-left: -190px; */
}

.loginout {
  float: right;
  height: 60px;
  line-height: 60px;
  cursor: pointer;
  color: white;
}

.loginout:hover {
  color: #eb9090;
}
</style>
