<template>
  <div>
    <van-nav-bar
      style="width: 100%; height: 50px;"
      title="快速注册">
        <template #right>
            <van-icon name="ellipsis" size="18" />
        </template>
        <template #left>
            <van-icon name="arrow-left" size="18" @click="$router.back()" />
        </template>
    </van-nav-bar>
        

    <div>
      <img
        src="/images/用户头像/tou3.jpg"
        style="position: relative; top: 50px; width: 130px; height: 130px"
      />
    </div>
    <h3 style="position: relative; top: 50px">请注册</h3>
    <div style="position: relative; top: 50px; text-align:center;">
      <van-form >

        <van-field
         @change="changebutton()"
          v-model="id"
          name="用户名"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <hr />
      
        <van-field
        @change="changebutton()"
          v-model="password"
          type="password"
          name="密码"
          label="密码"
          placeholder="密码"
          minlength = 8 
          onkeyup="this.value=this.value.replace(/\s+/g,'')"
          :rules="[
                  { 
                    required: true, 
                    trigger:'onBlur',
                    message: '请填写密码' 
                  },
                  {
                    pattern: /^.*(?=.{8,20})(?=.*\d)(?=.*[A-Z]{1,})(?=.*[a-z]{1,})(?=.*[!@#$%^&*?\(\)]).*$/,
                    message: '新密码8-20位，必须包含数字小写字母大写字母以及特殊字符',
                    trigger: 'onBlur'
                  }
                  ]"
        />

        <hr />
        
        <van-field  
        @change="changebutton()"
              name="确认密码" 
              v-model="confirmPassword" 
              label="确认密码" 
              placeholder="确认密码" 
              type="password" 
              minlength = 8 
              onkeyup="this.value=this.value.replace(/\s+/g,'')"
              :rules="[
                    { 
                        required: true,
                        trigger:'onBlur', 
                        message: '请填写确认密码' 
                    },
                    {
                        pattern: /^.*(?=.{8,20})(?=.*\d)(?=.*[A-Z]{1,})(?=.*[a-z]{1,})(?=.*[!@#$%^&*?\(\)]).*$/,
                        message: '确认密码一致',
                        trigger: 'onBlur'
                    }
                ]"  
            />

        <hr />

        <div style="margin: 16px">
          <van-button @click="zhuce()"
            :disabled="saveDisable"
            round
            block
            type="info"
            native-type="submit"
            style="width: 300px; display: block; margin: 0 auto"
            >注册
          </van-button>
          
        </div>
         </van-form>
         </div>
  </div>
</template>

<script>
import { Dialog } from 'vant';
export default {
  data() {
    return {
      id: "",
      password: "",
      confirmPassword: "",
      saveDisable: true,
    };
  },
  methods: {

   zhuce(){
        this.$ajax.addUser(this.id,this.confirmPassword);
        Dialog({ message: '恭喜你注册成功！' });
        this.$router.push({ path: "/login" });
   },
   changebutton(){
      console.log(this.id.length);
      console.log(this.password.length);
      console.log(this.confirmPassword.length);
        if(this.id.length>0 && this.password.length>0 && this.confirmPassword.length>0)
          this.saveDisable=false;
   }

  }
};
</script>

<style scoped>
   
</style>
