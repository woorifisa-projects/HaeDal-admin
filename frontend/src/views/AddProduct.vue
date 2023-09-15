<template>
  <form @submit.prevent="submit">
    <h2 style="margin-bottom:30px">상품 추가 페이지</h2>

    <div class="all-box">

      <span class="key-container">
        상품명<br>
        상품 최대금액<br>
        연령대<br>
        이용목적<br>
        태그<br>
        상품 종류<br>
        간략한 상품 설명<br>
        상품 전체정보<br>
        가입기간<br>
        상품 시작금액<br>
        금리<br>
      </span>

      <span class="value-container">



        <v-text-field class="productName" v-model="productName.value.value" :counter="20"
      :error-messages="productName.errorMessage.value" label="productName" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-text-field>

    <v-text-field class="maxProductMoney" v-model="maxProductMoney.value.value" :counter="9"
      :error-messages="maxProductMoney.errorMessage.value" label="maxProductMoney" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-text-field>

    <v-select v-model="userAgeGroup.value.value" :items="ageitems" :error-messages="userAgeGroup.errorMessage.value"
      label="userAgeGroup" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-select>

    <v-select v-model="servicePurpose.value.value" :items="purposeitems"
      :error-messages="servicePurpose.errorMessage.value" label="servicePurpose" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-select>

    <v-select v-model="tag.value.value" :items="tagitems" :error-messages="tag.errorMessage.value" label="tag"
      variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-select>

    <v-select v-model="isDeposit.value.value" :items="isDepositItems" :error-messages="isDeposit.errorMessage.value"
      label="isDeposit" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-select>

    <v-text-field v-model="shortInfo.value.value" :counter="50" :error-messages="shortInfo.errorMessage.value"
      label="shortInfo" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-text-field>

    <v-text-field v-model="longInfo.value.value" :counter="100" :error-messages="longInfo.errorMessage.value"
      label="longInfo" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-text-field>

    <v-text-field v-model="period.value.value" :counter="3" :error-messages="period.errorMessage.value" label="period"
      variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-text-field>

    <v-text-field v-model="requiredStartMoney.value.value" :counter="9"
      :error-messages="requiredStartMoney.errorMessage.value" label="requiredStartMoney" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-text-field>

    <v-text-field v-model="interestRate.value.value" :counter="4" :error-messages="interestRate.errorMessage.value"
      label="interestRate" variant="outlined">
      <template v-slot:prepend>
      </template>
    </v-text-field>

      </span>
    </div>


    <!-- <v-text-field class="productName" v-model="productName.value.value" :counter="20"
      :error-messages="productName.errorMessage.value" label="productName" variant="outlined">
      <template v-slot:prepend>
        <div>상품명</div>
      </template>
    </v-text-field>

    <v-text-field class="maxProductMoney" v-model="maxProductMoney.value.value" :counter="9"
      :error-messages="maxProductMoney.errorMessage.value" label="maxProductMoney" variant="outlined">
      <template v-slot:prepend>
        <div>상품 최대금액</div>
      </template>
    </v-text-field>

    <v-select v-model="userAgeGroup.value.value" :items="ageitems" :error-messages="userAgeGroup.errorMessage.value"
      label="userAgeGroup" variant="outlined">
      <template v-slot:prepend>
        <div>연령대</div>
      </template>
    </v-select>

    <v-select v-model="servicePurpose.value.value" :items="purposeitems"
      :error-messages="servicePurpose.errorMessage.value" label="servicePurpose" variant="outlined">
      <template v-slot:prepend>
        <div>이용목적</div>
      </template>
    </v-select>

    <v-select v-model="tag.value.value" :items="tagitems" :error-messages="tag.errorMessage.value" label="tag"
      variant="outlined">
      <template v-slot:prepend>
        <div>태그</div>
      </template>
    </v-select>

    <v-select v-model="isDeposit.value.value" :items="isDepositItems" :error-messages="isDeposit.errorMessage.value"
      label="isDeposit" variant="outlined">
      <template v-slot:prepend>
        <div>상품 종류</div>
      </template>
    </v-select>


    <v-text-field v-model="shortInfo.value.value" :counter="50" :error-messages="shortInfo.errorMessage.value"
      label="shortInfo" variant="outlined">
      <template v-slot:prepend>
        <div>간략한 상품 설명</div>
      </template>
    </v-text-field>

    <v-text-field v-model="longInfo.value.value" :counter="100" :error-messages="longInfo.errorMessage.value"
      label="longInfo" variant="outlined">
      <template v-slot:prepend>
        <div>상품 전체정보</div>
      </template>
    </v-text-field>

    <v-text-field v-model="period.value.value" :counter="3" :error-messages="period.errorMessage.value" label="period"
      variant="outlined">
      <template v-slot:prepend>
        <div>가입기간</div>
      </template>
    </v-text-field>

    <v-text-field v-model="requiredStartMoney.value.value" :counter="9"
      :error-messages="requiredStartMoney.errorMessage.value" label="requiredStartMoney" variant="outlined">
      <template v-slot:prepend>
        <div>상품 시작금액</div>
      </template>
    </v-text-field>

    <v-text-field v-model="interestRate.value.value" :counter="4" :error-messages="interestRate.errorMessage.value"
      label="interestRate" variant="outlined">
      <template v-slot:prepend>
        <div>금리</div>
      </template>
    </v-text-field> -->

    <v-btn class="me-4" type="submit">
      추가
    </v-btn>

    <v-btn @click="handleReset">
      취소
    </v-btn>

    <div class="text-center">

      <v-dialog v-model="dialog.isOpen.value" width="auto">
        <v-card>
          <v-card-text>
            상품의 정보가 추가되었습니다!
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
import { onMounted } from 'vue';
import { ref } from 'vue'
import axios from 'axios'
import { useField, useForm } from 'vee-validate';
import { useRouter } from 'vue-router';

