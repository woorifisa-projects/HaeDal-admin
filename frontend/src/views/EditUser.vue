<template>
  <form @submit.prevent="submit">
    <h2 class="title" style="margin-bottom:30px">
      유저 정보
    </h2>
    <v-text-field class="id" v-model="id.value.value" :counter="20" :error-messages="id.errorMessage.value" label="id">
      <template v-slot:prepend>
        <div>로그인 id</div>
      </template>
    </v-text-field>

    <v-text-field class="password" v-model="password.value.value" :counter="20"
      :error-messages="password.errorMessage.value" label="password">
      <template v-slot:prepend>
        <div>로그인 비밀번호</div>
      </template>
    </v-text-field>


    <v-text-field class="name" v-model="name.value.value" :counter="20" :error-messages="name.errorMessage.value"
      label="name">
      <template v-slot:prepend>
        <div>고객이름</div>
      </template>
    </v-text-field>


    <v-text-field v-model="phoneNumber.value.value" :counter="12" :error-messages="phoneNumber.errorMessage.value"
      label="phoneNumber">
      <template v-slot:prepend>
        <div>고객 전화번호</div>
      </template>
    </v-text-field>


    <v-select v-model="userAgeGroup.value.value" :items="ageitems" :error-messages="userAgeGroup.errorMessage.value"
      label="userAgeGroup">
      <template v-slot:prepend>
        <div>연령대</div>
      </template>
    </v-select>


    <v-select v-model="servicePurpose.value.value" :items="purposeitems"
      :error-messages="servicePurpose.errorMessage.value" label="servicePurpose">
      <template v-slot:prepend>
        <div>이용목적</div>
      </template>
    </v-select>




    <v-text-field v-model="accountNumber.value.value" :counter="100" :error-messages="accountNumber.errorMessage.value"
      label="accountNumber">
      <template v-slot:prepend>
        <div>계좌번호</div>
      </template>
    </v-text-field>

    <v-text-field v-model="authNumber.value.value" :counter="20" :error-messages="authNumber.errorMessage.value"
      label="authNumber">
      <template v-slot:prepend>
        <div>인증번호</div>
      </template>
    </v-text-field>

    <v-text-field v-model="asset.value.value" :counter="10" :error-messages="asset.errorMessage.value" label="asset">
      <template v-slot:prepend>
        <div>고객 자산</div>
      </template>
    </v-text-field>

    <v-btn class="me-4" type="submit">
      수정
    </v-btn>

    <v-btn @click="handleReset">
      취소
    </v-btn>


    <div class="text-center">

      <v-dialog v-model="dialog.isOpen.value" width="auto">
        <v-card>
          <v-card-text>
            고객의 정보가 수정되었습니다!
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" block @click="dialog.closeDialog">확인</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </form>
</template>
  


  
<script setup>
import { defineProps, onMounted } from 'vue';
import { ref } from 'vue'
import axios from 'axios'
import { useField, useForm } from 'vee-validate';
import { useRouter } from 'vue-router';

const router = useRouter();
// const product_id = router.params.product_id; // route.params로 product_id 가져오기


const props = defineProps({
  userId: String
});


const handleReset = () => {
  // 이전 페이지로 돌아가기
  router.go(-1);
}

const dialog = {
  isOpen: ref(false),
  openDialog() {
    dialog.isOpen.value = true; // 다이얼로그 열기
  },
  closeDialog() {
    dialog.isOpen.value = false; // 다이얼로그 닫기
    router.push({ name: 'user_management' }); // '상품관리' 경로로 이동
  }
};

const { handleSubmit } = useForm({
  validationSchema: {
    id(value) {
      if (value?.length >= 5) return true
      return 'id needs to be at least 5 characters.'
    },
    password(value) {
      if (value?.length > 1) return true
      return 'password is bigger than 1.'
    },
    name(value) {
      if (value?.length > 2) return true
      return 'name is bigger than 5.'
    },
    phoneNumber(value) {
      if (value?.length < 12) return true
      return 'phoneNumber is bigger than 10.'
    },
    userAgeGroup(value) {
      if (value) return true
      return 'userAgeGroup an item.'
    },
    servicePurpose(value) {
      if (value) return true
      return 'servicePurpose an item.'
    },
    accountNumber(value) {
      if (value?.length > 8) return true
      return 'accountNumber needs to be at 10 digits.'
    },
    authNumber(value) {
      if (value > 0) return true
      return 'authNumber is bigger than 0.'
    },
    asset(value) {
      if (value > 0) return true
      return 'asset is bigger than 0.'
    },

  },
})


const id = useField('id') // 사용자 로그인 id
const password = useField('password')// 사용자 로그인 password
const name = useField('name') // 사용자 이름
const servicePurpose = useField('servicePurpose')// 서비스 이용 목적
const userAgeGroup = useField('userAgeGroup') // 연령대
const phoneNumber = useField('phoneNumber') // 사용자 전화번호
const accountNumber = useField('accountNumber') // 계좌번호
const authNumber = useField('authNumber') // 인증 번호
const asset = useField('asset') // 사용자 자산





