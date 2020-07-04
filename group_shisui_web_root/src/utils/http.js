import _axios from 'axios';
import qs from 'qs';
import {
    Message
} from 'element-ui';

//https://coloros.onecar156.com/pc
//创建axios的基本配置
console.log(process.env.NODE_ENV);
const axios = _axios.create({
    baseURL: process.env.NODE_ENV == 'development' ? null : process.env.BASE_API,
    // transformRequest: [function (data) {
    //   data = qs.stringify(data);
    //   return data
    // }],
    headers: {
    //'Content-Type': 'application/x-www-form-urlencoded',
        'Content-Security-Policy': 'upgrade-insecure-requests',
        'Content-Type': 'application/json'
    },
    transformResponse: [function (data) {
    // 对 data 进行任意转换处理
        let _data;
        if (typeof (data) == 'string') {
            _data = JSON.parse(data);
        }
        return _data;
    }],
    withCredentials: true
});
axios.defaults.withCredentials = true;

//X_SESSION_ID
//添加拦截器
axios.interceptors.request.use(function (config) {
    var token = localStorage.getItem('token');
    // var token = JSON.parse(localStorage.getItem('token'));
    if (!config.headers.token) {
    config.headers.Token = token;
        // config.headers.X_SESSION_ID = Cookies.get('NEWOPPOSID');
    //config.headers.X_SESSION_ID = 'xIlvmWmjbklJeETbmCNLo_XePiA-1e83PtphFTwyxr9sUMgP0aSMKW_yKKTXrW1Jb4utlo74ylE'
    }
    return config;
}, function (error) {
    return Promise.reject(error);
});

axios.interceptors.response.use(
    response => {
        const res = response.data;
        if (res.code !== 0) {
            Message({
                message: res.message,
                type: 'error',
                duration: 5 * 1000
            });
            return Promise.reject('error');
        } else {
            return response.data;
        }
    }
);

export default axios;
