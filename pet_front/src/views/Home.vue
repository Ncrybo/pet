<template>
  <div class="home">
    <div class="top">
      <div class="search">
        <van-search placeholder="ddddd" shape="round">
      </van-search>
      </div>
    </div>
    <div class="main">
      <!-- 轮番图 -->
      <van-swipe :autoplay="3000">
        <van-swipe-item v-for="(image, index) in images" :key="index">
          <img v-lazy="image" />
        </van-swipe-item>
      </van-swipe>

      <!-- 菜单栏 -->
      <div class="menu">
        <van-grid :column-num="3" >
          <van-grid-item icon="/images/menu/宠物寄养.svg" text="宠物寄养" />
          <van-grid-item icon="/images/menu/宠物摄影.svg" text="宠物摄影" />
          <van-grid-item icon="/images/menu/宠物修剪.svg" text="宠物修剪" />
          <van-grid-item icon="/images/menu/宠物洗澡.svg" text="宠物洗澡" />
          <van-grid-item icon="/images/menu/宠物训练.svg" text="宠物训练" />
          <van-grid-item icon="/images/menu/宠物疫苗.svg" text="宠物疫苗" />
        </van-grid>
      </div>
      <!-- 特价区 -->
      <van-panel>
        <template #header>
         <div class="panel_head">
          <div class="head_left">
            <van-icon size="1.5em" name="/images/icon/商品.svg" />
            <strong>今日特价</strong>         
          </div>
          <div class="head_right">
            <span>更多</span> <van-icon name="arrow" />
          </div>
         </div>
        </template>
        <div class="heng">
          <div class="goodsItem" v-for="i in 3" :key="i">
            <van-image src="/images/pet/cat1.jpg" fit="cover" width="30vw" />
            <div class="goodsname">英里渐白</div>
            <div class="prices"><span class="tejia">￥4587</span> <span class="yuanjia">￥4587</span> </div>
          </div>
        </div>
      </van-panel>
      <!-- 热门------
          分为两部分，第一个是头部的标题，第而部分是商品列表
       -->
      <div class="remen">
        <div class="tou">
          <van-icon size="1.7rem" name="/images/icon/热销.svg" />
          <strong>今日热销</strong> 
        </div>
        <div class="remen_content">
          <van-grid :column-num="2" :center="false" :square="false">
            <van-grid-item class="card" v-for="i in 4" :key="i">
              <van-image src="/images/pet/dog2.jpg" width="100%" />
              <div class="goodsname">博方金平狗狗</div>
              <div class="prices re_price">￥4587</div>
            </van-grid-item>
          </van-grid>
        </div>
      </div>
    </div>
    <div class="foot">
      <tab-bar></tab-bar>
    </div>
  </div>
</template>

<script>
import TabBar from "../components/tabBar.vue";
export default {
  name: 'home',
  components:{TabBar},
  data() {
    return {
      images: [
        '/images/swipe/1.jpg',
        '/images/swipe/2.jpg',
      ]
    }
  }

}
</script>

<style scoped>
.home {
  width: 100vw;
  background: #f5f4f9;
}
.top {
  height: 3.4em;
  width: 100vw;
}
.main {
  width: 100vw;
  height: calc(100vh - 7.4em);
  overflow-y: auto;
}
.foot {
  width: 100vw;
  height: 4em;
}
img {
  width: 100%;
  object-fit: cover;
}


.panel_head {
  display: flex;
  justify-content: space-between;
  font-size: 1.2rem;
  
}
.head_left {
  display: flex;
  justify-items: center;
}
.heng {
  width: 100vw;
  overflow-x: auto;
  display: flex;
  justify-content: space-around;
}
.goodsItem {
  text-align: left;
}
.prices {
  display: flex;
  justify-content: space-between;
}

.yuanjia {
  color: gray;
  font-size: 0.87em;
  text-decoration: line-through;
}

.remen {
  margin-top: .5rem;
  text-align: left;
}
.remen .tou {
  display: flex;
  align-items: center;
  background: #fff;
  font-size: 1.2rem;
}

.card {
  display: flex;
  justify-content: space-around;
}

.goodsname {
  font-weight: 700;
}
.re_price , .tejia {
  color: #fa2a5c;
  font-weight: 900;
}
.remen_content .van-grid-item__content {
  padding: 5px 5px;
}
.menu .van-grid-item__content {
  padding: 10px 4px;
}
</style>