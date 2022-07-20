<template>
  <div class="order">
      <div class="header">
          <van-nav-bar left-arrow  @click-left="undo()" title="我的订单"/>
     </div>
     <div class="tab" style="text-align: left;">
      <van-tabs v-model="active" swipeable  animated>
        <!--下面是全部-->
        <van-tab title="全部">
          <van-card
          v-for="(item,index) in list" 
          :key="index"
          :price="item.price"
          :desc="item.desc"
          :title="item.shop"
          :thumb="item.img"
        >
        <template #footer>
          <van-button size="mini" @click="look(item)">查看状态</van-button>
          <van-button size="mini" @click="rebuy(item)">再次购买</van-button>
          <van-button size="mini" @click="det(index)">删除</van-button>
        </template>
      </van-card>
        </van-tab>
        <!--下面是待付款-->
        <van-tab title="待付款"> 
          <van-card
          v-for="(item,index) in list"
          v-if="(item.state == 1)" 
          :key="index"
          :price="item.price"
          :desc="item.desc"
          :title="item.shop"
          :thumb="item.img"
        >
        <template #footer>
          <van-button size="mini" @click="buy(item)">付款</van-button>
          <van-button size="mini" @click="cancel(item)">取消</van-button>
        </template>
        </van-card>
        </van-tab>
      <!--下面是待发货-->
        <van-tab title="待发货"> 
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.state == 2)" 
          :key="index"
          :price="item.price"
          :desc="item.desc"
          :title="item.shop"
          :thumb="item.img"
        >
        <template #footer>
          <van-button size="mini" @click="deliver(item)">发货</van-button>
          <van-button size="mini" @click="exit(item)">退单</van-button>
        </template>
        </van-card>
        </van-tab>
        <!--下面是待收货-->
        <van-tab title="待收货"> 
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.state == 3)" 
          :key="index"
          :price="item.price"
          :desc="item.desc"
          :title="item.shop"
          :thumb="item.img"
        >
        <template #footer>
          <van-button size="mini" @click="got(item)">确认收货</van-button>
          <van-button size="mini" @click="exit(item)">退单</van-button>
        </template>
        </van-card>
        </van-tab>
        <!--下面是待评价-->
        <van-tab title="待评价"> 
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.state == 4)" 
          :key="index"
          :price="item.price"
          :desc="item.desc"
          :title="item.shop"
          :thumb="item.img"
        >
        <template #footer>
          <van-button size="mini" @click="rebuy(item)">再次购买</van-button>
          <van-button size="mini" @click="evaluate(item)">评价</van-button>
          <van-button size="mini" @click="det(index)">删除</van-button>
        </template>
        </van-card>
        </van-tab>
      <!--下面是退款-->
        <van-tab title="退款">
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.state == 5)" 
          :key="index"
          :price="item.price"
          :desc="item.desc"
          :title="item.shop"
          :thumb="item.img"
        >
        <template #footer>
          <van-button size="mini" @click="rebuy(item)">再次购买</van-button>
          <van-button size="mini" @click="det(index)">删除</van-button>
        </template>
      </van-card>
        </van-tab>

      </van-tabs>
    </div>
  </div>
</template>

<script>
import { Dialog } from 'vant';
export default {
  data() {
    return {
      active:0,
      list:[
        {
          price:"100",
          desc:"测试商品1",
          shop:"淘宠",
          state:1,
          img:"/img/hadog.jpg"
        },
        {
          price:"99",
          desc:"测试商品2",
          shop:"喵的",
          state:2,
          img:"/dbImages/cat2.jpg",
        },
        {
          price:"99",
          desc:"测试商品3",
          shop:"淘宠",
          state:2,
          img:"/img/baocat.jpg",
        },
        {
          price:"101",
          desc:"测试商品4",
          shop:"淘宠",
          state:3,
          img:"/img/lancat.jpg",
        },
        {
          price:"122",
          desc:"测试商品5",
          shop:"淘宠",
          state:4,
          img:"/img/sadog.jpg",
        },
        {
          price:"100",
          desc:"测试商品6",
          shop:"淘宠",
          state:5,
          img:"/img/hadog.jpg",
        },
      ],
    }
  },

  mounted() {
      this.active=this.$route.query.id;
  },
  methods:{
      undo(){
          this.$router.go(-1);
      },
      look(item){
        if(item.state == 1)
          Dialog({ message: '待付款' });
        else if(item.state == 2)
          Dialog({ message: '待发货' });
        else if(item.state == 3)
          Dialog({ message: '待收货' });
        else if(item.state == 4)
          Dialog({ message: '待评价' });
        else if(item.state == 5)
          Dialog({ message: '退款' });
        else
          Dialog({ message: '已取消/已完成' });
      },
      rebuy(item){
        item.state = 1    //再次购买
        Dialog({ message: '已添加到待付款' });
      },
      buy(item){
        item.state = 2    //付款
        Dialog({ message: '付款成功' });
      },
      deliver(item){
        item.state = 3    //发货
        Dialog({ message: '发货成功' });
      },
      got(item){
        item.state = 4    //确认收货
        Dialog({ message: '收货成功' });
      },
      exit(item){
        item.state = 5    //退款
        Dialog({ message: '退款成功' });
      },
      evaluate(item){
        item.state = 0    //评价成功
        Dialog({ message: '评价成功，订单已完成' });
      },
      cancel(item){
        item.state = 0    //取消
        Dialog({ message: '取消成功' });
      },
      det(index){
        this.list.splice(index,1)
      },
  },


}
</script>

<style scoped>
  .card{
    text-align: left;
  }
</style>
