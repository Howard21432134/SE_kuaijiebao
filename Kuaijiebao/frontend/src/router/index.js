/**
 * Created by yqr on 2018/3/26.
 */
import Vue from 'vue'
import Router from 'vue-router'
import TopNav from '@/components/nav/topNav.vue'
import LeftNav from '@/components/nav/leftNav.vue'
import Home from '@/views/home.vue'
import Dashboard from '@/views/workbench/dashboard.vue'
import MySettings from '@/views/workbench/mySettings.vue'
import Mission from '@/views/workbench/mission.vue'
import Plan from '@/views/workbench/plan.vue'
import Maillist from '@/views/workbench/maillist.vue'
import EnterpriseList from '@/views/enterprise/index.vue'
import EnterpriseAdd from '@/views/enterprise/add.vue'
import EnterpriseDetail from '@/views/enterprise/detail.vue'
import EnterpriseValidate from '@/views/enterprise/validate.vue'
import VehicleManage from '@/views/vehicle/index.vue'
import DeptManager from '@/views/dept/index.vue'
import NotFound from '@/components/404.vue'
import Register from '@/views/admin/register.vue'
import RegisterNext from '@/views/admin/registerNext.vue'
import InfoSetting from '@/views/workbench/inform_setting.vue'
import Deletemoney from '@/views/enterprise/delete.vue'
import Buydebt from '@/views/dept/buy.vue'
import Selldebt from '@/views/dept/sell.vue'

import Applydebt from '@/views/debt/apply.vue'
import Changedebt from '@/views/debt/change.vue'
import Deletedebt from '@/views/debt/delete.vue'
import Consultcre from '@/views/credit/search.vue'
import managecre from '@/views/credit/manage.vue'
import debtcount from '@/views/statistic/debtcounting.vue'
import mmanagecount from '@/views/statistic/moneymanagement.vue'
import autoconsult from '@/views/consult/auto.vue'
import humanconsult from '@/views/consult/human.vue'

// 懒加载方式，当路由被访问的时候才加载对应组件
const Login = resolve => require(['@/views/login'], resolve)

Vue.use(Router)

const MyComponent= { template: '<div>STUB me.</div>' }

