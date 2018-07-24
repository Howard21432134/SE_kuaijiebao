<template>
  <div>
    <el-col :span="12">
      <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>理财产品管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1">选购理财产品</el-menu-item>
            <el-menu-item index="1-2">撤销理财产品</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="debt_management">
          <i class="el-icon-setting"></i>
          <span slot="title">我的债权</span>
        </el-menu-item>
        <el-submenu index="change_debt">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>债权交易</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="buy">购买债权</el-menu-item>
            <el-menu-item index="sell">出售债权</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
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
