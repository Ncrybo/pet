<template>
    <div class="detail">
        <div class="header">
             <van-nav-bar left-arrow v-if="(this.list.status == -4)" @click-left="undo" title="已被管理员退单"/>
             <van-nav-bar left-arrow v-if="(this.list.status == -3)" @click-left="undo" title="退单成功"/>
             <van-nav-bar left-arrow v-if="(this.list.status == -2)" @click-left="undo" title="已申请退单"/>
             <van-nav-bar left-arrow v-if="(this.list.status == -1)" @click-left="undo" title="订单已取消"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 0)" @click-left="undo" title="等待付款"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 1)" @click-left="undo" title="买家已付款"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 2)" @click-left="undo" title="卖家已发货"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 3)" @click-left="undo" title="交易成功"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 4)" @click-left="undo" title="已完成评价"/>

        </div> 
        <br>
        <div class="address">
            <van-row>
                <van-col offset="1"><h3><van-icon name="location-o" />{{list.address}}</h3></van-col>
            </van-row>
            <van-row>
                <van-col offset="1">{{list.name}}</van-col>
                <van-col offset="1">{{list.tel}}</van-col>
            </van-row>
        </div>
        <br>
        <div class="card">
            <van-row>
                <van-icon name="shop-o" />{{list.shopName}}<van-icon name="arrow" />
            </van-row>
            <br>
            <van-row gutter="8">
                <van-col>
                 <img :src="this.list.img" alt="" class="imgs">
                </van-col>
                <van-col>
                    <van-row>
                        {{list.goodsName}}
                    </van-row>
                    <van-row>
                        {{list.describes}}
                    </van-row>
                        <br>
                    <van-row>
                        <van-tag plain type="danger">七天无理由退换</van-tag>
                    </van-row>
                </van-col>
                <van-col offset="6">
                    <van-row style="text-align: right;">
                        ￥{{list.totalPrice}}
                    </van-row>
                    <van-row style="text-align: right;">
                        x{{list.productCount}}
                    </van-row>
                </van-col>
            </van-row>
            <van-row style="text-align: right;">
                <button>退换</button>
            </van-row>
            <br>
            <van-row>
                <van-col>
                    实付款:
                </van-col>
                <van-col offset="3">
                    ￥{{list.totalPrice}}
                </van-col>
            </van-row>
            <van-row>
                <van-col>
                    订单编号:
                </van-col>
                <van-col offset="2">
                    {{list.orderNo}}
                </van-col>
            </van-row>
            <van-row>
                <van-col>
                    下单时间:
                </van-col>
                <van-col offset="2">
                    {{list.orderTime}}
                </van-col>
            </van-row>
            <van-row v-if="(this.list.status == -1)">
              <van-col>
                  取消原因：
              </van-col>
              <van-col offset="1">
                  {{list.reason}}
              </van-col>
          </van-row>
            <van-row v-if="(this.list.status < -1)">
                <van-col>
                    退单理由：
                </van-col>
                <van-col offset="1">
                    {{list.reason}}
                </van-col>
            </van-row>
            <br>
            <div class="countDown" v-if="(this.list.status == 0)" >
              订单剩余支付时间<van-count-down
              ref="countDown"
              millisecond
              :auto-start="true"
              :time=this.overTime
              format="mm:ss:SSS"
              @finish="finish"
            />
           </div>
        </div>
        <div class="bottom">
            <van-row gutter="20">
                <van-col offset="10">
                    <van-button round plain type="info" v-if="(this.list.status == 0)" @click="cancel">取消订单</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 1)" @click="exit">申请退单</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 2)" @click="got">确认收货</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 3)" @click="rebuy">再次购买</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 4)" @click="rebuy">再次购买</van-button>
                </van-col>
                <van-col>
                    <van-button round plain type="info" v-if="(this.list.status == 0)" @click="buy">继续付款</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 1)" >修改地址</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 2)" @click="exit">申请售后</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 3)" @click="evaluate">评价</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 4)" @click="det">删除</van-button>
                </van-col>
            </van-row>
        </div>
        <van-dialog v-model="show" title="请输入退单理由" show-cancel-button  :before-close="beforeClose">
            <van-field v-model="text" label="在此输入：" />
          </van-dialog>
    </div>
</template>

