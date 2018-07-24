<template>
  <div>
    <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
      <el-col :span="24" class="toolbar" style="padding-bottom: 0;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-button type="primary" size="medium" @click="showAddDialog">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <!--表格数据-->
      <el-col :span="24" class="toolbar">
        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="currentPage" :page-sizes="[10, 50, 100, 200]" :page-size="10"
                       layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </el-col>
    </el-col>

    <el-dialog title="添加借款申请" :close-on-press-escape="false" :close-on-click-modal="false" :visible.sync="dialogAddVisible" :before-close="handleClose">
      <el-form :model="form">
        <el-form-item label="借款金额：" :label-width="formLabelWidth">
          <el-input v-model="form.number" placeholder="请填写借款金额" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="借款利率：" :label-width="formLabelWidth">
          <el-input v-model="form.number" placeholder="请填写借款利率" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="申请截止时间：" :label-width="formLabelWidth">
          <el-date-picker v-model="value1" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计还款时间：" :label-width="formLabelWidth">
          <el-date-picker v-model="value1" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="借款说明：" :label-width="formLabelWidth">
          <el-input v-model="form.number" placeholder="请填写借款说明" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editFormSubmit">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
  export default {
    name: 'mission',
    data: function(){
      return {
        loading: false,
        radio: '1',
        idShow: false,
        keyword: "集团",
        total: 2,
        currentPage: 1,
        pageSize: 10,
        form: {
          number: '',
          type: '',
          content: '1',
          remark: '',
          createTime: '',
        },
        formLabelWidth: '120px',
        dialogAddVisible: false,
        filters: {
          number: ''
        }
      };
    },
    methods: {
      handleSearch(){
        console.info(this.filters.number);
      },
      handleDetail(index, row) {
        console.log(index, row);
        console.log(row.mId);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      handleSizeChange(val){
//        this.pageSize = val;
//        this.currentPage = 1;
      },
      handleCurrentChange(val){
//        this.currentPage = val;
      },
      showAddDialog(){
        this.dialogAddVisible = true;
      },
      handleClose(done){  //关闭弹窗
        done();
      }
    }
  }
</script>
