<template>

    <!-- 로그인 후, or '상품관리 탭'클릭 시 기본으로 보여줄 페이지 -->

    <div id="products" v-bind:class="item.productName" v-for="(item, index) in listData" :key="index">
        <v-card class="mx-auto" max-width="70%">
            <v-card-item class="products" >
                <div>
                    <div class="text-h6 mb-1">
                        {{ item.productName }}
                    </div>
                    <div class="text-overline mb-1">
                        <span>
                            상품 기간 : {{ item.period }}개월
                            금리 : {{ item.interestRate }}%
                            최소 가입 금액 : {{ item.requiredStartMoney }}원
                        </span>
                    </div> 
                    <div class="text-caption">{{ item.longInfo }}</div>
                </div>
            </v-card-item>
            <div class="d-flex justify-end align-center">
                <v-card-actions>
                    <!-- 이부분 subscribeProduct X -> 수정페이지로 가도록 고치기  -->
                    <v-btn variant="outlined" @click="EditProduct(item.productId)">  <!-- href="http://localhost8080/admin/product/edit" -->
                        수정
                    </v-btn>
                    <!-- 이부분 subscribeProduct X -> 삭제 -->
                    <v-btn variant="outlined" @click="deleteProduct(item.productId)">  
                        삭제
                    </v-btn>
                </v-card-actions>
            </div>
        </v-card>
    </div>
</template>




<script setup>
import axios from 'axios'
import { watchEffect, ref } from 'vue'
import router from '../router'


// import { mdiConsoleNetwork } from '@mdi/js';

// 서버에서 받아오는 정보
const listData = ref([]);


// Axios 인스턴스 생성
const axiosInstance = axios.create({
    // baseURL: 'http://localhost:8080', // 서버의 주소
    baseURL: 'http://localhost:8080/admin', // 후에 관리자 서버만들어서 주소넣기
    // withCredentials: true // CORS 요청에 관련된 설정을 포함
})



// axios({
//         method:"post",
//         url:"http://localhost:8080/admin",
//         data : requestData,
//         headers: {
//           Authorization: `Bearer ${localStorage.getItem('accessToken')}`, // 토큰 포함
//           // "Content-Type":"application/json" -1개 데이터 직접 보낼때 사용
//         },
//     })



watchEffect(() => {
    axiosInstance.get('/product').then((res) => {
        let tempArr = [...res.data]
        tempArr.forEach((item) => {
            console.log(item)
            listData.value.push(item)
        })
        console.log(listData);
    })
})


// 수정하기 버튼
const EditProduct = (productId) => {
    router.push({ name: 'edit_product', params: { productId: productId } });
}



// 삭제하기 버튼
const deleteProduct=(productId)=>{
    const url=`http://localhost:8080/admin/product/${productId}}/delete`
    axios.delete(url).then(response=>{console.log("삭제 완료 제발")});
}

</script>


<style lang="scss" scoped>

.mx-auto {
    text-align: center;
    justify-content: center;
}

.mx-auto button {
    margin: auto;
}

.container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: repeat(3, minmax(100px, auto));
    grid-gap: 20px;
    margin: 10px 20rem 10rem 20rem;
}

</style>