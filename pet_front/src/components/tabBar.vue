<template>
  <!--请不要更改此页面！！-->
  <div class="tabBar">
    <van-tabbar v-model="active" :fixed="true" route>
      <van-tabbar-item icon="home-o" to="/">主页</van-tabbar-item>
      <van-tabbar-item icon="shop-o" to="/around">附近商店</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart-o" to="/cart">购物车</van-tabbar-item>
      <van-tabbar-item icon="user-circle-o" to="/my">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<style scoped>
.van-tabbar-item {
  font-size: inherit;
}
</style>


<script>
export default {
  data() {
    return {
      active: 0,
    };
  },
};
</script>