<script>
    import { Dialog } from 'vant';
    export default {
        data(){
            return{
                text:'',
                id: this.$route.query.id,
                show: false,
                list:[],
                overTime: 15000,
            }
        },
        mounted() {


        this.$ajax.getDetail(this.id).then(
            res => {        
                if(res.code == 100) {      
                    this.list = res.data;  
                    let endTime= new Date(this.list.orderTime).getTime() + 30*60*1000;
                    let start_time = new Date().getTime();
                    this.overTime = endTime - start_time;//时间戳
                }
                else {
                    console.log(res);
                }          
            })

        },

        methods: {
            undo(){
                this.$router.go(-1);
                console.log(this.TimeDate)
            
            },
            finish() {          //支付倒计时结束
              if(this.list.status == 0){
                Dialog({ message: '订单已超时' });
                this.$ajax.updStatus(-1,this.list.id).then(
                    res => {  
                      if(res.data == 1) {   
                      this.$ajax.addReason('订单超时',this.list.id)   
                      Dialog({ message: '订单因超时取消' });
                    }   
                })

              }
            },
            rebuy(item){
          this.$router.push('/goodsInfo?goodsId=' + this.list.goodsId);
            },
            buy(){      //付款
              Dialog.confirm({
                title: '确认订单',
                message:
                  '确认付款',
              })
                .then(() => {
                  // on confirm
                  this.$ajax.updStatus(1,this.list.id).then(
                    res => {  
                      if(res.data == 1) {      
                      Dialog({ message: '付款成功' });
                    }   
                  })
                })
                .catch(() => {
                  // on cancel
                  Dialog({ message: '付款已取消' });
                });
            },

            got(){    //确认收货
              Dialog.confirm({
                title: '确认收货',
                message:
                  '确认收货？',
              })
                .then(() => {
                  // on confirm
                  this.$ajax.updStatus(3,this.list.id).then(
                    res => {  
                      if(res.data == 1) {      
                      Dialog({ message: '收货成功' });
                    }   
                  })
                })
                .catch(() => {
                  // on cancel
                  Dialog({ message: '取消' });
                });
        
            },
            exit(){     //申请退单
              this.show=true
            },
            beforeClose : function (action, done) { // 点击事件 - 备注按钮提示框
                    if (action === 'confirm') { // 确认退款
                      this.$ajax.updStatus(-2,this.list.id).then(
                        res => {  
                          if(res.data == 1) {      
                            Dialog({ message: '已申请退款' });
                            }   
                      })
                      this.$ajax.addReason(this.text,this.list.id)
                      this.$router.push('/order');
                      done(); // 关闭提示框
                    }
                    else if(action === 'cancel')
                      done(); // 关闭提示框

              },

            evaluate(){     //评价
              this.$ajax.updStatus(4,this.list.id).then(
                  res => {  
                    if(res.data == 1) {      
                    Dialog({ message: '评价成功，订单已完成' });
                  }   
              })
            },
            cancel(){   //取消订单
              Dialog.confirm({
                title: '取消订单',
                message:
                  '确认取消？',
              })
                .then(() => {
                  // on confirm
                  this.$ajax.updStatus(-1,this.list.id).then(
                    res => {  
                      if(res.data == 1) {      
                      this.$ajax.addReason('用户手动取消',this.list.id)
                      Dialog({ message: '已取消成功' });
                    }   
                  })
                })
                .catch(() => {
                  // on cancel
                });
            },

            det(){
              Dialog.confirm({
                title: '删除订单',
                message:
                  '确认删除？',
              })
                .then(() => {
                  // on confirm
                  this.$ajax.detOrder(this.list.id);
                  Dialog({ message: '订单记录已删除' });
                  this.$router.push('/order');
                })
                .catch(() => {
                  // on cancel
                });
            },
              }
            
    }
    </script>

<style scoped>
    .detail{
        background-color: #fafafa;
    }
    .header{
        height: 6vh;
    }
    .countDown{
      color: red;
      text-align: center;
      margin-left: 0px;
    }
    .van-count-down{
      color: red;
    }
    .address{
        height: 20vh;
        background-color: white;
        text-align: left;
        border: 1px dotted darkslategray;
        border-radius: 15px;
        overflow: auto;
    }
    .card{
        height: 60vh;
        background-color: white;
        text-align: left;
        border: 1px dotted darkslategray;
        border-radius: 15px;
        overflow: auto;
    }
    .imgs{
        width: 100px;
        height: 100px;
        border-radius: 10px;
    }
    .bottom{
        height: 10vh;
        background-color: white;
        border: 1px solid darkslategray;
        border-left: none;
        border-right: none;
        border-bottom: none;
    }
</style>