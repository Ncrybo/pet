<template>
  <div class="goodsInfo">
    <!--分享面板-->
    <van-share-sheet
      v-model:show="showShare"
      title="立即分享给好友"
      :options="options"
      @select="onSelect"
    />
    <!--顶部-->
    <div class="header">
      <van-nav-bar title="商品详情" left-arrow  @click-left="undo()" >
        <template #right>
          <van-icon
            name="share-o"
            size="26"
            color="block"
            @click="showShare = true"
          />
        </template>
      </van-nav-bar>
    </div>
    <van-dialog v-model="show" :title="goods.goodsName"  :before-close="beforeClose">
      <video ref="videoPlayer1" controls :src=goods.video :poster=goods.img controlslist="nodownload" 
           style="width: 100%; height: 100%; object-fit: fill">
      </video>
    </van-dialog>
    <!--正文-->
    <div class="body">
        <div class="petimg" @click="showImg"><img :src="goods.img" alt="" style="width: 100%; height:50vh"></div>
        <div>
          <van-row>
            <van-col class="price">
              ￥{{goods.price}}
            </van-col>
            <van-col class="preprice">
              ￥{{preprice}}
            </van-col>
          </van-row>
        </div>
     
        <div class="monthpay">支持分期|最低月付{{monthpay}}元</div>
        <div>
          <van-row gutter="10">
            <van-col class="title">{{goods.goodsName}}</van-col>
            <van-col class="tags"><van-tag type="primary">公</van-tag></van-col>
            <van-col class="tags"><van-tag color="Silver " text-color="darkslategray">未绝育</van-tag></van-col>
            <van-col class="tags"><van-tag color="Silver " text-color="darkslategray">宠物级</van-tag></van-col>
          </van-row>
        </div>
        <van-divider />
        <div class="texts">品相&nbsp&nbsp<van-tag type="warning">大骨架</van-tag>&nbsp&nbsp<van-tag type="warning">爆毛</van-tag></div>
        <div class="texts">生日&nbsp&nbsp2022-0401</div>
        <div class="texts">发布&nbsp&nbsp30分钟前</div>
        <div class="texts">描述&nbsp&nbsp{{goods.describes}}</div>
        <van-divider />
        <div class="texts">配送&nbsp&nbsp<van-icon name="paid" color="orange"/>自提&nbsp&nbsp0元</div>
        <div class="texts">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<van-icon name="logistics" color="orange"/>汽运&nbsp&nbsp260元</div>
        <div class="texts">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<van-icon name="guide-o" color="orange"/>空运&nbsp&nbsp560元</div>
        <van-divider />
        <div class="texts">服务&nbsp&nbsp<span style="color: orange;">
          <van-icon name="certificate" color="orange"/>最长担保365天健康&nbsp&nbsp
          <van-icon name="certificate" color="orange"/>最高保额15000元</span></div>
        <div class="texts">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span style="color: orange;">
          <van-icon name="certificate" color="orange"/>养宠套餐服务&nbsp&nbsp
          <van-icon name="certificate" color="orange"/>疫苗接种服务</span></div>
        <van-divider />
        <div class="assess"><van-cell title="用户评价" is-link to="/HomeView" value="更多评价"/>
            <div v-for="item in assesses">
              <van-row gutter="10">
                <van-col offset="1">
                  <img :src="item.img" alt="" style="height: 50px; width: 50px;">
                </van-col>
                <van-col>
                  <van-row>{{item.name}}</van-row>
                  <van-row><van-rate v-model="item.score" readonly /></van-row>
                  <van-row>{{item.assess}}</van-row>
              </van-col>
              </van-row>
              <br>
            </div>
        </div>
        
    </div>
    <div class="vab">
      <van-goods-action>
        <van-goods-action-icon icon="chat-o" text="客服" dot />
        <van-goods-action-icon icon="cart-o" text="购物车" badge="5" @click="gotocart"/>
        <van-goods-action-icon icon="shop-o" text="店铺" badge="12" @click="gotoshop"/>
        <van-goods-action-button type="warning" text="加入购物车" @click="addCart"/>
 <!--      <van-goods-action-button type="danger" text="立即购买" />  --> 
      </van-goods-action>
    </div>
  </div>
