<template>
    <div>
        <div class="header">
            <van-nav-bar left-arrow  @click-left="undo" title="添加地址"/>          
       </div>
       <van-address-edit
        show-set-default
        tel-maxlength="11"
        :area-list="areaList"
        :area-columns-placeholder="['请选择', '请选择', '请选择']"
        @save="onSave"
        @change-default="setdefault"
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
            isDefault:false,
            uid:window.localStorage.getItem("uid"),
        };
    },
    methods: {
        undo(){
                this.$router.go(-1);
        },
        setdefault(){
            if(this.isDefault)
                this.isDefault = false
            else
                this.isDefault = true
        },
        onSave(content) {
            let address = content.city + content.province + content.county + content.addressDetail
            this.$ajax.addAddress({uid:this.uid,name:content.name,address:address,tel:content.tel,isDefault:this.isDefault}).then(
                res => {
                    if(res.code == 100)
                        Toast('添加成功');
                        this.$router.push('/address')
                }
            )
        },
    },
};
</script>