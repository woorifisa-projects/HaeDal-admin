<template>
    <div class="container">
        <v-data-table :headers="headers" :items="listData" v-model:items-per-page="itemsPerPage" class="elevation-1 center">
        </v-data-table>
    </div>
</template>
  
<script setup>
import axios from 'axios'
import 'vuetify/styles/main.css'
import { watchEffect, ref } from 'vue'
import router from '../router'
import {
    VDataTable,
    VDataTableServer,
    VDataTableVirtual,
} from "vuetify/labs/VDataTable";

// 서버에서 받아오는 정보
const listData = ref([]);

// Axios 인스턴스 생성
const axiosInstance = axios.create({
    baseURL: 'http://13.124.156.71/admin',
})

watchEffect(() => {
    axiosInstance.get('/log').then((res) => {
        listData.value = res.data;
        console.log(listData.value)
    })
})

const itemsPerPage = ref(5);

const headers = [
    { title: '로그 번호', key: 'logId' },
    { title: '고객 번호', key: 'userId' },
    { title: '로그 타입', key: 'logType' },
    { title: '로그 내용', key: 'logDesc' },
    { title: '로그 생성 시간', key: 'logTime' },
];
</script>
  
<style lang="scss" scoped>
.container {
    justify-content: center;
    margin: auto;
    margin-top: 50px;
    margin-bottom: 50px;
    width: 70%;
    min-width: 900px;
}
</style>
