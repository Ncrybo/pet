<template>
    <div class="shopInfo">
        <div class="nav">
            <van-nav-bar
                title="店铺详情"
                left-text="返回"
                left-arrow
                :fixed="true"
                @click-left="back"
            />
        </div>
        <div class="shopFirst item">  <!--商店的概要，名字，星星等-->
            <div class="touxiang"><img :src="shop.shopImg" alt=""></div>
            <div class="row1">
                <div class="name"><strong>{{shopName}}</strong></div>
                <div class="star">
                    <van-rate v-model="shop.shopStar" size="10" allow-half void-icon="star" 
                    color="#ffd21e" void-color="#eee" :readonly="true" />
                </div>
                <div class="fenshu">{{shop.shopStar}}<span>分</span></div>
            </div>
            <div class="row2">
                <div class="col1"><p>平台认证</p></div>
                <div class="col2"><p>已缴纳保证金</p></div>
                <div class="col3"><p>实体店</p></div>
            </div>
            <div class="row3">
                近期交易<span>7</span>笔 | 好评<span>5</span>条 | 成交价均<span>34254</span>元
            </div>

        </div>
        <div class="address item">  <!--地址-->
            <div class="content">
                <div class="address_title">地址</div>
                <p class="address_body">{{shop.shopProvince + shop.shopCity + shop.shopStreet}}</p>
                <div class="daohang" @click="toMap()">导航 <van-icon name="arrow" size="1em" /></div>
            </div>
            
        </div>
        <!-- ==========商品滑动标签============= -->
        <van-tabs class="goodsCard" v-model="active" @change="changeGoods" animated swipeable >
            <van-tab v-for="type in types" :title="type" :key="type" >
                <van-card v-for="item in goods" :key="item.id"
                :price="item.price"
                :desc="item.describes"  
                :title="item.goodsName"
                :thumb="item.img"
                @click="toInfo(item.id)"
                />
            </van-tab>
        </van-tabs>

    </div>

</template>

<script>
export default {
    data() {
        return {
            active:0,
            shopName: this.$route.query.shopName,
            types: ['宠物', '服务', '商品'],
            goods: [],
            shop: {},
        }
    },
    methods: {
        back() {
            this.$router.go(-1);
        },
        toMap() {
            this.$router.push('/map')
        },
        changeGoods(index,title) {
<<<<<<< .mine
            if(title == '宠物') title = 1;
            else if(title == '服务') title = '3';
            else if(title == '商品') title = '2';
=======
            if(title == '宠物') title = '1';
            else if(title == '服务') title = '3';
            else if(title == '商品') title = '2';
>>>>>>> .theirs
            this.$ajax.checkGoods(title,this.shopName). then(
                res => {
                    this.goods = res.data;
                }
            )
        },
        toInfo(goodsId) {
            this.$router.push('/goodsInfo?goodsId=' + goodsId);
        }
    },
    mounted() {
        //通过商店名字，获取商店所有信息
        this.$ajax.getShopInfo(this.shopName).then(
            res => {
                this.shop = res.data;
                this.star = res.data.shopStar;
            }
        );
<<<<<<< .mine
        //通过商店名称 和 商品种类 更新显示的商品数据，默认显示宠物
        this.$ajax.checkGoods(1,this.shopName).then(
=======
        this.$ajax.checkGoods("1",this.shopName).then(

>>>>>>> .theirs
            res => {
                this.goods = res.data;
            }
        )
    }
    
};
</script>

<style src="../assets/shopInfo.css" scoped>

</style>


