// Composables
import { createRouter, createWebHistory } from 'vue-router'

// const routes = [
//   {
//     path: '/',
//     component: () => import('@/layouts/default/Default.vue'),
//     children: [
//       {
//         path: '',
//         name: 'Home',
//         // route level code-splitting
//         // this generates a separate chunk (about.[hash].js) for this route
//         // which is lazy-loaded when the route is visited.
//         component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
//       },

// const routes = [
//   {
//     path: '/',
//     component: () => import('@/layouts/default/Default.vue'),
//     redirect: '/admin/login', // '/' 경로로 접속 시 '/admin/login' 경로로 리다이렉트
//     path: '/admin/login',
//     name: 'admin_login_view',
//     component: () => import('@/views/AdminLoginView.vue'),
//     },
  
const routes = [
    {
    path: '/',
    name: 'admin_login_view', // 이름 지정 (선택사항)
    component: () => import('@/views/AdminLoginView.vue'), // 컴포넌트 경로 지정
    },
  {
    path: '/admin',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
   
      {
        path: 'product',
        name: 'product_management',
        component: () => import('@/views/ProductManagement.vue'),
        props: true
      },
      {
        path: 'product/:productId/edit', // 동적 세그먼트 :product_id 사용,
        name: 'edit_product',
        component: () => import('@/views/EditProduct.vue'),
        props: true
      },

      {
        path: 'product/:productId/edit/save', // 동적 세그먼트 :product_id 사용,
        name: 'save_edited_product',
        component: () => import('@/views/EditProduct.vue'),
        props: true
      },

      {
        path: 'product/add',
        name: 'add_product',
        component: () => import('@/views/AddProduct.vue'),
        props: true
      },
      {
        path: 'product/delete',
        name: 'delete_product',
        component: () => import('@/views/ProductManagement.vue'),
        props: true
      },
      {
        path: 'product/:productId/return',
        name: 'return_product',
        component: () => import('@/views/ProductManagement.vue'),
        props: true
      },
      {
        path: 'product/add/save',
        name: 'save_add_product',
        component: () => import('@/views/AddProduct.vue'),
        props: true
      },





      // user 관련
      {
        path: 'user',
        name: 'user_management',
        component: () => import('@/views/UserManagement.vue'),
        props: true
      },

      {
        path: 'user/:userId/edit',
        name: 'edit_user',
        component: () => import('@/views/EditUser.vue'),
        props: true
      },

      {
        path: 'user/:userId/delete',
        name: 'delete_user',
        component: () => import('@/views/UserManagement.vue'),
        props: true
      },



      //log 페이지
      {
        path: 'log',
        name: 'log',
        component: () => import('@/views/LogManagement.vue'),
      },
    ],   
  },  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
