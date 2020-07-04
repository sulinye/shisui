// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'


Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
Vue.prototype.$http = axios
Vue.use(ElementUI)
// 配置请求信息
// var $http = Axios.create({
//   　baseURL: 'http://localhost:61002/V1/goods/findGoodsList',
//   　timeout: '3000',  //请求超时时间
//   　headers: {'X-Custom-Header': 'foobar'}     //header传值，例如：Authorization
//   })
// Vue.prototype.$http = $http
// this.$http.get('http://localhost:61002/V1/goods/findGoodsList')
// .then(function(res){
// alert(res);
// })
// .catch(function(err){
// alert(err);
// })