<template>
  <div>
    <p style="margin-bottom: 20px; font-weight: 500;">修改密码</p>
    <el-input width="300px" placeholder="请输入原密码" style="width:25%" v-model="oldPassword" clearable></el-input>
    <el-row>
      <el-input width="300px" placeholder="请输入新密码" style="width:25%" v-model="newPassword1" clearable></el-input>
    </el-row>
    <el-row>
      <el-input width="300px" placeholder="请确认新密码" style="width:25%" v-model="newPasword2" clearable></el-input>
    </el-row>
    <p>邮箱验证</p>
    <el-input width="300px" placeholder="请输入邮箱" style="width:25%" v-model="email" clearable></el-input>
    <el-button @click.native.prevent="handleRegister">获得验证码</el-button>
    <el-row>
    <el-input width="300px" placeholder="请输入验证码" style="width:25%" v-model="code" clearable></el-input>
    </el-row>

    <el-row>
    <el-button @click.native.prevent="handleValidate">确认修改</el-button>
    <el-button>取消</el-button>
    </el-row>
  </div>
</template>
<script>
  import API from '../../api/api_user';
  export default {
    data(){
      return {
        oldPassword:'',
        newPassword1:'',
        newPassword2:'',
        email:'',
        code:'',
        radio:'1',
      };
    },
    methods: {
      handleRegister(){
        let user = window.localStorage.getItem('access-user');
        let username;
        if (user) {
          user = JSON.parse(user);
          username = user.username || '';
        }
        let request={
          username:username,
          email:this.email,
          elem:"PASS_WORD",
          item:this.newPassword1,
        };
        API.registerUserInfoModification(request,(response)=>{console.log(response)});
      },
      handleValidate(){
        let user = window.localStorage.getItem('access-user');
        let username;
        if (user) {
          user = JSON.parse(user);
          username = user.username || '';
        }
        let request={
          username:username,
          code:this.code,
        };
        API.validateUserInfoModification(request,(response)=>{console.log(response)});
      }
    }
  }
</script>