</template>

<script>
    import { Toast } from 'vant';
    import { ImagePreview } from 'vant';
    export default {
        name: 'goodsInfo', //组件名
        data() {
            return {
              show:false,
              showShare: false,
              goodsId: this.$route.query.goodsId,
              preprice: 0,
              monthpay: 0,
              options: [
                { name: '微信', icon: 'wechat' },
                { name: '微博', icon: 'weibo' },
                { name: '复制链接', icon: 'link' },
                { name: '分享海报', icon: 'poster' },
                { name: '二维码', icon: 'qrcode' },
              ],
              assesses:[
                {
                  img: require("../../public/images/pet/cat2.jpg"),
                  name: "手机用户12345",
                  assess:"默认好评",
                  score:5
                },
                {
                  img: require("../../public/images/pet/cat2.jpg"),
                  name: "手机用户12345",
                  assess:"默认好评",
                  score:5
                },
                {
                  img: require("../../public/images/pet/cat2.jpg"),
                  name: "手机用户12345",
                  assess:"默认好评",
                  score:5
                }
              ],
              goods:[],
            }
        },
        methods: {
          undo(){
                this.$router.go(-1);
          },
          showImg(){
            if(this.goods.video == null)
              ImagePreview({images:[this.goods.img]});
            else
              this.show=true
          },
          beforeClose : function (action, done) { // 点击事件 - 备注按钮提示框
              if (action === 'confirm') { // 确认
                this.$refs.videoPlayer1.pause();
                done(); // 关闭提示框
              }
          },
          onSelect(option) {
            console.log(this.goods)
            Toast(option.name);
            this.showShare = false;
          },
          gotocart(){
            this.$router.push("/cart")
          },
          gotoshop(){
            this.$router.push("/shopInfo?shopName=" + this.goods.shopName);
          },
          addCart(){
            let uid = window.localStorage.getItem("uid")
            let token = window.localStorage.getItem('token');
            if (token) {
              this.$ajax.addCart(uid,this.goodsId).then(
                res => {        
                    if(res.data)
                    {
                      Toast.success("添加成功")
                    }    
                    else
                    {
                      Toast.fail("添加失败，商品已存在")
                    }
              });
            }
            else{
              Toast.fail("请先登录")
            }
          },
        },
       mounted() {
        this.$ajax.getGoodsById(this.goodsId).then(
          res => {        
            if(res.code == 100) {      
              this.goods = res.data;   
              this.preprice = this.goods.price * 2
              this.monthpay = parseFloat(this.goods.price / 12).toFixed(2)
          }
          else {
              console.log(res);
          }
        })
      },

    }
</script>

<style scoped>
  .header{
    height: 3vh;
  }
  .body{
    text-align: left;
    height: 90vh;
    overflow: auto;
  }
  .price{
    color: red;
    height: 3vh;
    font-size: 1em;
    margin-left: 0.3em;
  }
  .preprice{
    font-size: 0.7em;
    text-decoration:line-through;
    height: 3vh;
    padding-top: 0.5em;
    margin-left: 0.4em;
  }
  .monthpay{
    text-align: left;
    font-size: 0.7em;
    padding-bottom: 1vh;
    margin-left: 0.7em;
  }
  .title{
    font-weight: bold;
    font-size: 1.1em;
    margin-left: 0.4em;
  }
  .tags{
    margin-top: 2px;
  }
  .texts{
    font-size: 0.8em;
    height: 20px;
    margin-left: 0.4em;
  }
  .assess{
    font-size: 0.8em;
  }
</style>