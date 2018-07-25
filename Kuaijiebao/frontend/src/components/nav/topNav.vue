<template>
  <el-row class="container">
    <!--头部-->
    <el-col :span="24" class="topbar-wrap">
      <div class="topbar-logo topbar-btn">
        <a href="/"><img src="../../assets/logo.png" style="padding-left:8px;"></a>
      </div>
      <div class="topbar-logos">
        <a href="/" style="color: #fff;">快借宝系统</a>
      </div>
      <div class="topbar-title">
        <el-row v-show="$store.state.topNavState==='home'">
          <el-col :span="24">
            <el-menu :default-active="defaultActiveIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" :router="true">
              <el-menu-item index="/">工作台</el-menu-item>
              <el-menu-item index="/debtManager">我要借款</el-menu-item>
              <el-menu-item index="/enterpriseManager">我要理财</el-menu-item>
              <el-menu-item index="/pcManager">信用管理</el-menu-item>
              <el-menu-item index="/statisticsManager">数据统计</el-menu-item>
              <el-menu-item index="/counselManager">咨询</el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
        <el-row v-show="$store.state.topNavState==='debt'">
          <el-col :span="24">
            <el-menu :default-active="defaultActiveIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" :router="true">
              <el-menu-item index="/debtManager">借款申请</el-menu-item>
              <el-menu-item index="/mydebtManager">借款管理</el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
        <el-row v-show="$store.state.topNavState==='enterprise'">
          <el-col :span="24">
            <el-menu :default-active="defaultActiveIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" :router="true">
              <el-menu-item index="/enterpriseManager">理财产品管理</el-menu-item>
              <el-menu-item index="/vehicleManager">我的债权</el-menu-item>
              <el-menu-item index="/deptManager">债权交易</el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
        <el-row v-show="$store.state.topNavState==='pc'">
          <el-col :span="24">
            <el-menu :default-active="defaultActiveIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" :router="true">
              <el-menu-item index="/pcManager">信用查询</el-menu-item>
              <el-menu-item index="/mypcManager">信用额度管理</el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
        <el-row v-show="$store.state.topNavState==='statistics'">
          <el-col :span="24">
            <el-menu :default-active="defaultActiveIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" :router="true">
              <el-menu-item index="/statisticsManager">借贷款统计</el-menu-item>
              <el-menu-item index="/fpstatManager">理财统计</el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
        <el-row v-show="$store.state.topNavState==='counsel'">
          <el-col :span="24">
            <el-menu :default-active="defaultActiveIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" :router="true">
              <el-menu-item index="/counselManager">常见问题</el-menu-item>
              <el-menu-item index="/onlinecounselManager">在线咨询</el-menu-item>
            </el-menu>
          </el-col>
        </el-row>

      </div>
      <div class="topbar-account topbar-btn">
        <el-dropdown trigger="click">
          <span class="el-dropdown-link userinfo-inner">
            <i class="iconfont icon-user"></i> {{nickname}}   <i class="el-icon-caret-bottom"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="jumpTo('/user/profile')"><span style="color: #555;font-size: 14px;">个人信息</span></div>
            </el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-col>
  </el-row>
</template>
<script>
  import 'element-ui/lib/theme-chalk/display.css';
  import {road} from '../../road.js'

  export default {
    data(){
      return {
        loading: false,
        companyName: '',
        nickname: '',
        defaultActiveIndex: '/',
        homeMenu: false,
        messageCount: 5
      }
    },
    created() {
      road.$on('setNickName', (text) => {
        this.nickname = text;
      });

      road.$on('goto', (url) => {
        if(url === "/login") {
          localStorage.removeItem('access-user');
          this.$router.push(url);
        }
      });
      // 组件创建完后获取数据
      this.fetchNavData();
    },
    methods: {
      jumpTo(url){
        this.$router.push(url); //用go刷新
      },
      handleSelect(index){
        this.defaultActiveIndex = index;
      },
      fetchNavData () { // 初始化菜单激活项
        var cur_path = this.$route.path; //获取当前路由
        //console.log("cur_path:"+cur_path);
        var routers = this.$router.options.routes; // 获取路由对象
        var nav_type = "home", nav_name = "home";
        for (var i = 0; i < routers.length; i++) {
          var children = routers[i].children;
          if(children){
            for (var j = 0; j < children.length; j++) {
              //console.log("path:"+children[j].path);
              if (children[j].path === cur_path) {
                nav_type = routers[i].type;
                nav_name = routers[i].name;
                break;
              }
            }
          }
        }
        this.$store.state.topNavState = nav_type;
        this.$store.state.leftNavState = nav_name;
        if(nav_type == "home"){
          this.defaultActiveIndex = "/";
        } else {
          this.defaultActiveIndex = "/" + nav_name + "Manager";
          //console.log(this.defaultActiveIndex);
        }
      },
      logout(){
        //logout
        let that = this;
        this.$confirm('确认退出吗?', '提示', {
          confirmButtonClass: 'el-button--warning'
        }).then(() => {
          //确认
          localStorage.removeItem('access-user');
          that.$router.go('/login'); //用go刷新
        }).catch(() => {});
      }
    },
    mounted() {
      let user = window.localStorage.getItem('access-user');
      if (user) {
        user = JSON.parse(user);
        this.nickname = user.nickname || '';
        this.companyName = user.companyName || '';
      }
    },
    watch: {
      '$route': function(to, from){ // 路由改变时执行
        console.info("to.path:" + to.path);
        this.fetchNavData();
      }
    }
  }
</script>
