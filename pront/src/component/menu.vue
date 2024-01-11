<template>
<div class="">
    <nav class="bg-gray-50 dark:bg-gray-700 max-w-screen-xl flex">
        <div class="flex flex-wrap mx-auto p-4">
            <a href="https://github.com/Ashajisu/HAB" class="flex items-center">
                <img src="https://flowbite.com/docs/images/logo.svg" class="h-8 mr-3" alt="Flowbite Logo" />
                <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white mr-3">HAB</span>
            </a>
            <div class="flex items-center mr-6">
                <a href="https://github.com/Ashajisu/HAB" class="mr-6 text-sm  text-gray-500 dark:text-white hover:underline">contact</a>
                <a @click="fnMenuLink(menuNav.link.login)" class="mr-6 text-sm  text-blue-600 dark:text-blue-500 hover:underline">Login</a>
                <a @click="fnMenuLink(menuNav.link.logout)" class="mr-6 text-sm  text-blue-600 dark:text-blue-500 hover:underline">Logout</a>
                <a @click="fnMenuLink(menuNav.link.sign)" class="mr-6 text-sm  text-blue-600 dark:text-blue-500 hover:underline">Sign In</a>
            </div>
        </div>
        <div class="px-4 py-3 justify-between">
            <div class="flex items-center">
                <ul class="flex flex-row font-medium mt-0 mr-6 space-x-8 text-sm">
                    <li>
                        <a @click="fnMenuLink(menuNav.link.home)" class="text-gray-900 dark:text-white hover:underline" aria-current="page">{{menuNav.title.home}}</a>
                    </li>
                    <li>
                        <a @click="fnMenuLink(menuNav.link.link1)" class="text-gray-900 dark:text-white hover:underline">{{menuNav.title.menu1}}</a>
                    </li>
                    <li>
                        <a @click="fnMenuLink(menuNav.link.link2)" class="text-gray-900 dark:text-white hover:underline">{{menuNav.title.menu2}}</a>
                    </li>
                    <li>
                        <a @click="fnMenuLink(menuNav.link.link3)" class="text-gray-900 dark:text-white hover:underline">{{menuNav.title.menu3}}</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
</template>
<script>
export default {
    name: "menuNav",
    props: {
        menuNav: {
            type: Object,
            default: () => {
                return {
                    title : {
                        home : "Home",
                        menu1: "BalnaceSheet",
                        menu2: "admin",
                        menu3: "menu"
                    },
                    link :{
                        home : "/home",
                        login : "/home/login",
                        logout : "/home/logout",
                        sign : "/home/signing",
                        link1: "/view/BS",
                        link2: "/admin",
                        link3: "/component/menu"
                    }
                };
            }
        }
    },
    methods : {
        fnMenuLink(path) {
            this.$router.push({
                // path: '.' + path
                path: path
            })
        }
    }
}
</script>