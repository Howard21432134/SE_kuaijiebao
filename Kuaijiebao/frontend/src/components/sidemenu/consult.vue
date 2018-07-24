<template>
  <div>
    <el-col :span="12">
      <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
        <el-menu-item index="automatical">
          <i class="el-icon-menu"></i>
          <span slot="title">常见问题咨询</span>
        </el-menu-item>
        <el-menu-item index="humananity">
          <i class="el-icon-setting"></i>
          <span slot="title">在线咨询</span>w
        </el-menu-item>
      </el-menu>
    </el-col>

  </div>
</template>

<script>
export default {
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}

</script>

<style scoped>

</style>
