<template>
  <form @submit.prevent="submit">
    <h2 class="title" style="margin-bottom:30px">
      상품정보 수정
    </h2>
    <v-text-field class="productName" v-model="productName.value.value" :counter="20"
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

    <v-select v-model="deposit.value.value" :items="depositItems" :error-messages="deposit.errorMessage.value"
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
            상품의 정보가 수정되었습니다!
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

const handleReset = () => {
  // 이전 페이지로 돌아가기
  router.go(-1);
}

const props = defineProps({
  productId: String
});


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

const { handleSubmit } = useForm({
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
    deposit(value) {
      if (value) return true
      return 'deposit an item'
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
const deposit = useField('deposit') // 예금, 적금 타입 확인용 컬럼(0=예금, 1=적금)
const productStatus = useField('productStatus')




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
const depositItems = ref([
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
}





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

const depositBooleanMapping = {
  '적금': false,
  '예금': true,
}



const mapToAgeEnum = (value) => ageEnumMapping[value];
const mapToPurposeEnum = (value) => purposeEnumMapping[value];
const mapToTag = (value) => tagEnumMapping[value];
const mapToDeposit = (value) => depositBooleanMapping[value];


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


const maptagEnumToValue = (value) => {
  for (const key in tagEnumMapping) {
    if (tagEnumMapping[key] === value) {
      return key;
    }
  }
  return value; // 만약 일치하는 값이 없으면 그대로 반환
};



const mapDepositBooleanToValue = (value) => {
  for (const key in depositBooleanMapping) {
    if (depositBooleanMapping[key] === value) {
      return key;
    }
  }
  return value; // 만약 일치하는 값이 없으면 그대로 반환
};




const submit = handleSubmit(values => {



  const ageEnum = mapToAgeEnum(mapAgeEnumToValue(values.userAgeGroup));
  const purposeEnum = mapToPurposeEnum(mapPurposeEnumToValue(values.servicePurpose));
  const tagEnum = mapToTag(maptagEnumToValue(values.tag));
  const depositBoolean = mapToDeposit(mapDepositBooleanToValue(values.deposit));
  const productId = props.productId;



  const dataToSend = { ...values, deposit: depositBoolean.toString(), userAgeGroup: ageEnum, servicePurpose: purposeEnum, tag: tagEnum };


  axios.post(`http://13.124.156.71:8080/admin/product/${productId}/edit/save`, dataToSend, {
    headers: {
      'Content-Type': 'application/json',
    },

  })
    .then(response => {
      // POST 요청 성공 시 로직

      productName.value = values.productName;
      dialog.openDialog();

      // 수정이 완료되었을 때 'save' 경로로 이동
      // location.reload(); // 현재 페이지 리로드
      router.push({ name: 'save_edited_product', params: { productId } });

    })
    // POST 요청 실패 시 로직
    .catch(error => {
      console.error(error);
    });
})


onMounted(() => {
  const productId = props.productId; // props로 받아온 product_id 사용

  axios.get(`http://13.124.156.71:8080/admin/product/${productId}/edit`)
    .then(response => {

      productName.value.value = response.data.name
      maxProductMoney.value.value = response.data.maxProductMoney
      servicePurpose.value.value = mapPurposeEnumToValue(response.data.servicePurpose)
      userAgeGroup.value.value = mapAgeEnumToValue(response.data.userAgeGroup)
      tag.value.value = maptagEnumToValue(response.data.tag)
      productName.value.value = response.data.productName
      shortInfo.value.value = response.data.shortInfo
      longInfo.value.value = response.data.longInfo
      period.value.value = response.data.period
      requiredStartMoney.value.value = response.data.requiredStartMoney
      interestRate.value.value = response.data.interestRate
      deposit.value.value = mapDepositBooleanToValue(response.data.deposit)

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