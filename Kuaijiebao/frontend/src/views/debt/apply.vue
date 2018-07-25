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
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="mId" v-if="idShow" label="申请编号"></el-table-column>
        <el-table-column prop="id" label="申请编号" width="180" sortable></el-table-column>
        <el-table-column prop="sum" label="申请金额" width="180"></el-table-column>
        <el-table-column prop="rate" label="利率" show-overflow-tooltip></el-table-column>
        <el-table-column prop="content" label="借款说明" width="180"></el-table-column>
        <el-table-column prop="validTime" label="申请截止日期" width="180"></el-table-column>
        <el-table-column prop="expectDischargeTime" label="预计还款日期" width="180"></el-table-column>
      </el-table>


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
          <el-input v-model="form.sum" placeholder="请填写借款金额" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="借款利率：" :label-width="formLabelWidth">
          <el-input v-model="form.rate" placeholder="请填写借款利率" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="申请截止时间：" :label-width="formLabelWidth">
          <el-date-picker v-model="form.date1" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计还款时间：" :label-width="formLabelWidth">
          <el-date-picker v-model="form.date2" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="借款说明：" :label-width="formLabelWidth">
          <el-input v-model="form.contents" placeholder="请填写借款说明" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click.native.prevent="handleCreateDebt">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
  import API from '../../api/api_user';
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
        tableData: [
          /*
          {
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
          }
          */
          ],
        form: {
          number: '',
          type: '',
          content: '1',
          remark: '',
          createTime: '',
          id:'',//debtId
          userId:1,
          sum:'',
          date1:'',
          date2:'',
          rate:'',
          contents:'',
        },
        formLabelWidth: '120px',
        dialogAddVisible: false,
        filters: {
          number: ''
        }
      };
    },
    mounted(){
      this.loadData();
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
      },
      handleCreateDebt(){
        let user = window.localStorage.getItem('access-user');
        let userId;
        if (user) {
          user = JSON.parse(user);
          userId = user.userId || '';
          this.form.userId=userId;
        }
        API.AddDebtActivity(this.form,(response)=>{console.log(response);});
        this.loadData();
        this.dialogAddVisible = false;
      },
      loadData() {
        let user = window.localStorage.getItem('access-user');
        let userId;
        if (user) {
          user = JSON.parse(user);
          userId = user.userId || '';
        }
        API.ShowDebtByUserActivity(userId, (response) => {
          this.tableData=response;
        });
      },
    }
  }
</script>
