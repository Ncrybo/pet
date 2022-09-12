<template>
    <div>
        <div class="header">
             <van-nav-bar left-arrow  @click-left="undo()" title="我的地址"/>          
        </div>
        <div class="dizhi">
            <van-address-list
                v-model="chosenAddressId"
                :list="list"
                default-tag-text="默认"
                @add="onAdd"
                @edit="onEdit"
                @click-item="chooes"
            />
        </div>
    </div>
</template>

<script>
    import { Toast } from 'vant';
    export default {
        data() {
            return {
            chosenAddressId: '1',
            list: [],
            uid:window.localStorage.getItem("uid"),
            };
        },
        mounted() {
            this.$ajax.getAddress(this.uid).then(
            res => {        
                if(res.code == 100) {      
                this.list = res.data;   
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
            onAdd() {
                this.$router.push({path:"/addaddress"})
            },
            onEdit(item) {
                this.$router.push('/editaddress?id=' + item.id);
            },
            chooes(item){
                if(this.$route.query.id == 1)
                {
                    localStorage.setItem('ids',item.id)
                    this.$router.go(-1);
                }
            }
        },
};
</script>

<style scoped>
    .dizhi{
        height: calc(100vh - 40px - 46px);
        overflow: auto;
    }
</style>