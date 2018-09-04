// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'

import store from './store.js'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/iconfont.css'
import '@/assets/css/style.css'

Vue.config.productionTip = false
Vue.use(ElementUI)

Vue.component('footer-copyright', {
  template: '<p class="footer-msg">©CopyRight 2016-2018 软酷网有限公司 版权所有 <a href="http://www.miibeian.gov.cn" target="_blank">沪ICP备******号</a></p>'
});

new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
})
