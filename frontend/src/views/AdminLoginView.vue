<template>
  <form @submit.prevent="submit">
    <div class="login-box">
      <div class="login-header">
      
      <v-img alt="main-HaeDal-logo" class="mx-auto my-6" max-width="228" src="@/assets/img/HaeDalLogo.png"></v-img>
      <h2 style="text-align: center;margin-bottom: 30px;margin-top:-10px">관리자 로그인</h2>

      </div>

      <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
    
        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
          Password
        </div>

        <v-text-field v-model="password.value.value" :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
          :type="visible ? 'text' : 'password'" density="compact" placeholder="Enter your password"
          prepend-inner-icon="mdi-lock-outline" variant="outlined"
          @click:append-inner="visible = !visible"></v-text-field>


        <v-btn :loading="loading" block class="mb-8" color="blue" size="large" variant="tonal" @click="onSubmit">
          Log In
        </v-btn>
      </v-card>
    </div>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios'
import { useField, useForm } from 'vee-validate'

const { handleSubmit } = useForm({
  validationSchema: {
    password(value) {
      if (value?.length >= 1) return true
      return 'password needs to be at least 1 digits.'
    },
  },
});

const password = useField('password')
const router = useRouter(); // 라우터 객체 가져오기
const loading = ref(false);


const onSubmit = handleSubmit(async (values) => {
  try {
    const url = "http://13.124.156.71:8080/admin/login";

    

    const response = await axios.post(url, null, {
      params: {
        password: values.password, // 문자열로 보냄
      },
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (response.status === 200 && response.data === "관리자 확인 되었습니다.") {
      // 로그인 성공 시 로직
      
      router.push({ name: 'product_management' });
    } else {
      // 로그인 실패 시 로직
      console.error("로그인 실패");
    }
  } catch (error) {
    // POST 요청 실패 시 로직
    console.error(error);
  }
});



</script>
<style lang="scss" scoped>
.login-box{
  height: 100vh;
}

.mx-auto pa-12 pb-8 {
  justify-content: center;
    margin: auto;
    margin-top: 300px;
    width: 70%;
    min-width: 900px;

}

.v-card--variant-elevated, .v-card--variant-flat{
  margin-top:10rem;
}


.v-img__img--contain {
  justify-content: center;
    margin: auto;
    margin-bottom: 300px;
    width: 70%;
    min-width: 900px;
}

.login-header {
  margin-top: 100px; 
  text-align: center;
}


.v-card--variant-elevated {
  margin-top: 1rem;
}

</style>

