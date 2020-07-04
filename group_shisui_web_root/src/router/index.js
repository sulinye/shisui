import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

// export default new Router({
const router = new Router({
  routes: [
    {
      path:'/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login')
    },
    // {
    //   path: '/',
    //   name: 'login',
    //   component: () => import('@/views/user/login')
    // },
    {
      path: '/goods',
      name: 'goods',
      component: () => import('@/views/goods/goods')
    },
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next();
  } else {
    let token = localStorage.getItem("token");
    console.log("token",token)
    if (token == null || token === 'null' || token === '') {
      console.log('1')
      next('/login');
    } else {
      console.log('2')
      next();
    }
  }
});

export default router;