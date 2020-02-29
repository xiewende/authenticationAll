<template>
  <div class="wrap">
    <div class="container">
      <h1>Welcome</h1>
      <form>
        <input
          id="username-l"
          type="text"
          placeholder="账号"
          v-model="username"
        />
        <input
          id="password-l"
          type="password"
          placeholder="密码"
          v-model="password"
        />
        <div>
          <input
            type="text"
            id="checkCode"
            placeholder="验证码不区分大小写"
            v-model="checkCode_in"
          />
          <canvas id="canvas" @click="check"></canvas>
        </div>
        <input id="submit-l" type="button" value="登录" @click="login" />
      </form>
    </div>
    <ul>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
    </ul>
  </div>
</template>

<script>
import { reqLogin } from "../api";

export default {
  data() {
    return {
      username: "",
      password: "",
      checkCode_in: "",

      show_num: [],

      user:[],
    };
  },

  methods: {
    async login() {
      var checkCode_out = this.show_num.join("");
      var checkCode_in = this.checkCode_in;

      var username = this.username;
      var password = this.password;

      let result;

      if (checkCode_in.toLowerCase() != checkCode_out.toLowerCase()) {
        alert("验证码错误");
      } else {
        result = await reqLogin(username, password);
        if (result.status === 200) {
          this.user = result.data;
          // 将user保存到vuex的state
          // this.$store.dispatch("recordUser", user);
          var storage = window.localStorage
          storage.setItem("name",this.user.role.name)

          console.log('登录成功1')

          if (this.user.role.name == "系统管理员") {
            console.log('登录成功')
            this.$router.replace("/teacher");

          } else if (this.user.role.name == "大纲编撰者") {
            this.$router.replace("/course");

          } else if(this.user.role.name == "课程负责人"){
            
            this.$router.replace("/exam");
          }
        }
      }
    },

    check() {
      var canvas_width = document.getElementById("canvas").clientWidth;
      var canvas_height = document.getElementById("canvas").clientHeight;
      var canvas = document.getElementById("canvas"); //获取到canvas的对象
      var context = canvas.getContext("2d");
      canvas.width = canvas_width;
      canvas.height = canvas_height;
      var sCode =
        "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m";
      var aCode = sCode.split(",");
      var aLength = aCode.length; //获取到数组的长度
      this.show_num = [];

      for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength); //获取到随机的索引值
        var deg = (Math.random() * 30 * Math.PI) / 180; //产生0~30之间的随机弧度
        var txt = aCode[j]; //得到随机的一个内容
        this.show_num[i] = txt;
        var x = 10 + i * 20; //文字在canvas上的x坐标
        var y = 20 + Math.random() * 8; //文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = this.randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
      }
      //验证码上显示线条
      for (var i = 0; i <= 5; i++) {
        context.strokeStyle = this.randomColor();
        context.beginPath();
        context.moveTo(
          Math.random() * canvas_width,
          Math.random() * canvas_height
        );
        context.lineTo(
          Math.random() * canvas_width,
          Math.random() * canvas_height
        );
        context.stroke();
      }
      //验证码上显示小点
      for (var i = 0; i <= 30; i++) {
        context.strokeStyle = this.randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
      }
    },

    randomColor() {
      //得到随机的颜色值
      var r = Math.floor(Math.random() * 256);
      var g = Math.floor(Math.random() * 256);
      var b = Math.floor(Math.random() * 256);
      return "rgb(" + r + "," + g + "," + b + ")";
    }
  },

  mounted() {
    this.check();
  }
};
</script>

<style scoped>
.wrap {
  width: 100%;
  height: 100%;
  padding: 40px 0;
  position: fixed;
  margin-top: 0px;
  opacity: 0.8;
  background: linear-gradient(to bottom right, #5097a3, #53b5e3);
}

.container {
  width: 60%;
  margin: 0 auto;
}

.container h1 {
  text-align: center;
  color: #ffffff;
  font-weight: 500;
}

.container input {
  width: 320px;
  display: block;
  height: 36px;
  border: 0;
  outline: 0;
  padding: 6px 10px;
  line-height: 24px;
  margin: 32px auto;
  transition: all 0s ease-in 0.1ms;
}

.container input {
  background-color: #ffffff;
  font-size: 16px;
  color: #50a3a2;
  transition: width 0.5s;
}

.container input[type="button"] {
  font-size: 16px;
  letter-spacing: 2px;
  color: #666666;
  background-color: #ffffff;
  transition: width 0.5s;
  width: 340px;
  background: yellow;
  color: rgb(238, 12, 12);
}

.container > form > input:focus {
  width: 400px;
}

.container input[type="button"]:hover {
  cursor: pointer;
  width: 400px;
}

.wrap ul {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -10;
}

.wrap ul li {
  list-style-type: none;
  display: block;
  position: absolute;
  bottom: -120px;
  width: 15px;
  height: 15px;
  z-index: -8;
  background-color: rgba(255, 255, 255, 0.15);
  animation: square 25s infinite;
}

.wrap ul li:nth-child(1) {
  left: 0;
  animation-duration: 10s;
}

.wrap ul li:nth-child(2) {
  width: 40px;
  height: 40px;
  left: 10%;
  animation-duration: 15s;
}

.wrap ul li:nth-child(3) {
  left: 20%;
  width: 25px;
  height: 25px;
  animation-duration: 12s;
}

.wrap ul li:nth-child(4) {
  width: 50px;
  height: 50px;
  left: 30%;
  animation-delay: 3s;
  animation-duration: 12s;
}

.wrap ul li:nth-child(5) {
  width: 60px;
  height: 60px;
  left: 40%;
  animation-duration: 10s;
}

.wrap ul li:nth-child(6) {
  width: 75px;
  height: 75px;
  left: 50%;
  animation-delay: 7s;
}

.wrap ul li:nth-child(7) {
  left: 60%;
  animation-duration: 8s;
}

.wrap ul li:nth-child(8) {
  width: 90px;
  height: 90px;
  left: 70%;
  animation-delay: 4s;
}

.wrap ul li:nth-child(9) {
  width: 100px;
  height: 100px;
  left: 80%;
  animation-duration: 20s;
}

.wrap ul li:nth-child(10) {
  width: 120px;
  height: 120px;
  left: 90%;
  animation-delay: 6s;
  animation-duration: 30s;
}

@keyframes square {
  0% {
    transform: translateY(0);
  }

  100% {
    bottom: 400px;
    transform: rotate(600deg);
    transform: translateY(-500);
  }
}

@-webkit-keyframes square {
  0% {
    transform: translateY(0);
  }

  100% {
    bottom: 400px;
    transform: rotate(600deg); /* 主要 */
    transform: translateY(-500);
  }
}

#checkCode {
  width: 180px;
  margin-left: 290px;
  background-color: #ffffff;
  font-size: 16px;
  color: #50a3a2;
  transition: width 0.5s;
}
#checkCode:focus {
  width: 200px;
}

#canvas {
  position: absolute;
  width: 100px;
  height: 43px;
  left: 838px;
  top: 245px;
  background-color: white;
  border-radius: 5px;
}
</style>
