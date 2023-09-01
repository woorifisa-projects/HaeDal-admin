<template>

    <!-- 로그인 후, or '상품관리 탭'클릭 시 기본으로 보여줄 페이지 -->

    <div id="products" v-bind:class="item.productName" v-for="(item, index) in listData" :key="index">
        <v-card v-if="item.productStatus === true" class="mx-auto" max-width="70%"  >
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
                    <v-btn variant="outlined" @click="editProduct(item.productId)">  <!-- href="http://localhost8080/admin/product/edit" -->
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

    <div class="addproduct">

        <v-btn variant="outlined" @click= "addProduct">
            추가
        </v-btn>
    </div>


    <div class="text-center">

<v-dialog
  v-model="dialog.isOpen.value" 
  width="auto"
>
  <v-card>
    <v-card-text>
      상품이 삭제되었습니다!
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" block @click="dialog.closeDialog">확인</v-btn>
    </v-card-actions>
  </v-card>
</v-dialog>
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


// watchEffect(() => {
//     axiosInstance.get('/product').then((res) => {
//         listData.value = res.data.filter(item => item.productStatus === true);
//     })
// })





// 삭제 시 다이얼로그 창
const dialog = {
    isOpen: ref(false),
    openDialog() {
      dialog.isOpen.value = true; // 다이얼로그 열기
    },
    closeDialog() {
      dialog.isOpen.value = false; // 다이얼로그 닫기
      router.push({ name: 'product_management' }); // '상품관리' 경로로 이동
    }
  };


// 수정하기 버튼
const editProduct = (productId) => {
    router.push({ name: 'edit_product', params: { productId: productId } });
    console.log('수정됨');
}




// 삭제하기 버튼
const deleteProduct = async (productId) => {
    const url = `http://localhost:8080/admin/product/${productId}/delete`;
    // axiosInstance.get(url).then((res) => {
    //     listData.value = res.data.filter(item => item.productStatus === false);
    // });

    try {
        const response = await axiosInstance.post(url);

        
        // if (response.status === 200) {
        //     // 서버에서 업데이트된 데이터 받아오기
        //     // axiosInstance.get('/product').then((res) => {
        //     //     listData.value = res.data.filter(item => item.productStatus === true);
        //     // });
        // }

        if (response.status === 200) {
            // POST 요청 성공 시 로직
        console.log(response.data);
        // productName.value = values.productName;
        dialog.openDialog();
        console.log("모달창띄웟다");
        // 수정이 완료되었을 때 다시 상품관리 경로로 이동
        router.push({ name: 'product_management'});
        console.log("페이지 이동 성공!")

        }

        // //     // 요청이 성공되었을때, 서버에서 업데이트된 데이터 받아오기
        //     axiosInstance.get('/product').then((res) => {
        //         listData.value = res.data.filter(item => item.productStatus === true);
        //     });
        // }
    } catch (error) {
        console.error(error);
    }

};


// 추가 버튼
const addProduct = () => {
    router.push({ name: 'add_product'});
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

.addproduct { 
    margin: auto;

}

</style>