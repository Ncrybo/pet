<template>
    <div>
        <div class="header">
            <van-nav-bar left-arrow  @click-left="undo()" title="添加地址"/>          
       </div>
       <van-address-edit
        :area-list="areaList"
        :area-columns-placeholder="['请选择', '请选择', '请选择']"
        @save="onSave"
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
            Toast('save success');
            this.$ajax.addAddress(this.uid,content.name,this.address,content.tel)
            this.$router.push('/address')
        },
    },
};
</script>