let router = new Router({
  routes: [
    {
      path: '/login',
      type: 'login',
      component: Login
    },
    {
      path: '/register',
      type: 'register',
      component: Register
    },
    {
      path: '/registerNext',
      type: 'register',
      component: RegisterNext
    },
    {
      path: '*',
      component: NotFound
    },
    {
      path: '/',
      type: 'home',
      name: 'home',
      redirect: '/dashboard/',
      component: Home,
      children: [
        {
          path: '/dashboard/',
          name: '首页',
          components: {
            default: Dashboard,
            top: TopNav,
            aside: LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        },
        {
          path: '/mySet',
          components: {
            default: MySettings,
            top: TopNav,
            aside: LeftNav
          },
          name: '我的设置',
          iconCls: 'el-icon-menu',
          menuShow: true,
          children: [
            { path: '/mySet/plan/', component: InfoSetting, name: '个人信息管理', menuShow: true },
            { path: '/mySet/mission/', component: Maillist, name: '个人安全管理', menuShow: true },
            { path: '/mySet/maillist/', component: Mission, name: '银行卡管理', menuShow: true }
          ]
        }
      ]
    },

    {
      path: '/enterpriseManager',
      type: 'enterprise',
      name: 'enterprise',
      component: Home,
      redirect: '/enterprise/list',
      menuShow: true,
      children: [
        {
          path: '/enterprise/list',
          name: '选购理财产品',
          components: {
            default: EnterpriseList,
            top: TopNav,
            aside: LeftNav
          },
          leaf: true,
          iconCls: 'el-icon-setting',
          menuShow: true
        },
        {
          path: '/enterprise/add',
          name: '撤销理财产品',
          components: {
            default: Deletemoney,
            top: TopNav,
            aside: LeftNav
          },
          leaf: true,
          iconCls: 'el-icon-menu',
          menuShow: true
        },
      ]
    },
    {
      path: '/vehicleManager',
      type: 'enterprise',
      name: 'vehicle',
      component: Home,
      redirect: '/vehicle/list',
      menuShow: true,
      children: [
        {
          path: '/vehicle/list',
          name: '我的债权信息',
          components: {
            default: VehicleManage,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },

    {
      path: '/deptManager',
      type: 'enterprise',
      name: 'dept',
      component: Home,
      redirect: '/dept/list',
      menuShow: true,
      children: [
        {
          path: '/dept/list',
          name: '购买债权',
          components: {
            default: Buydebt,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        },
        {
          path: '/dept/sell',
          name: '出售债权',
          components: {
            default: Selldebt,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        },
      ]
    },


    //************Debt Module***************//
    {
      path: '/debtManager',//these three name should be the same.
      type: 'debt',        //
      name: 'debt',        //
      component: Home,
      redirect: '/debt/application',    //should redirect to its children path
      menuShow: true,
      children: [
        {
          path: '/debt/application',
          name: '借款申请',
          components: {
            default: Applydebt,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },
    {
      path: '/mydebtManager',
      type: 'debt',
      name: 'mydebt',
      component: Home,
      redirect: '/debt/update',
      menuShow: true,
      children: [
        {
          path: '/debt/update',
          name: '修改个人借款申请',
          components: {
            default: Changedebt,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        },
        {
          path: '/debt/cancel',
          name: '撒销个人借款申请',
          components: {
            default: Deletedebt,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },


    //************Personal Credit (PC) Module***************//
    {
      path: '/pcManager',//these three name should be the same.
      type: 'pc',        //
      name: 'pc',        //
      component: Home,
      redirect: '/pc/check',    //should redirect to its children path
      menuShow: true,
      children: [
        {
          path: '/pc/check',
          name: '查询当面信用额度',
          components: {
            default: Consultcre,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        },
      ]
    },
    {
      path: '/mypcManager',
      type: 'pc',
      name: 'mypc',
      component: Home,
      redirect: '/pc/upgrade',
      menuShow: true,
      children: [
        {
          path: '/pc/upgrade',
          name: '信用额度管理',
          components: {
            default: managecre,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },

    //************Statistics Module***************//
    {
      path: '/statisticsManager',//these three name should be the same.
      type: 'statistics',        //
      name: 'statistics',        //
      component: Home,
      redirect: '/stat/debt',    //should redirect to its children path
      menuShow: true,
      children: [
        {
          path: '/stat/debt',
          name: '借贷款统计',
          components: {
            default: debtcount,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },
    {
      path: '/fpstatManager',
      type: 'statistics',
      name: 'fpstat',
      component: Home,
      redirect: '/stat/fp',
      menuShow: true,
      children: [
        {
          path: '/stat/fp',
          name: '理财统计',
          components: {
            default: mmanagecount,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },

    //************Counsel Module***************//
    {
      path: '/counselManager',//these three name should be the same.
      type: 'counsel',        //
      name: 'counsel',        //
      component: Home,
      redirect: '/counsel/list',    //should redirect to its children path
      menuShow: true,
      children: [
        {
          path: '/counsel/list',
          name: '常见问题',
          components: {
            default: autoconsult,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },
    {
      path: '/onlinecounselManager',
      type: 'counsel',
      name: 'onlinecounsel',
      component: Home,
      redirect: '/counsel/online',
      menuShow: true,
      children: [
        {
          path: '/counsel/online',
          name: '在线咨询',
          components: {
            default: humanconsult,
            top: TopNav,
            aside:  LeftNav
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    },

    { path: '/posts', component: MyComponent },


  ]
});

router.beforeEach((to, from, next) => {
  // console.log('to:' + to.path)
  if (to.path.startsWith('/login')) {
    window.localStorage.removeItem('access-user')
    next()
  } else if(to.path.startsWith('/register')){
    window.localStorage.removeItem('access-user')
    next()
  } else {
    let user = JSON.parse(window.localStorage.getItem('access-user'))
    if (!user) {
      next({path: '/login'})
    } else {
      next()
    }
  }
});

export default router
