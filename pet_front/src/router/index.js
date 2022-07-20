import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import axios from "../network/http.js";
import { Toast } from "vant";

Vue.use(VueRouter);

const routes = [
  {                        //主页路由
    path: "/",          
    name: "home",
    component: Home,
  },
  {
    path: '/shopInfo',
    name: 'shopInfo',
    component:() => import("../views/ShopInfo.vue"),
    children: [
      {
        path: '/',
        component: () => import("../views/petPage.vue")
      }
    ]
  },

  {                         //基本登录路由
    path: "/login",
    name: "login",
    component: () => import("../views/Login.vue"),
  },
  {                         //注册路由
    path: "/zhuce",
    name: "zhuce",
    component: () => import("../views/Zhuce.vue"),
  },

  {                         //附近商店路由
    path: "/around",
    name: "aroundShop",
    component: () => import("../views/AroundShop.vue"),
  },

  {                       //购物车路由
    path: "/cart", 
    name: "cart",
    component: () => import("../views/Cart.vue"),
    meta: {
      requiresAuth: true
    }
  },

  {                      //个人主页路由
    path: "/my",
    name: "my",
    component: () => import("../views/My.vue"),
    meta: {
      requiresAuth: true
    }
  },
  {                      //个人主页路由
    path: "/order",
    name: "order",
    component: () => import("../views/Order.vue"),
    meta: {
      requiresAuth: true
    }
  },
  {                      //设置页面路由
    path: '/setting',
    name: 'Setting',
    component: () => import('../views/Setting.vue'),
  },
  {                      //编辑地址路由
    path: '/editaddress',
    name: 'Editaddress',
    component: () => import('../views/Editaddress.vue'),
  },
  {                      //添加地址路由
    path: '/addaddress',
    name: 'addaddress',
    component: () => import('../views/Addaddress.vue'),
  },
  {
    path: '/address',
    name: 'Address',
    component: () => import('../views/Address.vue'),
  },
  {                      
    path: "/goodsInfo",
    name: "goodsInfo",
    component: () => import("../views/GoodsInfo.vue"),
  },

];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to,from,next) => {
  let _this = this;
  if(to.meta.requiresAuth) {
    axios.verifyToken()
  .then((res)=> {
    if(res.code == 100)
    {
      next();
    }
    else {
      Toast({
        type: "loading",
        message: "请先登录",
        loadingType: "spinner",
        duration: 1000,
      });
      next({
        name: 'login',
       // query: { redirect: to.fullPath }
      })
    }
  }).catch(err => {
    alert("服务端连接失败！")
  })
    
  }
  else {
    next()
  }
})

export default router;
