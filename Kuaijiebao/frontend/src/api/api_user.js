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

  getUserByUserId: params => {

    let status=this;
    instance({
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
        console.log(response.data);
        status=response.data;
      })
      .catch(function (error) {
        console.log(error);
      })

    return status;

    //return API.POST('/api/users/login', params)
  },
  registerUserInfoModification: params => {
    let status=this;
    instance({
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
        username:"michele",
        email:"satoaikawa@sjtu.edu.cn",
        elem:"PHONE_NUMBER",
        item:"11122223333",
        code:"176046"
      }
    })
      .then(function (response) {
        console.log(response.data);
        status=response.data;
      })
      .catch(function (error) {
        console.log(error);
      })

    return status;

    //return API.POST('/api/users/login', params)
  },
  validateUserInfoModification: params => {
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
        username:"michele",
        code:"176046"
      }
    })
      .then(function (response) {
        console.log(response.data);
        status=response.data;
      })
      .catch(function (error) {
        console.log(error);
      })

    return status;

    //return API.POST('/api/users/login', params)
  },
}
