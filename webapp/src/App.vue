<script setup lang="ts">
import axios from 'axios'
import type { ClientInfo } from './data/responses'
import { ref } from 'vue'

var clientInfo = ref<ClientInfo | null>(null)
axios
    .get('http://localhost:6969/api/client')
    .then((fr) => {
        clientInfo.value = fr.data
    })
    .catch((error) => {
        console.log(error)
    })
    .finally(() => {
        if (clientInfo.value == null) {
            document.title = 'What the'
        } else {
            document.title = clientInfo.value?.name + ' v' + clientInfo.value?.version
        }
    })
</script>

<template>
    <h1 v-if="clientInfo">{{ clientInfo.name }} v{{ clientInfo.version }}</h1>
    <h1 v-else>I do not know html and css, lol</h1>
</template>
