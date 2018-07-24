<template>
  <div>
    <el-col :span="12">
      <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
        <el-menu-item index="money_manage_statistics">
          <i class="el-icon-menu"></i>
          <span slot="title">理财统计</span>
        </el-menu-item>
        <el-menu-item index="borrow/lending_statistic">
          <i class="el-icon-setting"></i>
          <span slot="title">借还款统计</span>
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
