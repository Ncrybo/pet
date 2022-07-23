<template>
    <div>
        <div class="header">
            <van-nav-bar left-arrow  @click-left="undo()" title="编辑地址"/>          
       </div>
       <van-address-edit
        :area-list="areaList"
        show-delete
        :area-columns-placeholder="['请选择', '请选择', '请选择']"
        @save="onSave"
        @delete="onDelete"
        :address-info="{
            name: info.name,
            tel: info.tel,
            addressDetail: info.address,
          }"
     />
    </div>
</template>

<script>
import { areaList } from "@vant/area-data";
import { Toast } from 'vant';
export default {
    data() {
        return {
            areaList,
            id:this.$route.query.id,
            info:[],
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
            let address = content.city + content.province + content.county + content.addressDetail    
            Toast('save success')
            this.$ajax.editAddress(content.name,address,content.tel,this.id)
            this.$router.push({path:"/address"})
        },
        onDelete() {
            Toast('delete success')
            this.$ajax.detAddress(this.id)
            this.$router.push({path:"/address"})
        },
    },
};
</script>