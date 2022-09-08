<template>
    <div>
        <div class="header">
             <van-nav-bar left-arrow  @click-left="undo" title="设置"/>
        </div>
        <div class="shezhi">
            <br>
            <van-row style="height: 60px;line-height: 60px;">
            <van-col span="5" offset="1">
            <img class="touxiang" :src="touxiang" alt="">
            </van-col>
            <van-col span="6">{{uname}}</van-col>
            </van-row>
            <br>
        </div>
        <div style="text-align: left;">
            <van-cell title="个人信息" is-link />
            <van-cell title="修改密码" is-link />
            <van-cell title="设置人脸/指纹" is-link />
            <van-cell title="修改地址" is-link router-link to ="/address" />
        </div>
        <br><br>
        <van-button round type="info" style="width: 90%;" @click="logout">退出登录</van-button>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                uname:localStorage.getItem("username"), 
                touxiang: localStorage.getItem("imgs"),
            }
        },
        methods: {
            undo(){
                this.$router.go(-1);
            },
            logout() {
                localStorage.removeItem("username");
                localStorage.removeItem("token");
                this.$router.push('/my')
            }
        }
      
    }
    </script>

<style scoped>
    .touxiang {
        width: 60px; 
        height: 60px;
        border-radius: 50%;
    }
</style>