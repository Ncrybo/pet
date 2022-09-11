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
    </div>
</template>

<script>
    export default {
        data(){
            return{
                sum:0,
                uid:window.localStorage.getItem("uid"),
                list: []
            }
        },
        mounted() {
            this.$ajax.getAddressDefault(this.uid).then(
            res => {        
            if(res.code == 100) {      
                if(res.data != 1)
                    this.list = res.data;   
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
            chooes(){
                this.$router.push({path:"/address?id=" + 1})
            }
            
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