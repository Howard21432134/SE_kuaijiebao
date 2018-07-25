/**
 * Created by yqr on 2018/4/13.
 */
import * as API from './'
import axios from 'axios'
const instance = axios.create({baseURL: 'http://localhost:8088'})
export default {
  //登录
  login: params => {

    let status=this;
    instance({
      method: 'post',
      url: '/api/auth/v1/signin',
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      withCredentials: false,
      credentials: 'same-origin',
      data: {
        username:"james",
        password:"password",
        identity:"student",
        nickname:"james",
      }
    })
      .then(function (response) {
        status=response.data.userId;
      })
      .catch(function (error) {
        console.log(error);
      })

    if(status!==undefined)
      return "success";
    else
      return "failed";

    //return API.POST('/api/users/login', params)
  },
  //登出
  logout: params => {
    return "success";
    //return API.GET('/api/users/logout', params)
  },

  getUserByUserId: (params,callback) => {

    return instance({
      method: 'get',
      url: '/api/v2/users/'+params,
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      withCredentials: false,
      credentials: 'same-origin',
    })
      .then(function (response) {
        return response.data;
      })
      .then(callback)
      .catch(function (error) {
        console.log(error);
      })
  },

  updateUser: (userId,user,callback) => {

    console.log(user);
    return instance({
      method: 'put',
      url: '/api/v2/users/'+userId,
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      data:{
        nickname:user.nickname,
        phone: user.phone,
        email: user.email,
        job: user.job,
        address: user.address,
        income: user.income,
        introduction: user.introduction
      },
      withCredentials: false,
      credentials: 'same-origin',
    })
      .then(function (response) {
        return response.data;
      })
      .then(callback)
      .catch(function (error) {
        console.log(error);
      })
  },

  registerUserInfoModification: (request,callback) => {
    return instance({
      method: 'post',
      url: '/api/v2/users/registerInfo',
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      withCredentials: false,
      credentials: 'same-origin',
      data:{
        username:request.username,
        email:request.email,
        elem:request.elem,
        item:request.item,
      }
    })
      .then(function (response) {
        console.log(response.data);
        return response.data
      })
      .then(callback)
      .catch(function (error) {
        console.log(error);
      })
  },
  validateUserInfoModification: (request,callback) => {
    let status=this;
    instance({
      method: 'post',
      url: '/api/v2/users/validateInfo',
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      withCredentials: false,
      credentials: 'same-origin',
      data:{
        username:request.username,
        code:request.code,
      }
    })
      .then(function (response) {
        console.log(response.data);
        return response.data;
      })
      .then(callback)
      .catch(function (error) {
        console.log(error);
      })

    return status;

    //return API.POST('/api/users/login', params)
  },



  AddDebtActivity: (request,callback) => {
  return instance({
    method: 'post',
    url: '/api/debt/AddDebtActivity',
    mode: 'no-cors',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
    },
    withCredentials: false,
    credentials: 'same-origin',
    data:{
      userId:request.useId,
      sum:request.sum,
      rate:request.rate,
      validTime:request.validTime,
      expectDischargeTime:request.expectDischargeTime,
      content:request.content,
    }
  })
    .then(function (response) {
      console.log(response.data);
      return response.data;
    })
    .then(callback)
    .catch(function (error) {
      console.log(error);
    })
},

  ShowDebtByUserActivity: (params,callback) => {
    return instance({
      method: 'get',
      url: '/api/debt/ShowDebtByUserActivity/'+params,
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      withCredentials: false,
      credentials: 'same-origin',
    })
      .then(function (response) {
        console.log(response.data);
        return response.data;
      })
      .then(callback)
      .catch(function (error) {
        console.log(error);
      })
  },

  getBankCardsByUserId: (params,callback) => {
    return instance({
      method: 'get',
      url: '/api/v2/users/'+params+'/bankcard',
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      withCredentials: false,
      credentials: 'same-origin',
    })
      .then(function (response) {
        return response.data;
      }).then(callback)
      .catch(function (error) {
        console.log(error);
      })


    //return API.POST('/api/users/login', params)
  },

}
