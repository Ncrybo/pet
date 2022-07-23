<template>
  <div class="cart">
    <div class="nav">
      <van-nav-bar title="购物车" left-text="返回" :fixed="true" left-arrow>
        <template #right>
          <van-icon name="search" size="18" />
        </template>
      </van-nav-bar>
    </div>
    <van-checkbox-group v-model="results" ref="checkboxGroup" 
     @change="(names)=>setTotalPrice()" >  <!--如果复选框选中值发生变化，则总价发生变化-->
    <div class="cart-goods" v-for="(item,id) in shops" :key="id">
      <van-row>
        <van-col span="20" offset="1">
          <van-icon name="shop-o" />{{item.shopName}}
        </van-col>
      </van-row>
      <hr>
      <div class="cards" v-for="(obj,index) in item.list" :key="index">
      <div class="deto">
        <van-icon name="delete-o" size="18" @click='det(obj,id,index)'/>
      </div>
      <van-checkbox :name="obj.id" :value="obj.count" label-disabled>
      <van-card
      style="width: 300px;"
      :price="obj.price"
      :desc="obj.describes"
      :title="obj.goodsName"
      :thumb="obj.img"
    >
    </van-card>
  </van-checkbox>
    <div class="jiajian">
      <van-stepper :value="obj.count" async-change @plus="increase(obj)" @minus="decrease(obj)" disable-input/>
    </div>
    </div>
  </div>
  </van-checkbox-group>
    <van-submit-bar :price="totalprice*100" button-text="提交订单" class="vab">
      <van-checkbox v-model="checked" @change="checkAll">全选</van-checkbox>
    </van-submit-bar>

    <tabBar></tabBar><!--底部导航栏-->
  </div>
</template>

<script>
import tabBar from '../components/tabBar.vue';
import { Dialog } from 'vant';

export default {
  components: {tabBar},
  data() {
    return {
      results:[],
      checked:false,
      sum:0,
      shops:[],
      uid:window.localStorage.getItem("uid"),
      totalprice:0,
    }
  },

  mounted() {
        this.$ajax.getCart(this.uid).then(
          res => {        
            if(res.code == 100) {      
              this.shops = res.data;   
          }
          else {
              console.log(res);
          }
        })
      },

    methods: {
      checkAll() {
          if(this.checked)
              this.$refs.checkboxGroup.toggleAll(true);
          else
              this.$refs.checkboxGroup.toggleAll(false);
      },
      det(obj,id,index){
        this.shops[id].list.splice(index,1);
        this.$ajax.detCart(obj.id);
        if(this.shops[id].list.length === 0)
        {
            this.shops.splice(id,1);
        }
      },
      increase(obj){
        if(obj.type === "pet")
        {
          Dialog({ message: '宠物只有一只，不能加购' });
        }
        else{
          obj.count++;
          this.$ajax.addCount(obj.id);
          this.setTotalPrice();
        }
      },
      decrease(obj){
        obj.count--;
        this.$ajax.downCount(obj.id);
        this.setTotalPrice();
      },
      setTotalPrice() {
            this.totalprice = 0;
            for(var z=0;z<this.results.length;z++)
            for(var i=0;i<this.shops.length;i++)
            {
              for(var j=0;j<this.shops[i].list.length;j++)
              {
                if(this.results[z]===this.shops[i].list[j].id)
                  this.totalprice += this.shops[i].list[j].price*this.shops[i].list[j].count
              }
            }
      },
    },

}
</script>

<style scoped>
  .nav{
    height: 6vh;

  }
  .cart{
    text-align: left;
  }
  .deto{
    float: right; 
    margin-top: 8px;
    margin-right: 20px;
  }
  .jiajian{
    text-align: right;
    margin-right: 10px;
    margin-bottom: 10px;
  }
  .cards{
    margin: 10px 0px 10px 10px;
  }
  .cart-goods{
    margin: 10px 0px 10px 0px;
    border: 1px dotted darkslategray;
    border-radius: 15px;
    background-color: #fafafa;
  }
  .van-checkbox__label {
     height: 6vh;
  }
  .vab{
    margin-bottom: 50px;
  }
  .van-checkbox-group {
    height: calc(100vh - 8vh - 6vh - 50px);
    overflow: auto;
  }
</style>
