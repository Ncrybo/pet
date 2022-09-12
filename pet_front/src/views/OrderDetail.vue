<template>
    <div class="detail">
        <div class="header">
             <van-nav-bar left-arrow v-if="(this.list.status == 0)" @click-left="undo" title="等待付款"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 1)" @click-left="undo" title="买家已付款"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 2)" @click-left="undo" title="卖家已发货"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 3)" @click-left="undo" title="交易成功"/>
             <van-nav-bar left-arrow v-if="(this.list.status == 4)" @click-left="undo" title="退款"/>
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
                    实付款
                </van-col>
                <van-col offset="17">
                    ￥{{list.totalPrice}}
                </van-col>
            </van-row>
            <van-row>
                <van-col>
                    订单编号
                </van-col>
                <van-col offset="18">
                    {{list.orderNo}}
                </van-col>
            </van-row>
            <van-row>
                <van-col>
                    下单时间
                </van-col>
                <van-col offset="12">
                    {{list.orderTime}}
                </van-col>
            </van-row>
        </div>
        <div class="bottom">
            <van-row gutter="20">
                <van-col offset="10">
                    <van-button round plain type="info" v-if="(this.list.status == 0)" >取消订单</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 1)" >申请退款</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 2)" >确认收货</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 3)" >再次购买</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 4)" >再次购买</van-button>
                </van-col>
                <van-col>
                    <van-button round plain type="info" v-if="(this.list.status == 0)" >继续付款</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 1)" >修改地址</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 2)" >申请售后</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 3)" >评价</van-button>
                    <van-button round plain type="info" v-if="(this.list.status == 4)" >删除</van-button>
                </van-col>
            </van-row>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                id: this.$route.query.id,
                list:[],
            }
        },
        mounted() {
        this.$ajax.getDetail(this.id).then(
            res => {        
                if(res.code == 100) {      
                    this.list = res.data;   
                    console.log(list);
                }
                else {
                    console.log(res);
                }
            })
        },
        methods: {
            undo(){
                this.$router.go(-1);
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