const router = useRouter();

// add_product 경로로 이동
router.push({ name: 'add_product' });




// 상품 추가 완료시 메세지
const dialog = {
  isOpen: ref(false),
  openDialog() {
    dialog.isOpen.value = true; // 다이얼로그 열기
  },
  closeDialog() {
    dialog.isOpen.value = false; // 다이얼로그 닫기
    router.push({ name: 'product_management' }); // 'edit' 경로로 이동
  }
};

// 상품 등록 유효성 처리
const { handleSubmit, handleReset } = useForm({
  validationSchema: {
    productName(value) {
      if (value?.length >= 2) return true
      return 'Name needs to be at least 2 characters.'
    },
    maxProductMoney(value) {
      if (value?.length > 0) return true
      return 'maxProductMoney is bigger than 0.'
    },
    userAgeGroup(value) {
      if (value) return true
      return 'userAgeGroup an item.'
    },
    servicePurpose(value) {
      if (value) return true
      return 'servicePurpose an item.'
    },
    tag(value) {
      if (value) return true
      return 'tag an item.'
    },
    isDeposit(value) {
      if (value) return true
      return 'isDesposit an item'
    },
    shortInfo(value) {
      if (value?.length >= 10) return true
      return 'shortInfo needs to be at 10 characters.'
    },
    longInfo(value) {
      if (value?.length >= 10) return true
      return 'longInfo needs to be at 10 characters.'
    },
    period(value) {
      if (value >= 1) return true
      return 'period is bigger than 1.'
    },
    requiredStartMoney(value) {
      if (value > 0) return true
      return 'requiredStartMoney is bigger than 0.'
    },
    interestRate(value) {
      if (value > 0) return true
      return 'interestRate is bigger than 0.'
    },

  },
})


const maxProductMoney = useField('maxProductMoney') // 상품 최대금액
const servicePurpose = useField('servicePurpose')// 서비스 이용 목적
const userAgeGroup = useField('userAgeGroup') // 연령대
const tag = useField('tag') // 어떤 태그를 가지고 있는지
const productName = useField('productName') // 상품 이름
const shortInfo = useField('shortInfo') // 상품 한 줄 설명
const longInfo = useField('longInfo') // 상품 전체 설명
const period = useField('period') // 저축 기간
const requiredStartMoney = useField('requiredStartMoney') // 시작 금액
const interestRate = useField('interestRate') // 금리
const isDeposit = useField('isDeposit') // 예금, 적금 타입 확인용 컬럼(0=예금, 1=적금)




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
const tagitems = ref([
  '금융',
  '테마',
])


// boolean 값 
const isDepositItems = ref([
  '적금',
  '예금',
])


const ageEnumMapping = {
  '10대': 'ONE',
  '20대': 'TWO',
  '30대': 'THREE',
  '40대': 'FOUR',
  '50대': 'FIVE',
  '전연령대': 'ONETWOTHREEFOURFIVE'
};

const purposeEnumMapping = {
  '자동차 구매': 'FORCAR',
  '주택 구매': 'FORHOUSE',
  '목돈 마련': 'MOKDON',
  '기타': 'OTHERS',
}

const tagEnumMapping = {
  '금융': 'FINANCE',
  '테마': 'THEMA',
}

const isDepositBooleanMapping = {
  '적금': '0',
  '예금': '1',
}

const mapToAgeEnum = (value) => ageEnumMapping[value];
const mapToPurposeEnum = (value) => purposeEnumMapping[value];
const mapToTag = (value) => tagEnumMapping[value];
const mapToIsDeposit = (value) => isDepositBooleanMapping[value];


const submit = handleSubmit(values => {
  
  const ageEnum = mapToAgeEnum(values.userAgeGroup);
  const purposeEnum = mapToPurposeEnum(values.servicePurpose);
  const tagEnum = mapToTag(values.tag);
  const isDepositBoolean = mapToIsDeposit(values.isDeposit);


  const dataToSend = { ...values, userAgeGroup: ageEnum, servicePurpose: purposeEnum, tag: tagEnum, isDeposit: isDepositBoolean };

  

  axios.post('http://13.124.156.71:8080/admin/product/add', dataToSend)
    .then(response => {
      // POST 요청 성공 시 로직
      
      productName.value = values.productName;
      dialog.openDialog();
      
      // 수정이 완료되었을 때 'save' 경로로 이동
      router.push({ name: 'save_add_product' });
    })
    // POST 요청 실패 시 로직
    .catch(error => {
      console.error(error);
    });
})

onMounted(() => {





});



</script>




<style lang="scss" scoped>
form {
  width: 40%;
  margin: auto;
  margin-top: 30px;
  margin-bottom: 100px;
  text-align: center;
}

.title {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.center-text-field {
  display: flex;
  flex-direction: column;
}

form .v-btn {
  background-color: rgb(4, 171, 255);
  box-shadow: 4px 1px 10px rgba(161, 216, 255, 0.61);
  border-radius: 15px !important;
  font-weight: bold;
  color: white;
}

.all-box {
  display: flex;
  margin-right: 0%;
  

  /* Flexbox를 사용하여 가로 배치
   설정 */
  /* 두 열을 가로로 최대한 넓게 배치 */
}

.key-container {
  flex: 0.9;
  
  text-align: left;
  font-weight: 500;
  font-size: 16px;
  line-height: 77px;
  margin-top: -2.5px;


}


.value-container {
  flex: 3;
  flex-wrap: nowrap;
}


</style>