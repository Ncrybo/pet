import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({          //可以增加东西，但是请不要移动位置，或者删除，防止合并时出错！
  state: {
    activePage:0,

    //董波----

    //李伟东使用位置-----

    //浪哥--

    //书剑---

    

  },
  getters: {
    getActivePage(state) {
      return state.activePage;
    },

    //董波----
    
    //李伟东使用位置-----

    //浪哥--

    //书剑---
  },
  mutations: {
    setActivePage(state,active) {
      state.activePage = active;
    }

    //董波----
    
    //李伟东使用位置-----

    //浪哥--

    //书剑---
  },
  actions: {
    //董波----
    
    //李伟东使用位置-----

    //浪哥--

    //书剑---
  },
  modules: {
    //董波----
    
    //李伟东使用位置-----

    //浪哥--

    //书剑---
  },
});