const ageitems = ref([
  '10대',
  '20대',
  '30대',
  '40대',
  '50대',
  '전연령대',
])
const purposeitems = ref([
  '자동차 구매',
  '주택 구매',
  '목돈 마련',
  '기타',
])





const ageEnumMapping = {
  '10대': 'ONE',
  '20대': 'TWO',
  '30대': 'THREE',
  '40대': 'FOUR',
  '50대': 'FIVE',
  '전연령대': 'ONETWOTHREEFOURFIVE'
}


console.log('여기서는 ageEnumMapping이 어떤 값들을 가질까? :' + ageEnumMapping)


const purposeEnumMapping = {
  '자동차 구매': 'FORCAR',
  '주택 구매': 'FORHOUSE',
  '목돈 마련': 'MOKDON',
  '기타': 'OTHERS',
}



const mapToAgeEnum = (value) => ageEnumMapping[value];
const mapToPurposeEnum = (value) => purposeEnumMapping[value];


const mapAgeEnumToValue = (value) => {
  for (const key in ageEnumMapping) {
    if (ageEnumMapping[key] === value) {
      return key;
    }
  }
  return value; // 만약 일치하는 값이 없으면 그대로 반환
};

const mapPurposeEnumToValue = (value) => {
  for (const key in purposeEnumMapping) {
    if (purposeEnumMapping[key] === value) {
      return key;
    }
  }
  return value; // 만약 일치하는 값이 없으면 그대로 반환
};





console.log('여기서는 mapToAgeEnum이 어떤 값들을 가질까? :' + mapToAgeEnum('10대'))


// console.log('maptoAgeEnum 타입은? :' + typeof(JSON.stringify(mapToAgeEnum)));

const submit = handleSubmit(values => {
  console.log("함수 동작하고있니");


  const ageEnum = mapToAgeEnum(mapAgeEnumToValue(values.userAgeGroup));
  const purposeEnum = mapToPurposeEnum(mapPurposeEnumToValue(values.servicePurpose));

  const userId = props.userId;


  console.log('관리자가 수정한 연령대 값 ' + mapAgeEnumToValue(values.userAgeGroup))
  console.log('관리자가 수정한 값의 ageEnum 값 ' + mapToAgeEnum(mapAgeEnumToValue(values.userAgeGroup)))

  console.log('const ageEnum = mapToAgeEnum(values.userAgeGroup) 값은? : ' + JSON.stringify(ageEnum));
  console.log('const ageEnum = mapToAgeEnum(values.userAgeGroup) 타입은? : ' + typeof (ageEnum));

  const dataToSend = { ...values, userAgeGroup: ageEnum, servicePurpose: purposeEnum };

  console.log('악 모르겠어' + JSON.stringify(dataToSend));

  console.log('ageEnum 타입은? ' + typeof (ageEnum));
  console.log('purposeEnum 타입은? ' + typeof (purposeEnum));



  axios.post(`http://localhost:8080/admin/user/${userId}/edit`, dataToSend, {
    headers: {
      'Content-Type': 'application/json',
    },

  })
    .then(response => {
      // POST 요청 성공 시 로직
      console.log(response.data);
      id.value = values.id;
      dialog.openDialog();
      console.log("모달창띄웟다");
      // 수정이 완료되었을 때 'save' 경로로 이동
      // location.reload(); // 현재 페이지 리로드
      router.push({ name: 'save_edit_user', params: { userId } });
      console.log("페이지 이동 성공!")
    })
    // POST 요청 실패 시 로직
    .catch(error => {
      console.error(error);
    });
})




onMounted(() => {
  const userId = props.userId; // props로 받아온 user_id 사용

  axios.get(`http://localhost:8080/admin/user/${userId}/edit`)
    .then(response => {
      console.log(response.data);
      id.value.value = response.data.id
      password.value.value = response.data.password
      name.value.value = response.data.name
      phoneNumber.value.value = response.data.phoneNumber
      servicePurpose.value.value = mapPurposeEnumToValue(response.data.servicePurpose)
      userAgeGroup.value.value = mapAgeEnumToValue(response.data.userAgeGroup)
      accountNumber.value.value = response.data.accountNumber
      authNumber.value.value = response.data.authNumber
      asset.value.value = response.data.asset

      console.log("잘 나타나고 있니")
    })
    .catch(error => {
      console.error(error);
    });
});

</script>
  




<style lang="scss" scoped>
.title {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.center-text-field {
  display: flex;
  flex-direction: column;
}

form {
  width: 40%;
  margin: auto;
  margin-top: 30px;
  margin-bottom: 100px;
  text-align: center;
}

form .v-btn {
  background-color: rgb(4, 171, 255);
  box-shadow: 4px 1px 10px rgba(161, 216, 255, 0.61);
  border-radius: 15px !important;
  font-weight: bold;
  color: white;
}
</style>