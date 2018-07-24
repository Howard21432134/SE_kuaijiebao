<template>
  <el-carousel height="500px" width="200px" type="card">
    <el-carousel-item v-for="item in imgList" :key="item" height="500px"  width="200px">
      <h3><img :src="item" alt=""> </h3>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  data () {
    return {
      imgList: [
        require('../../assets/a1.jpeg'),
        require('../../assets/a2.jpeg'),
        require('../../assets/a3.jpeg'),
        require('../../assets/a4.jpeg'),
        require('../../assets/a5.jpeg')
      ]
    }
  },
  components: {
  }
}
</script>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
