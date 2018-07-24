<template>
  <div>
    <el-col :span="12">
      <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
        <el-menu-item index="debt_application">
          <i class="el-icon-menu"></i>
          <span slot="title">个人信息管理</span>
        </el-menu-item>
        <el-menu-item index="debt_management">
          <i class="el-icon-setting"></i>
          <span slot="title">个人安全管理</span>
        </el-menu-item>
        <el-menu-item index="debt_management">
          <i class="el-icon-setting"></i>
          <span slot="title">银行卡管理</span>
        </el-menu-item>
        <el-menu-item index="debt_management">
          <i class="el-icon-setting"></i>
          <span slot="title">退出登录</span>
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
