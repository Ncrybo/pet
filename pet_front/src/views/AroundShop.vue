<template>
  <div class="aroundShop">
    <div class="baonav">
    <van-row class="nav" :fixed="true" algin="center">
      <van-col span="8">厦门</van-col>
      <van-col span="8">资质</van-col>
      <van-col span="8">信誉</van-col>
    </van-row>
    </div>
    <div class="main">
      <div class="shop-card" v-for="item in shops" :key="item.shopName">
        <!--附近商店页中的 商店卡片-->
        <div class="avatar" @click="toShop(item.shopName)">
          <img :src="item.shopImg"  alt="" />
        </div>
        <div class="shopName" @click="toShop(item.shopName)"><strong>{{item.shopName}}</strong></div>
        <div class="pingfen">
          <van-rate
            v-model="item.shopStar"
            size="10"
            allow-half
            void-icon="star"
            color="#ffd21e"
            void-color="#eee"
            :readonly="true"
          />
        </div>
        <div class="address">{{item.shopProvince+item.shopCity+item.shopStreet}}</div>
        <div class="petImg">
          <div class="imgItem">
            <div class="img">
              <img src="/images/pet/cat1.jpg" alt="" />
            </div>
            <div class="details">
              <div class="petName">英短金渐层</div>
              <div class="price">￥4588</div>
            </div>
          </div>
          <div class="imgItem">
            <div class="img">
              <img src="/images/pet/cat1.jpg" alt="" />
            </div>
            <div class="details">
              <div class="petName">英短金渐层</div>
              <div class="price">￥4588</div>
            </div>
          </div>
          <div class="imgItem">
            <div class="img">
              <img src="/images/pet/cat1.jpg" alt="" />
            </div>
            <div class="details">
              <div class="petName">英短金渐层</div>
              <div class="price">￥4588</div>
            </div>
          </div>
          
        </div>
      </div>
    </div>
    <tabBar></tabBar>
  </div>
</template>

<script>
import tabBar from "../components/tabBar.vue";
export default {
  name: "aroundShop", //组件名
  components: { tabBar},
  data() {
    return {
      shops:{},
    };
  },
  methods: {
    toShop(shopName) {
      this.$router.push("/shopInfo?shopName=" + shopName);
    },
  },
  computed: {},

  mounted() {
    this.$ajax.getAllShop().then(
      res => { 
        if(res.code == 100) {           
            this.shops = res.data;
        }
        else {
            console.log(res);
        }
    });
    

  },
};
</script>

<style scoped>
.aroundShop {
  width: 100vw;
  height: 100vh;
  background: #f1f4f8;
  overflow: hidden;
}

.baonav {
  height: 5vh;
}
.nav {
  position: fixed;
  height: 5vh;
  font-size: 1.2em;
}

.nav:after {
  /* 创建一条分割线 */
  content: " ";
  position: relative;
  display: table;
  left: 0;
  margin-top: 3px;
  width: 100vw;
  height: 1px;
  background: #b7b7b7;
}
.main {
  height: calc(100VH - 5vh - 6vh - 5px);
  overflow-y: scroll;
  overflow-x: hidden;
}

.bottomBar {
  height: 6vh;
}

.shop-card {
  /*商家卡片 */
  width: 96vw;
  height: 29vh;
  background: #fff;
  margin: 8px 0;
  padding: 4px;
  display: grid;
  grid-template-rows: 1fr 1.1fr 6fr;
  grid-template-columns: 4fr 10fr 6fr;
}
.avatar {
  grid-row: 1/3;
  grid-column: 1/2;
}
.shopName {
  grid-row: 1/2;
  grid-column: 2/3;
  text-align: left;
  margin-left: 3px;
  display: flex;
  align-items: center;
}
.address {
  grid-row: 2/3;
  grid-column: 2/3;
  text-align: left;
  margin-left: 3px;
  font-size: 0.8em;
}

.petImg {
  grid-row: 3/4;
  grid-column: 1/4;
  display: flex;
  justify-content: space-around;
  align-content: center;
}
.imgItem {
  width: 30%;
  margin-top: 4px;
}
.pingfen {
  width: 100%;
  grid-row: 1/2;
  grid-column: 3/4;
  text-align: center;
}
.shop-card img {
  width: 100%;
  object-fit: cover;
}
.avatar img {
  width: 80%;
  max-height: 100%;
  border-radius: 50%;
  text-align: center;
}
.details {
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 0.8em;
}
.shop-card .price {
  color: red;
}
</style>