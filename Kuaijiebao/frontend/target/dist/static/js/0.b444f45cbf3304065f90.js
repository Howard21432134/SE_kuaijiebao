webpackJsonp([0],{Quw4:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=a("mvHQ"),n=a.n(r),o=(a("gyMJ"),function(t){return"success"}),s={data:function(){var t=this;return{loading:!1,account:{username:"",pwd:""},rules:{username:[{required:!0,validator:function(e,a,r){""===a?r(new Error("请输入账号")):(""!==t.account.username&&(t.account.username=a,t.validateCorrect()),r())},trigger:"change"}],pwd:[{required:!0,validator:function(e,a,r){""===a?r(new Error("请输入密码")):(""!==t.account.pwd&&(t.account.pwd=a,t.validateCorrect()),r())},trigger:"change"}]},pwdFocus:!1,allowLogin:!0,checked:!0}},created:function(){var t=JSON.parse(window.localStorage.getItem("register-user"));t&&(this.account.username=t.username,this.account.pwd="",this.pwdFocus=!0)},methods:{handleLogin:function(){var t={id:"1",username:"admin",nickname:this.account.username,name:"administrator",email:"888888@163.com"};this.loading=!0,"success"==o(t)?(localStorage.setItem("access-user",n()(t)),window.localStorage.removeItem("register-user"),this.$router.push({path:"/"})):(this.loading=!1,this.$message.error("登录失败，账号或密码错误"))},validateCorrect:function(){this.account.pwd.trim().length>0&&this.account.username.trim().length>0?this.allowLogin=!1:this.allowLogin=!0}}},i={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[t._m(0),t._v(" "),a("el-form",{ref:"AccountForm",staticClass:"demo-ruleForm login-container",attrs:{model:t.account,rules:t.rules,"label-position":"left","label-width":"0px"}},[a("h3",{staticClass:"title"},[t._v("欢迎登录")]),t._v(" "),a("el-form-item",{attrs:{prop:"username"}},[a("el-input",{attrs:{type:"text","auto-complete":"off",placeholder:"手机号或公司企业码"},model:{value:t.account.username,callback:function(e){t.$set(t.account,"username",e)},expression:"account.username"}})],1),t._v(" "),a("el-form-item",{attrs:{prop:"pwd"}},[a("el-input",{attrs:{type:"password",autofocus:t.pwdFocus,"auto-complete":"off",placeholder:"请输入登录密码"},model:{value:t.account.pwd,callback:function(e){t.$set(t.account,"pwd",e)},expression:"account.pwd"}})],1),t._v(" "),a("el-form-item",{staticClass:"extra-text"},[a("a",{staticClass:"forget-pwd",attrs:{href:"javascript:;",title:"找回密码"}},[t._v("忘记密码?")]),t._v(" "),a("router-link",{staticClass:"reg-text",attrs:{to:{path:"/register"},title:"立即注册"}},[t._v("立即注册")])],1),t._v(" "),a("el-form-item",{staticStyle:{width:"100%"}},[a("el-button",{staticStyle:{width:"100%"},attrs:{type:"primary",disabled:t.allowLogin,loading:t.loading},nativeOn:{click:function(e){return e.preventDefault(),t.handleLogin(e)}}},[t._v("登录")])],1)],1),t._v(" "),a("div",{staticClass:"footer"},[a("footer-copyright")],1)],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"header-content"},[e("div",{staticClass:"logo-part"},[e("img",{attrs:{src:a("7Otq"),width:"30",height:"30"}}),this._v(" "),e("span",[this._v("快借宝")])])])}]};var c=a("VU/8")(s,i,!1,function(t){a("nhP8")},"data-v-0eaea6e4",null);e.default=c.exports},nhP8:function(t,e){}});
//# sourceMappingURL=0.b444f45cbf3304065f90.js.map