<template>
<div>
  <div>
    <p>用户昵称</p>
    <el-input placeholder="$nickname" v-model="nickname" style="width:25%" clearable></el-input>
    <p>电话</p>
    <el-input placeholder="$phone" v-model="phonenum" style="width:25%" clearable></el-input>
      <el-button>验证</el-button>
    <p>邮箱</p>
    <el-input placeholder="$email" v-model="email" style="width:25%" clearable></el-input>
    <el-button>验证</el-button>
    <p>工作</p>
    <el-input placeholder="$job" v-model="job" style="width:25%" clearable></el-input>
    <p>家庭地址</p>
    <el-input placeholder="$address" v-model="address" style="width:25%" clearable></el-input>
    <p>年收入</p>
    <el-input placeholder="$income" v-model="income"  style="width:25%" clearable></el-input>
    <p>个人说明</p>
    <el-input placeholder="$introduction" v-model="introduction" style="width:25%" clearable></el-input>
    <el-row>
      <el-button @click.native.prevent="handleUpdate">确定</el-button>
      <el-button>取消</el-button>
    </el-row>

  </div>
</div>
</template>

<script>
    import API from '../../api/api_user';
    export default {
      name: "inform_setting",
      data() {
        return {
          allowSubmit:false,

          nickname:' ',
          phone: ' ',
          email: ' ',
          job: ' ',
          address: ' ',
          income: ' ',
          introduction: ' '

        }
      },
      mounted() {
        this.loadData();
      },
      methods: {
        loadData() {
          API.getUserByUserId(1, (user) => {
            this.nickname = user.nickname;
            this.phone = user.phone;
            this.email = user.email;
            this.job = user.job;
            this.address = user.address;
            this.income = user.income;
            this.introduction = user.introduction;
          });
        },
        handleUpdate(){



          let user={
            nickname:this.nickname,
            phone: this.phone,
            email: this.email,
            job: this.job,
            address: this.address,
            income: this.income,
            introduction: this.introduction
          };
          API.updateUser(1,user,this.loadData);
        }
      }
    }
</script>

<style scoped>

</style>
