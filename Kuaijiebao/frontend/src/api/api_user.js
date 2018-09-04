/**
 * Created by yqr on 2018/4/13.
 */
import * as API from './'
import axios from 'axios'
const instance = axios.create({baseURL: 'http://localhost:8088'})
export default {
  //登录
  login: (request,callback) => {

    //console.log(request.password+request.nickname);

    return instance({
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
        username:request.username,
        password:request.password,
      }
    })
      .then(function (response) {
        return response.data;
      })
      .then(callback)
      .catch(function (error) {
        console.log(error);
      });
  },
  //登出
  logout: params => {
    return "success";
    //return API.GET('/api/users/logout', params)
  },

  registerUser: (request,callback) => {

  return instance({
    method: 'post',
    url: '/api/auth/v1/signup',
    mode: 'no-cors',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
    },
    data: {
      username:request.username,
      password:request.password,
      nickname:request.nickname,
      identity:request.identity,
      name:request.name
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
      userId:request.userId,
      sum:request.sum,
      rate:request.rate,
      validTime:request.validTime,
      expectDischargeTime:request.expectDischargeTime,
      content:request.contents,
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
  UpdateDebtActivity: (debtId,request,callback) => {
    return instance({
      method: 'put',
      url: '/api/debt/EditDebtActivity/'+debtId,
      mode: 'no-cors',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      withCredentials: false,
      credentials: 'same-origin',
      data:{
        userId:request.userId,
        sum:request.sum,
        rate:request.rate,
        validTime:request.validTime,
        expectDischargeTime:request.expectDischargeTime,
        content:request.contents,
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
  DeleteDebtActivity: (debtId,callback) => {
    return instance({
      method: 'delete',
      url: '/api/debt/DeleteDebtActivity/'+debtId,
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



  getDebtStatByUserId: (userId,request,callback) => {
    return instance({
      method: 'get',
      url: '/api/v2/statistics/debt-stat/'+userId+'?since='+request.since+'&until='+request.until,
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
  getFPDRStatByUserId: (userId,request,callback) => {
    return instance({
      method: 'get',
      url: '/api/v2/statistics/fpdr-stat/'+userId+'?since='+request.since+'&until='+request.until,
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


}
