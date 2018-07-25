<template>
<div>
  <div>
    <p>用户昵称</p>
    <el-input placeholder="$nickname" v-model="nickname" style="width:25%" clearable></el-input>
    <p>电话</p>
    <el-input placeholder="$phonenum" v-model="phonenum" style="width:25%" clearable></el-input>
      <el-button>验证</el-button>
    <p>邮箱</p>
    <el-input placeholder="$email" v-model="email" style="width:25%" clearable></el-input>
    <el-button>验证</el-button>
    <p>工作</p>
    <el-input placeholder="$job" v-model="job" style="width:25%" clearable></el-input>
    <p>家庭地址</p>
    <el-input placeholder="$address" v-model="address" style="width:25%" clearable></el-input>
    <p>年收入</p>
    <el-input placeholder="$income" v-model="income"  style="width:25%" clearable></el-input>
    <p>个人说明</p>
    <el-input placeholder="$discription" v-model="discription" style="width:25%" clearable></el-input>
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
          nickname : 'imnickname',
          phonenum : 'fd',
          email : ' ',
          job : ' ',
          address : ' ',
          income : ' ',
          discription : ' '

        }
      }

    }
</script>

<style scoped>

</style>
