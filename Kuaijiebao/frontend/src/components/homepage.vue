<template>
  <div>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">我要借款</el-menu-item>
      <el-menu-item index="3">我要理财</el-menu-item>
      <el-menu-item index="4">信用管理</el-menu-item>
      <el-menu-item index="5">数据统计</el-menu-item>
      <el-menu-item index="6">咨询</el-menu-item>
      <el-menu-item index="7">个人中心</el-menu-item>
      <el-menu-item index="8">登录/注册</el-menu-item>
    </el-menu>
    <p>欢迎使用快借宝！</p>
  </div>
    <div>
      <el-carousel height="500px" width="200px" type="card">
        <el-carousel-item v-for="item in imgList" :key="item" height="500px"  width="200px">
          <h3><img :src="item" alt=""> </h3>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      imgList: [
        require('../assets/a1.jpeg'),
        require('../assets/a2.jpeg'),
        require('../assets/a3.jpeg'),
        require('../assets/a4.jpeg'),
        require('../assets/a5.jpeg')
      ]
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      switch (key) {
        case '1':
          this.$router.push('/home')
          break
        case '2':
          this.$router.push('/borrow_money')
          break
        case '3':
          this.$router.push('/money_management')
          break
        case '4':
          this.$router.push('/borrow_money')
          break
        case '5':
          this.$router.push('/borrow_money')
          break
        case '6':
          this.$router.push('/borrow_money')
          break
        case '7':
          this.$router.push('/borrow_money')
          break
        case '8':
          this.$router.push('/login')
          break
      }
    }
  }
}

</script>
.el-carousel__item h3 {
color: #475669;
font-size: 14px;
opacity: 0.75;
line-height: 200px;
margin: 0;
}
<style scoped>

</style>
