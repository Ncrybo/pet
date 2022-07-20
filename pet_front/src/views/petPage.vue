<template>
    <div class="petPage">
        <div class="card" v-for="item in shopGoods" :key="item.GoodsId">
            <div class="img" @click="toGoodsPage(item.GoosId)"><img :src="item.img" alt=""></div>
            <div class="name"><strong>{{item.goodsName}}</strong></div>
            <div class="price">￥{{item.price}}</div>
        </div>
    </div>
</template>

<script>
import { Toast } from 'vant';
export default {
    data() {
        return {
            shopGoods: [],
        }
    },
    methods: {
        toGoodsPage(goodsId) {
            this.$router.push("/goodsInfo?goodsId="+ goodsId);
        }
    },

    mounted() {
        this.$ajax.getShopGoods(this.$route.query.shopName).then(
            res => {
                if(res.code == 100) {
                    this.shopGoods = res.data;
                }
                else {
                    Toast("未查询到商品");
                }
            }
        )
    }

}
</script>

<style scoped>
* {
    margin: 0;
}
.petPage {
    width: 100vw;
    text-align: left;
}
.petPage .card {
    width: 45vw;
    margin: calc(10vw / 5);
    float: left;
}
.petPage img {
    width: 45vw;
    height: 45vw;
    object-fit: cover;
}
.name {
    text-align: left;
    padding: 2px;
}
.price {
    text-align: left;
    color: magenta;
}
</style>