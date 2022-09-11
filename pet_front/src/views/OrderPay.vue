<template>
    <div>
        <div class="header">
             <van-nav-bar left-arrow  @click-left="undo" title="确认订单"/>
        </div>
        <div class="address" @click="chooes">
        <van-row>
            <van-col span="20">
                <van-row>
                    <van-col offset="1"><h3><van-icon name="location-o" />{{this.list.address}}</h3></van-col>
                </van-row>
                <van-row>
                    <van-col offset="1">{{this.list.name}}</van-col>
                    <van-col offset="1">{{this.list.tel}}</van-col>
                </van-row>
            </van-col>
            <van-col><h1><van-icon name="arrow" /></h1></van-col>
        </van-row>
        </div>
        <div class="card" style="text-align: left;">
            <van-card
            v-for="(item,index) in goods"
            :key = index
            :num="item.count"
            :price="item.price"
            :desc="item.describes"
            :title="item.goodsName"
            :thumb="item.img"
            />
        </div>
        <van-submit-bar :price="this.sum * 100" button-text="提交订单" @submit="onSubmit" />
    </div>
</template>

<script>
    import { Toast } from 'vant';
    export default {
        data(){
            return{
                sum:0,
                arr:this.$route.query.name,
                uid:window.localStorage.getItem("uid"),
                list: [],
                goods: [],
            }
        },
        mounted() {
            let ids = localStorage.getItem("ids")
            console.log(this.arr)
            this.sum = 0
            for(let i = 0;i<this.arr.length;i++){
                this.$ajax.payCart(this.arr[i]).then(
                res => {        
                if(res.code == 100) {      
                    this.goods.push(res.data) 
                    this.sum += res.data.price * res.data.count
                }
                else {
                    console.log(res);
                }
                })
            }

            if(ids != -1){
                this.$ajax.getAddressById(ids).then(
                res => {        
                    if(res.code == 100) {      
                    this.list = res.data;   
                }
                else {
                    console.log(res);
                }
                })
            }
            else{
            this.$ajax.getAddressDefault(this.uid).then(
                res => {        
                if(res.code == 100) {      
                    if(res.data != 1)
                        this.list = res.data;   
                    else
                    {
                        Toast.fail("暂无地址，请点击上方添加")
                    }
                }
                else {
                    console.log(res);
                }
                })
            }
        },
        methods: {
            undo(){
                this.$router.go(-1);
            },
            chooes(){
                this.$router.push({path:"/address?id=" + 1})
                localStorage.setItem('ids',-1)
            },
            onSubmit(){
                if(this.list.name != null)
                {
                    Toast.success("付款成功")
                    for(let j=0;j<this.arr.length;j++)
                    {
                        this.$ajax.addOrder({orderNo:j,goodsId:this.goods[j].goodsId,productCount:this.goods[j].count,totalPrice:this.goods[j].price * this.goods[j].count,addressId:this.list.id,userId:this.uid}).then(
                        res => {        
                        if(res.code == 100) {      
                            Toast.success("订单已生成")
                        }
                        else {
                            console.log(j);
                            console.log(this.goods[j].goodsId);
                            console.log(this.goods[j].count);
                            console.log(this.goods[j].price * this.goods[j].count);
                            console.log(this.list.id);
                            console.log(res);
                        }
                        })
                    }
                }
                else
                    Toast.fail("暂无地址，请点击上方添加")
            },
            
        }
      
    }
    </script>

<style scoped>
    .address{
        height: 20vh;
        background-color: white;
        text-align: left;
        border: 1px dotted darkslategray;
        border-radius: 15px;
        overflow: auto;
    }
</style>