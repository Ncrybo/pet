<template>
  <div
    class="My"
    style="
      background: -webkit-linear-gradient(
          bottom,
          rgba(110, 108, 108, 0.15),
          rgb(180, 197, 219)
        )
        no-repeat;
    "
  >
    <!-- 下面写你的界面 -->
    <div class="bao">
      <div class="header">
        <van-nav-bar title="个人中心">
          <template #right>
            <van-icon
              name="setting"
              size="26"
              color="block"
              @click="setting()"
            />
          </template>
        </van-nav-bar>
      </div>
      <div class="vip">
        <br />
        <van-row style="height: 60px; line-height: 60px; font-size: 1em">
          <van-col span="4" offset="1"
            ><img :src="imgs" alt="" class="touxiang" ></van-col>
          <van-col
            span="8"
            style="text-align: left; line-height: 20px; margin-top: 10px"
          >
            {{ username }} <br />
            <van-tag type="success">优惠券:0</van-tag>&nbsp;
            <van-tag type="success">积分:0</van-tag>
          </van-col>
          <van-col span="6" offset="4"
            ><van-button round type="primary" @click="logining">查看会员</van-button></van-col
          >
        </van-row>
        <br />
      </div>
      <br />
      <div class="card">
        <van-row style="line-height: 30px">
          <van-col span="8">常用功能</van-col>
        </van-row>
        <van-row style="line-height: 90px">
          <van-grid :border="false" :column-num="4">
            <van-grid-item
              icon="location-o"
              text="收货地址"
              @click="address()"
            />
            <van-grid-item icon="star-o" text="我的收藏" />
            <van-grid-item icon="clock-o" text="浏览记录" />
            <van-grid-item icon="setting-o" text="设置" @click="setting()" />
          </van-grid>
        </van-row>
      </div>
      <br />
      <div class="card" @click="order()">
        <van-row style="line-height: 30px">
          <van-col span="8">我的订单</van-col>
          <van-col span="6" offset="10">全部<van-icon name="arrow" /></van-col>
        </van-row>
        <van-row style="line-height: 90px">
          <van-grid :border="false" :column-num="5">
            <van-grid-item icon="paid" text="待付款" @click.stop="order()" />
            <van-grid-item
              icon="logistics"
              text="待发货"
              @click.stop="order()"
            />
            <van-grid-item icon="sign" text="待收货" @click.stop="order()" />
            <van-grid-item
              icon="comment-o"
              text="待评价"
              @click.stop="order()"
            />
            <van-grid-item
              icon="after-sale"
              text="退款"
              @click.stop="order()"
            />
          </van-grid>
        </van-row>
      </div>
      <br />
      <div class="card" style="height: 160px">
        <van-tabs v-model="active" swipeable animated style="margin-top: 8px">
          <van-tab
            v-for="(item, index) in list"
            :title="item.title"
            :key="index"
          >
            <van-card
              style="
                text-align: left;
                margin: 0px 10px 10px 10px;
                font-size: 1em;
              "
              :desc="item.desc"
              :title="item.favors"
              :thumb="item.img"
            />
          </van-tab>
        </van-tabs>
      </div>
    </div>
    <tabBar></tabBar>
    <!-- 底部导航栏 -->
  </div>
</template>

<script>
import { Toast } from "vant";
import tabBar from "../components/tabBar.vue";

export default {
  components: { tabBar },
  data() {
    return {
      imgs: window.localStorage.getItem("imgs"),
      username: window.localStorage.getItem("username"),
      id: 3,
      active: 0,
      list: [
        {
          title: "最常看宠物",
          favors: "英短金渐长",
          desc: "hello",
          img: "/dbImages/cat1.jpg",
        },
        {
          title: "最常逛店铺",
          favors: "喵的幸福",
          desc: "hello",
          img: "/dbImages/shop1.jpg",
        },
        {
          title: "最常看服务",
          favors: "猫猫寄养",
          desc: "hello",
          img: "/dbImages/cat1.jpg",
        },
        {
          title: "最常看食物",
          favors: "猫粮",
          desc: "hello",
          img: "/dbImages/cat1.jpg",
        },
      ],
    };
  },
  methods: {
    order(id) {
      this.$router.push({ path: "/order", query: { id: id } });
    },
    address() {
      this.$router.push({ path: "/address" });
    },
    setting() {
      this.$router.push({ path: "/setting" });
    },
    logining() {
      Toast("功能暂未开放");
    },
  },
};
</script>

<style scoped>
.my {
  height: 100vh;
  width: 100vw;
}
.bao {
  height: calc(100vh - 55px);
  overflow: auto;
}
.card {
  height: 125px;
  line-height: 125px;
  background-color: white;
  border: 1px dotted darkslategray;
  border-radius: 15px;
}
.touxiang {
  width: 60px; 
  height: 60px;
  border-radius: 50%;
}
</style>
