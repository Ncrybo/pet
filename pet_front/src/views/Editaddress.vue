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
        address:"",
        uid:window.localStorage.getItem("uid"),
        };
    },
    methods: {
        undo(){
            this.$router.go(-1);
        },
        onSave(content) {
            this.address = content.city + content.province + content.county + content.addressDetail    
            Toast('save success')
            this.$ajax.editAddress(this.uid,content.name,this.address,content.tel,3)
            this.$router.push({path:"/address"})
        },
        onDelete() {
            Toast('delete success')
            this.$ajax.detAddress(3)
            this.$router.push({path:"/address"})
        },
    },
};
</script>