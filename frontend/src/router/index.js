// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
      },

      {
        path: "header",
        name: "Header",
        component: () => import(/* webpackChunkName: "home" */ '@/views/Header.vue'),
      },



      // 상품관련
      {
        path: '/admin/product',
        name: 'product_management',
        component: () => import('@/views/ProductManagement.vue'),
        props: true
      },


      {
        path: '/admin/product/:productId/edit', // 동적 세그먼트 :product_id 사용,
        name: 'edit_product',
        component: () => import('@/views/EditProduct.vue'),
        props: true
      },

      {
        path: '/admin/product/:productId/edit/save', // 동적 세그먼트 :product_id 사용,
        name: 'save_edited_product',
        component: () => import('@/views/EditProduct.vue'),
        props: true
      },

      {
        path: '/admin/product/add',
        name: 'add_product',
        component: () => import('@/views/AddProduct.vue'),
        props: true
      },

      {
        path: '/admin/product/delete',
        name: 'delete_product',
        component: () => import('@/views/ProductManagement.vue'),
        props: true
      },

      {
        path: '/admin/product/:productId/return',
        name: 'return_product',
        component: () => import('@/views/ProductManagement.vue'),
        props: true
      },

      {
        path: '/admin/product/add/save',
        name: 'save_add_product',
        component: () => import('@/views/AddProduct.vue'),
        props: true
      },





      // user 관련
      {
        path: '/admin/user',
        name: 'user_management',
        component: () => import('@/views/UserManagement.vue'),
        props: true
      },

      {
        path: '/admin/user/:userId/edit',
        name: 'edit_user',
        component: () => import('@/views/EditUser.vue'),
        props: true
      },

      {
        path: '/admin/user/:userId/delete',
        name: 'delete_user',
        component: () => import('@/views/UserManagement.vue'),
        props: true
      },

      //log 페이지
      {
        path: '/admin/log',
        name: 'log',
        component: () => import('@/views/LogManagement.vue'),
      }
    ],
  }, {
    path: '/admin/login',
    name: 'admin_login_view',
    component: () => import('@/views/AdminLoginView.vue'),
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
