<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="homepage">首页</el-menu-item>
      <el-menu-item index="borrow_money">我要借款</el-menu-item>
      <el-menu-item index="money_management">我要理财</el-menu-item>
m      <el-menu-item index="credit_management">信用管理</el-menu-item>
      <el-menu-item index="data_statistics">数据统计</el-menu-item>
      <el-menu-item index="consult">咨询</el-menu-item>
      <el-menu-item index="personal_center">个人中心</el-menu-item>
      <el-menu-item index="login/regin">登录/注册</el-menu-item>
    </el-menu>

  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  computed: {
    actveIndex () {
      return this.$route.path.replace('/', '')
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(this);
      console.log(key, keyPath);
    }
  }
}

</script>

<style scoped>

</style>
