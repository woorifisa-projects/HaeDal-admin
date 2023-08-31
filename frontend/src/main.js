/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'
import pinia from './store';
// Plugins
import { registerPlugins } from '@/plugins'
// import { Field, Form, ErrorMessage } from 'vee-validate'; // 필요한 모듈들을 import

const app = createApp(App)

// app.component('Field', Field); // Field 컴포넌트 등록
// app.component('Form', Form); // Form 컴포넌트 등록
// app.component('ErrorMessage', ErrorMessage); // ErrorMessage 컴포넌트 등록


registerPlugins(app)

app.use(pinia)
app.mount('#app')




