<template>
  <div class="shop-car">
      <!-- <div class="avatar"><img src="../../public/img/shop1.jpg" alt=""></div>
      <div class="shopName"><strong>喵的幸福</strong></div>
      <div class="pingfen"><van-rate v-model="star" size="10" allow-half void-icon="star" color="#ffd21e" void-color="#eee" readonly="true" /></div>
      <div class="address">福建省厦门市集美区理工路60号</div> -->
      <div class="petImg">
        <div class="imgItem">
          <div class="img"><img src="/dbImages/cat1.jpg" alt=""></div>
          <div class="details">
            <div class="petName">英短金渐层</div>
            <div class="price">￥4588</div>
          </div>
        </div>

        <!-- <div class="imgItem">
          <div class="img"><img src="../../public/img/cat1.jpg" alt=""></div>
          <div class="details">
            <div class="petName">英短金渐层</div>
            <div class="price">￥4588</div>
          </div>
        </div>
        <div class="imgItem">
          <div class="img"><img src="../../public/img/cat1.jpg" alt=""></div>
          <div class="details">
            <div class="petName">英短金渐层</div>
            <div class="price">￥4588</div>
          </div>
        </div> -->
      </div>
    </div>
    
</template>

<script>
export default {
    data() {
        return {
            star: 3.5,
        }
        
    }
}
</script>

<style scoped>
.shop-car {      /*商家卡片 */
  width:100vw;
  height: 58vh;
  background: #fff;
  margin: 8px 0;
  padding: 4px;
  display: grid;
  grid-template-rows: 1.5fr 1fr 6fr;
  grid-template-columns: 4fr 10fr 6fr;
}
.avatar {
  grid-row: 1/3;
  grid-column: 1/2;
  
}
.shopName {
  grid-row: 1/2;
  grid-column: 2/3;
  text-align: left;
  margin-left: 3px;
  display: flex;
  align-items: center;
}
.address {
  grid-row: 2/3;
  grid-column: 2/3;
  text-align: left;
  margin-left: 3px;
  font-size: .8em;
}

.petImg {
  grid-row: 3/4;
  grid-column: 1/4;
  display: flex;
  justify-content: space-around;
  align-content: center;
  margin-top: 5px;
}
.imgItem {
  width: 80%;
}
.pingfen {
    width: 100%;
    grid-row: 1/2;
    grid-column: 3/4;
    text-align: center;
}
img {
  width: 100%;
  object-fit: cover;
}
.avatar img {
    width:80%;
    border-radius: 50%;
}
.details {
    width: 100%;
    display: flex;
    justify-content: space-between;
    font-size: 0.2em;
}
.price {
    color: red;
}
</style>