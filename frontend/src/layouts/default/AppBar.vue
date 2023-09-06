<template>
  <form @submit.prevent="submit">
    <v-app-bar class="bar" flat :color="scrolling ? 'rgba(255,255,255,0.4)' : 'rgba(119, 198, 255, 0.152)'">
      <v-container class="mx-auto d-flex align-center justify-center ">
        <img src='@/assets/img/HaeDalLogo.png' class="logo">

        <v-btn v-for="link in links" :key="link" :text="link" variant="text" :to="goLinkPath(link)"
          class="nav-link"></v-btn>

        <v-spacer></v-spacer>
      </v-container>
    </v-app-bar>

  </form>
</template>

<script setup>
import { onMounted } from 'vue';
import axios from 'axios';
import { useAuthStore } from '@/store/app';
import { ref } from 'vue';
import router from '@/router';

const username = ref(0);
const authStore = useAuthStore();

const links = [
  '상품관리',
  '고객관리',
  '로그관리',
];


const goLinkPath = (link) => {
  if (link === '상품관리') {
    return '/admin/product';
  } else if (link === '고객관리') {
    return '/admin/user';
  } else if (link === '로그관리') {
    return '/admin/log';
  }
};

const scrolling = ref(false);

window.addEventListener('scroll', () => {
  scrolling.value = window.scrollY > 0;
});

</script>


<style>
.logo {
  width: 120px;
  margin: 2rem;
}

.bar {
  backdrop-filter: blur(5px);
}

.nav-link {
  font-weight: bold;
  color: rgb(0, 90, 150);
}

.v-btn {
  border-radius: 10px !important;
}
</style>
