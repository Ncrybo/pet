import Vue from "vue";
import Vant from "vant";
import { Lazyload } from 'vant';
import "vant/lib/index.css";
import { Cell, CellGroup } from 'vant';
import App from "./App.vue";
import router from "./router";
import store from "./store";
import api from "./network/http"


Vue.config.productionTip = false;
Vue.use(Vant);
Vue.use(Lazyload);
Vue.prototype.$ajax = api




new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
