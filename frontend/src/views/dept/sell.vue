<template>
  <div>
    <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
      <el-col :span="24" class="toolbar" style="padding-bottom: 0;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-input v-model="filters.name" placeholder="请输入产品编号" auto-complete="off" @keyup.enter.native="fetchData"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="medium" v-on:click="fetchData">查询</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-table ref="multipleTable" :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="60"></el-table-column>
        <el-table-column prop="eNumber" label="债权编号" width="180" sortable></el-table-column>
        <el-table-column prop="eName" label="债权持有人" show-overflow-tooltip></el-table-column>
        <el-table-column prop="eIndustry" label="金额" width="180"></el-table-column>
        <el-table-column prop="eRange" label="利率" width="180"></el-table-column>
        <el-table-column prop="eModel" label="预计还款时间" width="180"></el-table-column>
        <el-table-column label="出售">
          <template slot-scope="scope">
            <el-button size="mini" @click.native="open" type="danger">出售</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--<div style="margin-top: 20px">
        <el-button @click="toggleSelection([tableData3[1], tableData3[2]])">切换第二、第三行的选中状态</el-button>
        <el-button @click="toggleSelection()">取消选择</el-button>
      </div>-->
      <el-col :span="24" class="toolbar">
        <el-pagination background
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="currentPage"
                       :page-sizes="[10, 50, 100, 200]"
                       :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total">
        </el-pagination>
      </el-col>
    </el-col>
  </div>
</template>
<script>
  import API from '../../api/api_enterprise'
  export default {
    methods: {
      open() {
        this.$alert('这是一段内容', '标题名称', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      }
    },

    data() {
      return {
        loading: false,
        keyword: "集团",
        total: 5,
        currentPage: 1,
        pageSize: 10,
        tableData: [{
          eNumber: 'A10001',
          eName: 'XXX',
          eIndustry: '1000',
          eRange: '4.68%',
          eModel: '2018-07-08',
        },
          {
            eNumber: 'A10001',
            eName: 'XXX',
            eIndustry: '3000',
            eRange: '5.68',
            eModel: '2018-07-08',

          }],
        multipleSelection: [],
        filters: {
          name: ''
        }
      }
    },
    created: function(){
      // 组件创建完后获取数据，
      // 此时 data 已经被 observed 了
      //this.fetchData();  //调用接口获取动态数据

    },
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(function(row) {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      fetchData(){ //获取数据
        let that = this;
        let params = {
          curr: that.currentPage,
          pageSize: that.pageSize,
          keywords: that.filters.name
        };
        that.loading = true;
        API.findList(params).then(function(result){
          that.loading = false;
          that.total = result.count;
          that.currentPage = result.curr;
          that.tableData = result.data;
        }).catch(function (error) {
          that.loading = false;
          console.log(error);
        });
      },
      handleSizeChange(val){
        this.pageSize = val;
        this.currentPage = 1;
        this.fetchData();
//        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val){
        this.currentPage = val;
        this.fetchData();
//        console.log(`当前页: ${val}`);
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }
</script>
<style>
  .el-table th {
    text-align: center;
  }

  .el-table tbody tr td:first-child {
    text-align: center;
  }
</style>
