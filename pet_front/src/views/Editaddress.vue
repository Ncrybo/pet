<template>
    <div>
        <div class="header">
            <van-nav-bar left-arrow  @click-left="undo" title="编辑地址"/>          
       </div>
       <van-address-edit
        show-set-default
        tel-maxlength="11"
        show-delete
        :show-area="false"
        @save="onSave"
        @delete="onDelete"
        :address-info="{
            name: info.name,
            tel: info.tel,
            isDefault: info.isDefault,
            addressDetail: info.address
          }"
     />
    </div>
</template>

<script>
import { Toast } from 'vant';
export default {
    data() {
        return {
            id:this.$route.query.id,
            info:[],
            uid:window.localStorage.getItem("uid"),
        };
    },
    mounted() {
        this.$ajax.getAddressById(this.id).then(
        res => {        
            if(res.code == 100) {      
            this.info = res.data;   
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
        onSave(content) {
            this.$ajax.editAddress({uid: this.uid, name: content.name, address: content.addressDetail, tel: content.tel, isDefault: content.isDefault,id: this.id}).then(
                res => {
                    if(res.code==100)
                    {
                        Toast.success('修改成功')
                        this.$router.push({path:"/address"})
                    }
                    else
                        console.log(res)
                }
            )
        },
        onDelete() {
            this.$ajax.detAddress(this.id).then(
                res => {
                    if(res.code == 100)
                    {
                        Toast.success('删除成功')
                        this.$router.push({path:"/address"})
                    }
                    else
                        console.log(res)
                }
            )
        },
    },
};
</script>