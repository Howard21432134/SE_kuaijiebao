<template>
  <div>
    <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
      <!--表格数据-->
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="mId" v-if="idShow" label="申请编号"></el-table-column>
        <el-table-column prop="mNumber" label="申请编号" width="180" sortable></el-table-column>
        <el-table-column prop="mType" label="申请金额" width="180"></el-table-column>
        <el-table-column prop="mContent" label="利率" show-overflow-tooltip></el-table-column>
        <el-table-column prop="eRemark" label="借款说明" width="180"></el-table-column>
        <el-table-column prop="createTime" label="申请截止日期" width="180"></el-table-column>
        <el-table-column prop="updateTime" label="预计还款日期" width="180"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="showAddDialog">修改</el-button>
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

    <el-dialog title="修改借款申请" :close-on-press-escape="false" :close-on-click-modal="false" :visible.sync="dialogAddVisible" :before-close="handleClose">
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
        <el-button type="primary" @click.native="editFormSubmit">提交修改</el-button>
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
          mType: '2000',
          mContent: '4.52%',
          eRemark: 'XXXXX',
          createTime: '2016-03-27',
          updateTime: '2016-05-27',

        },
          {
            mId: 1,
            mNumber: '10002',
            mType: '2500',
            mContent: '5.52%',
            eRemark: 'XXXXX',
            createTime: '2016-04-12',
            updateTime: '2016-06-27',
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
