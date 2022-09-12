<template>
  <div class="home">
    <div class="top">
      <div class="search">
        <van-search placeholder="请输入搜索关键词" shape="round">
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
        <!-- 弹窗区 -->
        
        <van-dialog v-model="show1" title="英短金渐层" show-cancel-button>
          <van-image src="/images/pet/cat1.jpg" height="150%" width="100%" />
        </van-dialog>

        <van-dialog v-model="show2" title="英短银渐层" show-cancel-button>
            <video ref="videoPlayer1" controls :src=fileUrl1 :poster=posterUrl1 controlslist="nodownload" 
                 style="width: 100%; height: 100%; object-fit: fill">
            </video>
        </van-dialog>

        <van-dialog v-model="show3" title="冰种蓝猫" show-cancel-button>
          <van-image src="/images/pet/cat6.jpg" height="150%" width="100%" />
        </van-dialog>

        <van-dialog v-model="show4" title="柯基" show-cancel-button>
          <van-image src="/images/pet/dog2.jpg" height="150%" width="100%" />
        </van-dialog>

        <van-dialog v-model="show5" title="哈士奇" show-cancel-button>
          <van-image src="/images/pet/dog6.jpg" height="150%" width="100%" />
        </van-dialog>

        <van-dialog v-model="show6" title="萨摩耶" show-cancel-button>
          <van-image src="/images/pet/dog7.jpg" height="150%" width="100%" />
        </van-dialog>

        <van-dialog v-model="show7" title="柴犬" show-cancel-button>
          <video ref="videoPlayer2" controls :src=fileUrl2 :poster=posterUrl2 controlslist="nodownload" 
                 style="width: 100%; height: 100%; object-fit: fill">
          </video>
        </van-dialog>
        
        <div class="heng">
          <div class="goodsItem" v-for="(item,index) in listcat" :key="index">
            <van-image :src="item.img" fit="cover" width="30vw" @click="shows(index)"/>
            <div class="goodsname">{{item.name}}</div>
            <div class="prices"><span class="tejia">￥{{item.tejia}}</span> <span class="yuanjia">￥{{item.yuanjia}}</span> </div>
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
            <van-grid-item class="card" v-for="(item,id) in listdog" :key="id">
              <van-image :src="item.img" width="100%"  @click="shows(id+3)"/>
              <div class="goodsname">{{item.name}}</div>
              <div class="prices re_price">￥{{item.yuanjia}}</div>
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
      fileUrl1:"/images/pet/cat1.mp4",
      posterUrl1:"/images/pet/cat5.jpg",
      fileUrl2:"/images/pet/dog1.mp4",
      posterUrl2:"/images/pet/dog8.jpg",

      show1:false,
      show2:false,
      show3:false,
      show4:false,
      show5:false,
      show6:false,
      show7:false,
      images: [
        '/images/swipe/1.jpg',
        '/images/swipe/2.jpg',
      ],
   listcat: [
        {
          name: "英短金渐层",
          yuanjia: "6666",
          tejia:"3888",
          img: "/images/pet/cat1.jpg",
        },
        {
          name: "英短银渐层",
          yuanjia: "8888",
          tejia:"5888",
          img: "/images/pet/cat5.jpg",
        },
        {
          name: "冰种蓝猫",
          yuanjia: "9999",
          tejia:"7888",
          img: "/images/pet/cat6.jpg",
        },
      ],
    listdog: [
        {
          name: "柯基",
          yuanjia: "2888",
          img: "/images/pet/dog2.jpg",
        },
        {
          name: "哈士奇",
          yuanjia: "3888",
          img: "/images/pet/dog6.jpg",
        },
        {
          name: "萨摩耶",
          yuanjia: "6888",
          img: "/images/pet/dog7.jpg",
        },
        {
          name: "柴犬",
          yuanjia: "6999",
          img: "/images/pet/dog8.jpg",
        },
      ],
      
    }
  },
    methods: {
      shows(index){
        if(index == 0)
          this.show1 = true
        else if(index == 1)
          this.show2 = true
        else if(index == 2)
          this.show3 = true
        else if(index == 3)
          this.show4 = true
        else if(index == 4)
          this.show5 = true
        else if(index == 5)
          this.show6 = true
        else if(index == 6)
          this.show7 = true
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