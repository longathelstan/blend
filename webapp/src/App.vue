<script setup lang="ts">
import Sidebar from './components/Sidebar.vue'

const socket = new WebSocket('ws://localhost:6969/blend')
socket.onmessage = (e) => console.log('recieved: ', e.data)
socket.onerror = (e) => console.error('error with connection:', e)
socket.onclose = (e) => console.log('closed connection: ', e.reason)
socket.onopen = async () => {
    console.log('opened connection')
    socket.send('{"type": "info"}')
    await new Promise((res) => setTimeout(res, 2000))
    socket.close(1000, 'closed on purpose frr')
}
</script>

<template>
    <Sidebar />
    <h1>Empty....</h1>
</template>

<style lang="css"></style>
