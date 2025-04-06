import { createApp, watch } from 'vue'
import App from './App.vue'

import './assets/style.css'

var theme = localStorage.getItem('theme')
const body = document.getElementsByTagName('body')[0]
if (theme == null || (theme != 'dark' && theme != 'light')) {
    if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
        theme = 'dark'
    } else {
        theme = 'light'
    }
}
body.className = theme
localStorage.setItem('theme', theme)

createApp(App).mount('#app')
