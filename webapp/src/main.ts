import { createApp } from 'vue'
import App from './App.vue'

import './assets/style.css'

const body = document.getElementsByTagName('body')[0]
var theme = localStorage.getItem('theme')
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
