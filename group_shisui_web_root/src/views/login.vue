<template>
<div>
  <el-form>
    <h3 class="title">拾穗</h3>
    <el-form-item>
      <el-input type="text" placeholder="用户名称" v-model="user.userName"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" placeholder="密码" v-model="user.password"></el-input>
    </el-form-item>
    <el-form-item>
      <div>
        <el-input placeholder="请输入验证码" v-model="user.verifyCode"></el-input>
        <div id="verifyCode" @click="refreshImg">
          <img v-if="imgData.length>0" :src="'data:image/png;base64,'+imgData">
        </div>
      </div>
    </el-form-item>
    <el-form-item>
      <el-button @click.native.prevent="handleLogin">
        <span v-if="!loading">登 录</span>
        <span v-else>登 录 中...</span>
      </el-button>
    </el-form-item>
  </el-form>
</div>
  <!-- <div class="login">
    <el-form ref="loginForm" :model="user" :rules="loginRules" class="login-form">
      <h3 class="title">拾穗</h3>
      <el-form-item prop="username">
        <el-input v-model="user.userName" type="text" auto-complete="off" placeholder="账号">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="user.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input

          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" />
        </div>
      </el-form-item>
      <el-checkbox  style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
    <div class="el-login-footer">
      <span>Copyright © 2018-2019 ruoyi.vip All Rights Reserved.</span>
    </div>
  </div> -->
</template>
<script>
import {login,getImageData} from "@/api/login";
import {findGoodsList} from "@/api/goods";

export default {
  name: "login",
  data () {
    return {
      loading: false,
      user: {
        userName: '',
        password: '',
        token: '',
        uuid: '',
        imgData: '',
        verifyCode: ''
      },
      imgData: '',
      redirect: undefined,
      isImgGet: false // 用于请求验证码接口有返回之后，才能再次请求验证码
    }
  },
  created () {
    // this.login();
    // this.findGoodsList();
  },
  mounted () {
    this.getImage();
  },
  methods: {
    // 登录
    handleLogin () {
      this.loading = true;
      let param = {
        userName: this.user.userName,
        password: this.user.password,
        verifyCode: this.user.verifyCode,
        uuid: this.user.uuid
      }
      login(param).then(res => {
        console.log(res,'login-res')
        this.token = res.data.token;
        console.log(this.token)
        window.localStorage.setItem('token',this.token);
        // window.localStorage.setItem('Authorization',this.token);
        this.$router.push({ path: this.redirect || "/goods" });
      })
      this.loading = false;
    },
    // 获取验证码
    getImage(){
      getImageData().then(res => {
        this.user.uuid = res.data.uuid;
        this.user.imgData = res.data.base64Image;
        this.imgData = res.data.base64Image;
        this.isImgGet = true;
      })
    },
    // 刷新验证码
    refreshImg(){
      if (this.isImgGet) {
        getImage();
        // getImageData().then(res => {
        //   this.user.uuid = res.data.uuid;
        //   this.user.imgData = res.data.base64Image;
        //   this.imgData = res.data.base64Image;
        //   this.isImgGet = true;
        // });
      }
    }
    // login () {
    //   console.log('11111111')
    //   let param = {
    //     userName: this.user.userName,
    //     password: this.user.password
    //   }
    //   login(param).then(res => {
    //     console.log(res,'login-res')
    //   })
    // },
    // findGoodsList(){
    //   findGoodsList().then(res => {
    //     console.log(res,'res')
    //   })
    // },
    
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  // background-image: url("../assets/image/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
</style>
