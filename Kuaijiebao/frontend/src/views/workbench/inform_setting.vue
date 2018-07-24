<template>
<div>
  <div>
    <p>用户昵称</p>
    <el-input placeholder="用户昵称" v-model="nickname" style="width:25%" clearable></el-input>
    <p>电话</p>
    <el-input placeholder="电话" v-model="phonenum" style="width:25%" clearable></el-input>
      <el-button>验证</el-button>
    <p>邮箱</p>
    <el-input placeholder="邮箱" v-model="email" style="width:25%" clearable></el-input>
    <el-button>验证</el-button>
    <p>工作</p>
    <el-input placeholder="工作" v-model="job" style="width:25%" clearable></el-input>
    <p>家庭地址</p>
    <el-input placeholder="家庭地址" v-model="address" style="width:25%" clearable></el-input>
    <p>年收入</p>
    <el-input placeholder="年收入" v-model="income"  style="width:25%" clearable></el-input>
    <p>个人说明</p>
    <el-input placeholder="个人说明" v-model="discription" style="width:25%" clearable></el-input>
    <el-row>
      <el-button>确定</el-button>
      <el-button>取消</el-button>
    </el-row>

  </div>
</div>
</template>

<script>
    export default {
        name: "inform_setting",
      data() {
        return {
          nickname : '',
          phonenum : '',
          email : '',
          job : '',
          address : '',
          income : '',
          discription : ''

        }
      }

    }
</script>

<style scoped>

</style>
