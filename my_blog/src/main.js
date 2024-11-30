import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 引入 Element Plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App)

// 使用 Element Plus
app.use(ElementPlus)
app.use(store)
app.use(router)
app.mount('#app')