<template>
  <div>
    <p style="margin-bottom: 20px; font-weight: 500;">修改密码</p>
    <el-input width="300px" placeholder="请输入原密码" style="width:25%" v-model="input10" clearable></el-input>
    <p>请选择验证方式</p>
    <el-radio v-model="radio" label="1">邮箱验证</el-radio>
    <el-radio v-model="radio" label="2">短信验证</el-radio>
    <el-button>验证</el-button>
    <el-row>
    <el-input width="300px" placeholder="请输入验证码" style="width:25%" v-model="input10" clearable></el-input>
    </el-row>
    <el-row>
    <el-input width="300px" placeholder="请输入新密码" style="width:25%" v-model="input10" clearable></el-input>
    </el-row>
    <el-row>
    <el-input width="300px" placeholder="请确认新密码" style="width:25%" v-model="input10" clearable></el-input>
    </el-row>
    <el-row>
    <el-button>确认修改</el-button>
    <el-button>取消</el-button>
    </el-row>
  </div>
</template>
<script>
  export default {
    data(){
      return {
        input10:'',
        radio:'1',
      };
    }
  }
</script>
