import axios from "axios"


axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = 'http://localhost:18083';
let instance = axios.create({});
/*
    请求拦截器， 每个请求都带上token
 */
instance.interceptors.request.use(
    config => {
        let token = window.localStorage.getItem('token');
        if (token) {
            console.log("有token")
            config.headers['Authorization'] = token;
        }
        else {
            console.log("没有token")
        }
        return config;
    });

instance.interceptors.response.use(
    //res.data  返回的是后端传回的数据
    res => res.status === 200 ? Promise.resolve(res.data) : Promise.reject(res),
    err => {
        return Promise.reject(err.response);
    }
);

export default {
    post(url, data) {
        return axios.post(url, data)
    },
    get(url,data) {
        return axios.get(url,data);
    },
    //董波
    getAllShop() {
        return instance.post('/getAllShop')
    },
    getShopInfo(shopName) {
        return instance.get('/getShopInfo?shopName='+ shopName);
    },
    login(data) {
        return instance.post('/login', data)
    },
    getShopGoods(shoName) {
        return instance.get("/getShopGoods?ShopName=" + shoName);
    },
    getTreePet(shopName) {
        return instance.get("/getTreePet?shopName=" + shopName);
    },
    verifyToken() {
        return instance.get("/verifyToken");
    },
    checkGoods(type,shoName){
        return instance.get("/checkGoods?type=" + type + "&shopName=" + shoName);
    },
    huawei_login(code) {
        let axios = require('axios');
        let qs = require('qs');
        let data = qs.stringify({
            'client_id': '106595639',
            'grant_type': 'authorization_code',
            'code': code,
            'redirect_uri': 'http://localhost:8080/login',
            'client_secret': '298571224bfff62ed7728efdd2798260182a588b9d4e62084a99740c93ae2c22'
        });
        let config = {
            method: 'post',
            baseURL: 'http://localhost:8080',
            url: '/huawei/oauth2/v3/token',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: data
        };
        return axios(config);
    },

    parseToken(data) {
        return axios.post('/parseToken', data);
    },






    //柳明浪
    getCart(User){
        return instance.post('/getCart/'+User)
    },
    addCount(id){
        return instance.post('/addCount/'+id)
    },
    downCount(id){
        return instance.post('/downCount/'+id)
    },
    detCart(id){
        return instance.post('/detCart/'+id)
    },
    addCart(uid,gid){
        return instance.post('/addCart/'+uid+'/'+gid)
    },

    detAddress(id){
        return instance.post('/detAddress/'+id)
    },
    getAddress(uid){
        return instance.post('/getAddress/'+uid)
    },
    addAddress(uid,name,address,tel){
        return instance.post('/addAddress/'+uid+'/'+name+'/'+address+'/'+tel)
    },
    editAddress(uid,name,address,tel,id){
        return instance.post('/editAddress/'+uid+'/'+name+'/'+address+'/'+tel+'/'+id)
    },

    //李炜东
    addUser(uname,psw){
        return instance.post('/addUser/'+uname+'/'+psw)
    },
    getAllGoods(){
        return instance.post('/getAllGoods')
    },
    //潘书剑



}
