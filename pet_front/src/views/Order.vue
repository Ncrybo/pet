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
          :price="item.totalPrice"
          :desc="item.describes"
          :title="item.goodsName"
          :thumb="item.img"
          :num="item.productCount"
          @click="gotodetail(item)"
        >
        <template #tags >
          <van-tag v-if="(item.status == 0)" plain type="danger">等待付款</van-tag>
          <van-tag v-if="(item.status == 1)" plain type="danger">买家已支付</van-tag>
          <van-tag v-if="(item.status == 2)" plain type="danger">卖家已发货</van-tag>
          <van-tag v-if="(item.status == 3)" plain type="danger">交易成功</van-tag>
          <van-tag v-if="(item.status == 4)" plain type="danger">已完成评价</van-tag>
          <van-tag v-if="(item.status == -1)" plain type="danger">订单已取消</van-tag>
          <van-tag v-if="(item.status == -2)" plain type="danger">退单等待审核</van-tag>
          <van-tag v-if="(item.status == -3)" plain type="danger">退单成功</van-tag>
          <van-tag v-if="(item.status == -4)" plain type="danger">已被管理员退单</van-tag>
        </template>
        <template #footer>
          <van-button v-if="(item.status == 0)" size="mini" @click.stop="cancel(item)">取消订单</van-button>
          <van-button v-if="(item.status == 0)" size="mini" >继续付款</van-button>

          <van-button v-if="(item.status == 1)" size="mini" @click.stop="exit(item)">申请退款</van-button>
          <van-button v-if="(item.status == 1)" size="mini">修改地址</van-button>

          <van-button v-if="(item.status == 2)" size="mini" @click.stop="got(item)">确认收货</van-button>
          <van-button v-if="(item.status == 2)" size="mini" @click.stop="exit(item)">申请售后</van-button>

          <van-button v-if="(item.status == 3)" size="mini" @click.stop="rebuy(item)">再次购买</van-button>
          <van-button v-if="(item.status == 3)" size="mini" @click.stop="evaluate(item)">评价</van-button>

          <van-button v-if="(item.status == 4)" size="mini" @click.stop="rebuy(item)">再次购买</van-button>
          <van-button v-if="(item.status == 4)" size="mini" @click.stop="det(item)">删除</van-button>

          <van-button v-if="(item.status == -1)" size="mini" @click.stop="rebuy(item)">再次购买</van-button>
          <van-button v-if="(item.status == -1)" size="mini" @click.stop="det(item)">删除</van-button>

          <van-button v-if="(item.status < -2)" size="mini" @click.stop="rebuy(item)">再次购买</van-button>
          <van-button v-if="(item.status < -2)" size="mini" @click.stop="det(item)">删除</van-button>
        </template>
      </van-card>
        </van-tab>
        <!--下面是待付款-->
        <van-tab title="待付款"> 
          <van-card
          v-for="(item,index) in list"
          v-if="(item.status == 0)" 
          :key="index"
          :price="item.totalPrice"
          :desc="item.describes"
          :title="item.goodsName"
          :thumb="item.img"
          :num="item.productCount"
          @click="gotodetail(item)"
        >
        <template #tags>
          <van-tag plain type="danger">等待付款</van-tag>
        </template>
        <template #footer>
          <van-button size="mini" @click.stop="cancel(item)">取消订单</van-button>
          <van-button size="mini" @click.stop="buy(item)">继续付款</van-button>
        </template>
        </van-card>
        </van-tab>
      <!--下面是待发货-->
        <van-tab title="待发货"> 
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.status == 1)" 
          :key="index"
          :price="item.totalPrice"
          :desc="item.describes"
          :title="item.goodsName"
          :thumb="item.img"
          :num="item.productCount"
          @click="gotodetail(item)"
        >
        <template #tags>
          <van-tag plain type="danger">买家已付款</van-tag>
        </template>
        <template #footer>
          <van-button size="mini" @click.stop="cancel(item)">申请退款</van-button>
          <van-button size="mini">修改地址</van-button>
        </template>
        </van-card>
        </van-tab>
        <!--下面是待收货-->
        <van-tab title="待收货"> 
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.status == 2)" 
          :key="index"
          :price="item.totalPrice"
          :desc="item.describes"
          :title="item.goodsName"
          :thumb="item.img"
          :num="item.productCount"
          @click="gotodetail(item)"
        >
        <template #tags>
          <van-tag plain type="danger">卖家已发货</van-tag>
        </template>
        <template #footer>
          <van-button size="mini" @click.stop="got(item)">确认收货</van-button>
          <van-button size="mini" @click.stop="exit(item)">申请售后</van-button>
        </template>
        </van-card>
        </van-tab>
        <!--下面是待评价-->
        <van-tab title="待评价"> 
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.status == 3)" 
          :key="index"
          :price="item.totalPrice"
          :desc="item.describes"
          :title="item.goodsName"
          :thumb="item.img"
          :num="item.productCount"
          @click="gotodetail(item)"
        >
        <template #tags>
          <van-tag plain type="danger">交易成功</van-tag>
        </template>
        <template #footer>
          <van-button size="mini" @click.stop="rebuy(item)">再次购买</van-button>
          <van-button size="mini" @click.stop="evaluate(item)">评价</van-button>
        </template>
        </van-card>
        </van-tab>
      <!--下面是退款-->

        <van-tab title="退单退款">
          <van-card
          v-for="(item,index) in list" 
          v-if="(item.status < 0)" 
          :key="index"
          :price="item.totalPrice"
          :desc="item.describes"
          :title="item.goodsName"
          :thumb="item.img"
          :num="item.productCount"
          @click="gotodetail(item)"
        >
        <template #tags>
          <van-tag plain type="danger" v-if="(item.status == -1)" >订单已取消</van-tag>
          <van-tag plain type="danger" v-if="(item.status == -2)" >退单等待审核</van-tag>
          <van-tag plain type="danger" v-if="(item.status == -3)" >退单成功</van-tag>
          <van-tag plain type="danger" v-if="(item.status == -4)" >已被管理员退单</van-tag>
        </template>
        <template #footer>
          <van-button size="mini" @click.stop="rebuy(item)">再次购买</van-button>
          <van-button size="mini" @click.stop="det(item)">删除</van-button>
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
      text:'',
      show:false,
      active:0,
      uid:window.localStorage.getItem("uid"),
      list:[],
    }
  },

  mounted() {
      this.active=this.$route.query.id;
      this.$ajax.getOrders(this.uid).then(
          res => {        
            if(res.code == 100) {      
              this.list = res.data;   
          }
          else {
              console.log(res);
          }
      })
  },
  methods:{
      undo(){
          this.$router.go(-1);
      },
      gotodetail(item){
          this.$router.push('/orderdetail?id=' + item.id);
      },
      rebuy(item){
          this.$router.push('/goodsInfo?goodsId=' + item.goodsId);
      },
      buy(item){      //付款
        Dialog.confirm({
          title: '确认订单',
          message:
            '确认付款',
        })
          .then(() => {
            // on confirm
            this.$ajax.updStatus(1,item.id).then(
              res => {  
                if(res.data == 1) {      
                Dialog({ message: '付款成功' });
                this.$router.push('/orderdetail?id=' + item.id);
              }   
            })
          })
          .catch(() => {
            // on cancel
            Dialog({ message: '付款已取消' });
          });
      },

      got(item){    //确认收货
        Dialog.confirm({
          title: '确认收货',
          message:
            '确认收货？',
        })
          .then(() => {
            // on confirm
            this.$ajax.updStatus(3,item.id).then(
              res => {  
                if(res.data == 1) {      
                Dialog({ message: '收货成功' });
                this.$router.push('/orderdetail?id=' + item.id);
              }   
            })
          })
          .catch(() => {
            // on cancel
            Dialog({ message: '取消' });
          });
  
      },
      exit(item){     //申请退单
        this.$router.push('/orderdetail?id=' + item.id);
      },

      evaluate(item){     //评价
        this.$ajax.updStatus(4,item.id).then(
            res => {  
              if(res.data == 1) {      
              Dialog({ message: '评价成功，订单已完成' });
            }   
        })
      },
      cancel(item){   //取消订单
        Dialog.confirm({
          title: '取消订单',
          message:
            '确认取消？',
        })
          .then(() => {
            // on confirm
            this.$ajax.updStatus(-1,item.id).then(
              res => {  
                if(res.data == 1) {      
                this.$ajax.addReason('用户取消',item.id)
                Dialog({ message: '已取消成功' });
                this.$router.push('/orderdetail?id=' + item.id);
              }   
            })
          })
          .catch(() => {
            // on cancel
          });
      },

      det(item){
        Dialog.confirm({
          title: '删除订单',
          message:
            '确认删除？',
        })
          .then(() => {
            // on confirm
            this.list.splice(item,1)
            this.$ajax.detOrder(item.id);
            Dialog({ message: '订单记录已删除' });
          })
          .catch(() => {
            // on cancel
          });
      },
  },


}
</script>

<style scoped>
  .card{
    text-align: left;
  }
  .van-tag{
    position: absolute;
    margin-left: 200px;
    margin-top: -40px;
  }
  .header{
    height: 6vh;
  }
  .van-tab__pane-wrapper{
    height: 88vh;
    overflow: auto;
  }
</style>
