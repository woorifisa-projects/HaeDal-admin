<template>


    <!-- 로그인 후, or '고객관리 탭'클릭 시 기본으로 보여줄 페이지 -->
    <div id="users" v-bind:class="user.id" v-for="(user, index) in listData" :key="index">
        <v-card class="mx-auto" max-width="70%">
            <v-card-item class="users">
                <div>
                    <div class="text-h6 mb-1">
                        {{ user.id }}
                    </div>
                    <div class="text-overline mb-1">
                        <span>
                            고객 이름 : {{ user.name }}
                            고객 전화번호 : {{ user.phoneNumber }}
                        </span>
                    </div> 
                </div>
            </v-card-item>
            <div class="d-flex justify-end align-center">
                <v-card-actions>
                    <!-- 이부분 subscribeUser X -> 수정페이지로 가도록 고치기  -->
                    <v-btn variant="outlined" @click=editUser(user.userId)> 
                        수정
                    </v-btn>
                    <!-- 이부분 deleteUser X -> 삭제페이지로 가도록 고치기 -->
                    <v-btn variant="outlined" @click=deleteUser(user.userId)>  
                        삭제
                    </v-btn>
                </v-card-actions>
            </div>
        </v-card>
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
    axiosInstance.get('user').then((res) => {
        let tempArr = [...res.data]
        tempArr.forEach((user) => {
            console.log(user)
            listData.value.push(user)
        })
        console.log(listData);
    })
})



// 삭제 시 다이얼로그 창
const dialog = {
    isOpen: ref(false),
    openDialog() {
      dialog.isOpen.value = true; // 다이얼로그 열기
    },
    closeDialog() {
      dialog.isOpen.value = false; // 다이얼로그 닫기
      router.push({ name: 'user_management' }); // '고객관리' 경로로 이동
    }
  };

// 수정하기 버튼
// 수정하기 버튼
const editUser = (userId) => {
    router.push({ name: 'edit_user', params: { userId: userId } });
    console.log('수정됨');
}



// 유저 삭제 버튼
const deleteUser = async (userId) => {
    const url = `http://localhost:8080/admin/user/${userId}/delete`;

    try {
        const response = await axiosInstance.delete(url);

        if (response.status === 200) {
            // POST 요청 성공 시 로직
        console.log(response.data);
        dialog.openDialog();
        console.log("모달창띄웟다");
        // 수정이 완료되었을 때 다시 상품관리 경로로 이동
        router.push({ name: 'user_management'});
        console.log("페이지 이동 성공!")

        }


    } catch (error) {
        console.error(error);
    }

};
    // axiosInstance.get(url).then((res) => {
    //     listData.value = res.data.filter(item => item.productStatus === false);
    // });

    






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