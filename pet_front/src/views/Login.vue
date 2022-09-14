<template>
  <div class="login">
    <div class="top">
        <van-nav-bar left-arrow placeholder @click-left="back"  />
    </div>
    <div class="main">
        <div class="title">
            <van-icon name="/logo.png"  size="2.6em"/><span>登录体验更多精彩</span> 
        </div>
        <div class="login_form">
            <van-form >
                <van-field
                    v-model="username"
                    name="用户名"
                    placeholder="用户名"
                />
                <van-field
                    v-model="password"
                    type="password"
                    name="密码"
                    placeholder="密码"
                />
                <div style="margin: 16px;">
                    <van-button round block type="info" native-type="submit" @click="login">登录 / 注册</van-button>
                </div>
            </van-form>
        </div>
        <div class="aggrement">
            <van-checkbox v-model="checked" icon-size="2em" shape="square">点击同意"<span class="t">用户协议</span>"和"<span class="t">隐私政策</span>"</van-checkbox>
        </div>
    </div>
    <div class="foot">
        <div class="icons">
            <van-icon name="/images/icon/微信登录.svg" size="2.2rem" />
            <van-icon name="/images/icon/华为.svg" size="2.2rem" @click="huawei" />
            <van-icon name="/images/icon/github.svg" size="2.2rem" />
        </div>
        
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";
export default {
  data() {
    return {
      username: "小明",
      password: "123456",
      checked: false,
      saveDisable: true,
      huawei_token: {
        access_token: "",
        expires_in: "",
        scope: "",
        token_type: "",
        id_token: "",
      },
    };
  },
  methods: {
    successLogin(res) {
      window.localStorage.setItem("token", res.data.token);
      window.localStorage.setItem("username", res.data.uname);
      window.localStorage.setItem("uid", res.data.id);
      window.localStorage.setItem("imgs", res.data.img);
      Toast({
              type: "loading",
              message: "欢迎您，" + res.data.uname,
              loadingType: "spinner",
              duration: 1000,
              onClose: () => {
                this.$router.push("/");
              },
            });
    },

    login() {
      this.$ajax
        .login({ uname: this.username, psw: this.password })
        .then((res) => {
          if (res.code === 100) {
            this.successLogin(res);
          } else {
            Toast({
              type: "html",
              message: "账号或密码错误",
              duration: 1000,
              className: "login_toast",
            });
          }
        })
        .catch((res) => {
          console.log("axios报错:" + res);
        });
    },
    /**
     * 获取华为授权码
     */
    huawei() {
      let url =
        "https://oauth-login.cloud.huawei.com/oauth2/v3/authorize?" +
        "response_type=code&access_type=offline&state=state_parameter_passthrough_value&client_id=106595639" +
        "&redirect_uri=http://localhost:8080/login" +
        "&scope=openid+profile";
      location.href = url;
    },
    back() {
      this.$router.go(-1);
    }
  },

  mounted() {
    /**
     * 授权成功 将授权码给后端进行处理
     * 后端检验token成功，返回用户信息
     */
    let code = this.$route.query.code;
    console.log(code);
    if (code !== undefined) {
      //当成功授权后
      this.$ajax.huawei_login(code).then(
        //从华为服务器获取token
        (res) => {
          console.log("正在发送token:\n" + res);
          this.$ajax.parseToken(res.data).then(
            //解析token,返回用户信息
            (res) => {
              if (res.data.code == 100) {
                this.successLogin(res.data);
              } else {
                Toast("访问频繁，请冲洗尝试")
                console.log(res.data.message);
              }
            }
          );
        }
      );
    }
  },
};
</script>

<style scope>
.login {
    background: #fff;
}
.top .van-icon-arrow-left:before {
    content: '\e668';
    font-size: 1.5em;
}

.main {
    margin-top: 2em;
}

.main .title {
    display: flex;
    align-items: center;
    margin-left: 1.5em;
}
.title span {
    font-size: 1.6em;
    font-weight: 500;
    margin-left: .5em;
}

/**表单样式 */
.login_form {
    width: 90%;
    margin: 2em auto;
}

.login_form .van-form {
    width: 100%;
    
}

.aggrement {
    width: 100vw;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: .5em;
}
.aggrement .van-checkbox__label {
    display: flex;
    align-items: center;
    line-height: .5em;
}
.t {
    color: rgb(37, 129, 241);
    font-weight: 600;
}
.foot {
    width: 100vw;
    display: flex;
    justify-content: center;
}
.icons {
    width: 60%;
    display: flex;
    justify-content: space-around;
    position: absolute;
    bottom: 10vh;
}

</style>

