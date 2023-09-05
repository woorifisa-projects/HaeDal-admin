<template>
    <!-- 로그인 후, or '상품관리 탭'클릭 시 기본으로 보여줄 페이지 -->

    <div>
        <v-layout class="overflow-visible" style="height: 56px; box-shadow: none; margin-bottom:30px;">
            <v-bottom-navigation v-model="value" color="teal" grow>
                <v-btn @click="inProgressService">
                    현재 서비스 상품
                </v-btn>

                <v-btn @click="doneService">
                    서비스 만료 상품
                </v-btn>
            </v-bottom-navigation>
        </v-layout>

    </div>



    <div id="products" v-bind:class="item.productName" v-for="(item, index) in listData" :key="index">
        <v-card class="mx-auto" max-width="70%">
            <v-card-item class="products">
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
                    <v-btn variant="outlined" @click="editProduct(item.productId)">
                        <!-- href="http://localhost8080/admin/product/edit" -->
                        수정
                    </v-btn>
                    <!-- 이부분 subscribeProduct X -> 삭제 -->
                    <v-btn variant="outlined" @click="deleteProduct(item.productId)" v-if="item.productStatus === true">
                        삭제
                    </v-btn>
                    <v-btn variant="outlined" @click="returnProduct(item.productId)" v-if="item.productStatus === false">
                        재등록
                    </v-btn>

                </v-card-actions>
            </div>
        </v-card>
    </div>

    <div class="addproduct">
        <v-btn variant="outlined" @click="addProduct">
            상품 추가
        </v-btn>
    </div>


    <div class="text-center">
        <v-dialog v-model="deleteDialog.isOpen.value" width="auto">
            <v-card>
                <v-card-text>
                    상품이 삭제되었습니다!
                </v-card-text>
                <v-card-actions>
                    <v-btn color="primary" block @click="deleteDialog.closeDialog">확인</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>


    <div class="text-center">
        <v-dialog v-model="returnDialog.isOpen.value" width="auto">
            <v-card>
                <v-card-text>
                    상품이 재등록되었습니다!
                </v-card-text>
                <v-card-actions>
                    <v-btn color="primary" block @click="returnDialog.closeDialog">확인</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>




<script setup>
import axios from 'axios'
import { watchEffect, ref } from 'vue'
import router from '../router'


// 서버에서 받아오는 정보
const listData = ref([]);

// Axios 인스턴스 생성
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/admin', // 후에 관리자 서버만들어서 주소넣기

})


watchEffect(() => {
    listData.value = [];
    axiosInstance.get('/product').then((res) => {
        // 서버로부터 받아온 데이터 중 productStatus가 false인 것만 필터링
        const filteredData = res.data.filter(item => item.productStatus === true);

        // 필터링된 데이터를 listData에 할당
        listData.value = filteredData;
        console.log(listData);
    })
})

// 현재 서비스 중인 상품들만 나타내는 목록(productStatus = true(1))
const inProgressService = () => {
    listData.value = [];
    axiosInstance.get('/product').then((res) => {
        // 서버로부터 받아온 데이터 중 productStatus가 false인 것만 필터링
        const filteredData = res.data.filter(item => item.productStatus === true);

        // 필터링된 데이터를 listData에 할당
        listData.value = filteredData;

        console.log(listData);
    });
}



// 만료된 서비스 상품들만 나타내는 목록(productStatus = false(0)) -> 상품 '삭제'하여 HaeDal 클라이언트에 보이지 않는 목록
const doneService = () => {
    listData.value = [];
    axiosInstance.get('product').then((res) => {
        // 서버로부터 받아온 데이터 중 productStatus가 false인 것만 필터링
        const filteredData = res.data.filter(item => item.productStatus === false);

        // 필터링된 데이터를 listData에 할당
        listData.value = filteredData;

        console.log(listData);
    });
}


// 삭제 시 다이얼로그 창
const deleteDialog = {
    isOpen: ref(false),
    openDialog() {
        deleteDialog.isOpen.value = true; // 다이얼로그 열기
    },
    closeDialog() {
        deleteDialog.isOpen.value = false; // 다이얼로그 닫기
        router.push({ name: 'product_management' }); // '상품관리' 경로로 이동
    }
};


// 재등록 시 다이얼로그 창
const returnDialog = {
    isOpen: ref(false),
    openDialog() {
        returnDialog.isOpen.value = true; // 다이얼로그 열기
    },
    closeDialog() {
        returnDialog.isOpen.value = false; // 다이얼로그 닫기
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
    try {
        const response = await axiosInstance.post(url);
        // POST 요청 성공 시 로직
        console.log(response.data);
        deleteDialog.openDialog();
        console.log("모달창띄웟다");

        // 상품 삭제 후 listData에서도 해당 상품 삭제
        const productIndex = listData.value.findIndex(product => product.productId === productId);
        if (productIndex !== -1) {
            listData.value.splice(productIndex, 1);
        }

        // 삭제가 완료되었을 때 다시 상품관리 경로로 이동
        router.push({ name: 'product_management' });
        console.log("페이지 이동 성공!")
    } catch (error) {
        console.error(error);
    };
};

// 재등록 버튼
const returnProduct = async (productId) => {
    const url = `http://localhost:8080/admin/product/${productId}/return`;

    try {
        const response = await axiosInstance.post(url);

        if (response.status === 200) {
            // POST 요청 성공 시 로직
            console.log(response.data);
            // productName.value = values.productName;
            returnDialog.openDialog();
            console.log("모달창띄웟다");

            // 상품 재등록 후 listData에서 해당 상품 삭제
            const productIndex = listData.value.findIndex(product => product.productId === productId);
            if (productIndex !== -1) {
                listData.value.splice(productIndex, 1);
            }
            // 재등록이 완료되었을 때 다시 상품관리 경로로 이동
            router.push({ name: 'product_management' });
            console.log("페이지 이동 성공!")
        }
    } catch (error) {
        console.error(error);
    }
};


// 추가 버튼
const addProduct = () => {
    router.push({ name: 'add_product' });
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
    margin-top: 40px;
    margin-left: 80%;
}
</style>