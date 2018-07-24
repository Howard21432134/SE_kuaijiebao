<template>
  <div>
    <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
      <el-col :span="24" class="toolbar" style="padding-bottom: 0;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-input v-model="filters.number" placeholder="请输入银行卡号" auto-complete="off" @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="medium" v-on:click="handleSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="medium" @click="showAddDialog">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <!--表格数据-->
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="mId" v-if="idShow" label="银行卡号"></el-table-column>
        <el-table-column prop="mNumber" label="银行卡号" width="180" sortable></el-table-column>
        <el-table-column prop="mType" label="开卡银行" width="180"></el-table-column>
        <el-table-column prop="mContent" label="持卡人" show-overflow-tooltip></el-table-column>
        <el-table-column prop="eRemark" label="余额" width="180"></el-table-column>
        <el-table-column prop="createTime" label="开通日期" width="180"></el-table-column>
        <el-table-column prop="updateTime" label="绑定日期" width="180"></el-table-column>
        <el-table-column prop="recordStatus" label="状态" width="180"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">解绑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-col :span="24" class="toolbar">
        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="currentPage" :page-sizes="[10, 50, 100, 200]" :page-size="10"
                       layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </el-col>
    </el-col>

    <el-dialog title="添加银行卡" :close-on-press-escape="false" :close-on-click-modal="false" :visible.sync="dialogAddVisible" :before-close="handleClose">
      <el-form :model="form">
        <el-form-item label="银行卡号：" :label-width="formLabelWidth">
          <el-input v-model="form.number" placeholder="请填写银行卡号" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="开卡银行：" :label-width="formLabelWidth">
          <el-select v-model="form.type" placeholder="请选择开卡银行">
            <el-option label="交通银行" value="receive"></el-option>
            <el-option label="中国银行" value="forward"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="持卡人：" :label-width="formLabelWidth">
          <el-input v-model="form.number" placeholder="持卡人" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号：" :label-width="formLabelWidth">
          <el-input v-model="form.number" placeholder="请填写该银行卡所绑定手机号" auto-complete="off"></el-input>
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
        tableData: [{
          mId: 1,
          mNumber: '10001',
          mType: '中国银行',
          mContent: 'XXX',
          eRemark: '10000',
          createTime: '2016-03-27',
          updateTime: '2016-03-27',
          recordStatus: '正常'
        },
          {
            mId: 2,
            mNumber: '10002',
            mType: '交通银行',
            mContent: 'XXX',
            eRemark: '2000',
            createTime: '2016-03-27',
            updateTime: '2016-03-27',
            recordStatus: '冻结'
          }],
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
