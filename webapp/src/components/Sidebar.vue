<script setup lang="ts">
import { FileJson, LayoutDashboard, Palette, type LucideIcon } from 'lucide-vue-next'

interface SidebarItem {
    label: String
    icon: LucideIcon
}
const items: SidebarItem[] = [
    {
        label: 'Modules',
        icon: LayoutDashboard,
    },
    {
        label: 'Configs',
        icon: FileJson,
    },
]

function switchTheme() {
    const body = document.getElementsByTagName('body')[0]
    var theme = body.className
    if (theme == 'dark') {
        theme = 'light'
    } else {
        theme = 'dark'
    }
    body.className = theme
    localStorage.setItem('theme', theme)
}
</script>

<template>
    <aside
        class="flex flex-col w-64 h-screen px-4 py-8 overflow-y-auto cursor-default bg-gray-100 border-r dark:bg-gray-900"
    >
        <h2 class="text-blue-500 text-2xl">Blend v6.0</h2>

        <div class="flex flex-col justify-between flex-1 mt-6">
            <nav>
                <a
                    v-for="item in items"
                    class="flex items-center px-4 py-2 mt-5 rounded text-gray-800 dark:text-gray-200 hover:bg-gray-200 hover:dark:bg-gray-800 hover:text-blue-500"
                >
                    <component :is="item.icon" />
                    <span class="m-2 font-medium">{{ item.label }}</span>
                </a>
            </nav>
            <div
                class="flex items-center px-4 py-2 rounded text-gray-800 dark:text-gray-200 hover:bg-gray-200 hover:dark:bg-gray-800 mt-auto"
                @click="switchTheme"
            >
                <component :is="Palette" />
                <span class="m-2 font-medium">Theme</span>
            </div>
        </div>
    </aside>
</template>
