<template>
    <div class="container">
        <div>
            <v-layout class="overflow-visible" style="height: 56px; box-shadow: none; margin-bottom:30px;">
                <v-bottom-navigation v-model="value" color="blue" grow>
                    <v-btn @click="inProgressService">
                        활성화 고객
                    </v-btn>

                    <v-btn @click="doneService">
                        비활성화 고객
                    </v-btn>
                </v-bottom-navigation>
            </v-layout>

        </div>

        <div id="users" v-bind:class="user.id" v-for="(user, index) in listData" :key="index">
            <v-card class="mx-auto" max-width="60%">
                <v-card-item class="users">
                    <div>
                        <div class="text-h6 mb-4" style="font-weight: bolder;">
                            고객 아이디 : {{ user.id }}
                        </div>
                        <div class="text-overline mb-1">
                            <span>
                                <b>고객 번호</b> {{ user.userId }}
                                <b>고객 이름</b> {{ user.name }}
                                <b>고객 전화번호</b> {{ user.phoneNumber }}
                            </span>
                        </div>
                    </div>
                </v-card-item>
                <div class="d-flex justify-end align-center">
                    <v-card-actions>
                        <!-- 이부분 subscribeUser X -> 수정페이지로 가도록 고치기  -->
                        <v-btn @click=editUser(user.userId)>
                            수정
                        </v-btn>
                        <!-- 이부분 deleteUser X -> 삭제페이지로 가도록 고치기 -->
                        <v-btn @click=deleteUser(user.userId) v-if="user.userStatus === true">
                            비활성화
                        </v-btn>
                        <v-btn @click=returnUser(user.userId) v-if="user.userStatus === false">
                            활성화
                        </v-btn>
                    </v-card-actions>
                </div>
            </v-card>
        </div>

        <div class="text-center">
            <v-dialog v-model="deleteDialog.isOpen.value" width="auto">
                <v-card>
                    <v-card-text>
                        해당 고객이 비활성화 되었습니다!
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
                        해당 고객이 활성화 되었습니다!
                    </v-card-text>
                    <v-card-actions>
                        <v-btn color="primary" block @click="returnDialog.closeDialog">확인</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </div>
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

})



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
const deleteDialog = {
    isOpen: ref(false),
    openDialog() {
        deleteDialog.isOpen.value = true; // 다이얼로그 열기
    },
    closeDialog() {
        deleteDialog.isOpen.value = false; // 다이얼로그 닫기
        router.push({ name: 'user_management' }); // '고객관리' 경로로 이동
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
        router.push({ name: 'user_management' }); // '고객관리' 경로로 이동
    }
};


// 수정하기 버튼
const editUser = (userId) => {
    router.push({ name: 'edit_user', params: { userId: userId } });
    console.log('수정됨');
}



// 유저 삭제 버튼
const deleteUser = async (userId) => {
    const url = `http://localhost:8080/admin/user/${userId}/delete`;

    try {
        const response = await axiosInstance.post(url);

        if (response.status === 200) {
            // POST 요청 성공 시 로직
            console.log(response.data);
            deleteDialog.openDialog();
            console.log("모달창띄웟다");

            // 사용자 삭제 후 listData에서도 해당 사용자 삭제
            const userIndex = listData.value.findIndex(user => user.userId === userId);
            if (userIndex !== -1) {
                listData.value.splice(userIndex, 1);
            }

            // 수정이 완료되었을 때 다시 상품관리 경로로 이동
            router.push({ name: 'user_management' });
            console.log("페이지 이동 성공!")
        }


    } catch (error) {
        console.error(error);
    }

};


// 재등록 버튼
const returnUser = async (userId) => {
    const url = `http://localhost:8080/admin/user/${userId}/return`;

    try {
        const response = await axiosInstance.post(url);

        if (response.status === 200) {
            // POST 요청 성공 시 로직
            console.log(response.data);
            returnDialog.openDialog();
            console.log("모달창띄웟다");

            // 사용자 복구 후 listData에서도 해당 사용자 삭제
            const userIndex = listData.value.findIndex(user => user.userId === userId);
            if (userIndex !== -1) {
                listData.value.splice(userIndex, 1);
            }

            // 수정이 완료되었을 때 다시 상품관리 경로로 이동
            router.push({ name: 'user_management' });
            console.log("페이지 이동 성공!")
        }
    } catch (error) {
        console.error(error);
    }
};



// 현재 서비스 중인 상품들만 나타내는 목록(userStatus = true(1))
const inProgressService = () => {
    listData.value = [];
    axiosInstance.get('/user').then((res) => {
        // 서버로부터 받아온 데이터 중 userStatus가 false인 것만 필터링
        const filteredData = res.data.filter(item => item.userStatus === true);

        // 필터링된 데이터를 listData에 할당
        listData.value = filteredData;

        console.log(listData);
    });
}



// 만료된 서비스 상품들만 나타내는 목록(userStatus = false(0)) -> 상품 '삭제'하여 HaeDal 클라이언트에 보이지 않는 목록
const doneService = () => {
    listData.value = [];
    axiosInstance.get('/user').then((res) => {
        // 서버로부터 받아온 데이터 중 userStatus가 false인 것만 필터링
        const filteredData = res.data.filter(item => item.userStatus === false);

        // 필터링된 데이터를 listData에 할당
        listData.value = filteredData;

        console.log(listData);
    });
}




</script>


<style lang="scss" scoped>
.mx-auto {
    text-align: center;
    justify-content: center;
    border-top: 1px solid rgba(255, 255, 255, 0.5);
    border-left: 1px solid rgba(255, 255, 255, 0.5);
    border-radius: 15px;
    height: 220px;
    padding: 30px;
    margin: 20px;
    backdrop-filter: blur(5px);
    background: rgba(255, 255, 255, 0.1);
    overflow: hidden;
    box-shadow: inset 5px 5px 20px rgb(229, 244, 255);
    min-width: 600px;
    border: 1px solid rgb(4, 171, 255);
}

.container {
    margin-bottom: 90px;
}

.mx-auto button {
    margin: auto;
    border-top: 1px solid rgba(255, 255, 255, 0.5);
    border-left: 1px solid rgba(255, 255, 255, 0.5);
    background-color: rgb(229, 244, 255);
    box-shadow: inset 1px 1px 10px rgba(161, 216, 255, 0.236);
    border-radius: 20px;
    width: 100px;
}

.users {
    text-align: center;
    display: flex;
    justify-content: center;
}

.users b {
    background-color: rgb(4, 171, 255);
    padding: 4px;
    border-radius: 10px;
    color: white;
}

.v-card-actions {
    margin: auto;
    margin-top: 5px;
}

.v-bottom-navigation {
    background: none;
    color: rgb(0, 149, 255);
    box-shadow: none;
}

.v-bottom-navigation button {
    background: rgba(255, 255, 255, 0.264);
    border-bottom: 2px solid rgb(0, 149, 255);
    margin-left: 16px;
    border-radius: 0px !important;
    height: 2px;
}
</style>